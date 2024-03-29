package com.shengdingbox.blog.controller;

import javax.servlet.http.HttpServletResponse;

import com.zhouzifei.tool.html.verification.Captcha;
import com.zhouzifei.tool.html.verification.GifCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.extern.slf4j.Slf4j;

/**
 * 验证码
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Slf4j
@Controller
public class KaptchaController {

    @GetMapping("/getKaptcha")
    @ResponseBody
    public void getKaptcha(HttpServletResponse response) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取验证码异常：{}", e.getMessage());
        }
    }

}
