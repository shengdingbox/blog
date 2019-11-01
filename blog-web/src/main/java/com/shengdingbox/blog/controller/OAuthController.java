package com.shengdingbox.blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shengdingbox.blog.business.service.AuthService;
import com.shengdingbox.blog.util.RequestUtil;
import com.shengdingbox.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shengdingbox.blog.plugin.oauth.RequestFactory;

import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.AuthRequest;

/**
 *
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController {
    /**
     *
     */
    @Autowired
    private AuthService authService;

    @RequestMapping("/render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response, HttpSession session) throws IOException {
        AuthRequest authRequest = RequestFactory.getInstance(source).getRequest();
        session.setAttribute("historyUrl", RequestUtil.getReferer());
        response.sendRedirect(authRequest.authorize());
    }

    /**
     * 授权回调地址
     *
     * @param source    授权回调来源
     * @param code      认证code
     * @param auth_code 认证code，当使用支付宝登陆时，该值不为空。切勿修改该参数的命名！强迫症不要把他改成驼峰式命名哈~~~
     * @return
     */
    @RequestMapping("/callback/{source}")
    public ModelAndView login(@PathVariable("source") String source, String code, String auth_code, HttpSession session) {

        authService.login(source, code, auth_code);
        String historyUrl = (String) session.getAttribute("historyUrl");
        session.removeAttribute("historyUrl");
        if (StringUtils.isEmpty(historyUrl)) {
            return ResultUtil.redirect("/");
        }
        return ResultUtil.redirect(historyUrl);
    }

    /**
     * 收回授权
     *
     * @param source
     * @param token
     * @return
     * @throws IOException
     */
    @RequestMapping("/revoke/{source}/{token}")
    public ModelAndView revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) throws IOException {
        AuthRequest authRequest = RequestFactory.getInstance(source).getRequest();
        AuthResponse response = authRequest.revoke(AuthToken.builder().accessToken(token).build());
        if (response.getCode() == 2000) {
            return ResultUtil.redirect("/");
        }
        return ResultUtil.redirect("/login");
    }

    /**
     * 退出登录
     *
     * @throws IOException
     */
    @RequestMapping("/logout")
    public ModelAndView logout() {
        authService.logout();
        return ResultUtil.redirect("/");
    }

}
