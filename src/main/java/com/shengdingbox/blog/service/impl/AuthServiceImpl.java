package com.shengdingbox.blog.service.impl;


import com.shengdingbox.blog.entity.User;
import com.shengdingbox.blog.enums.UserTypeEnum;
import com.shengdingbox.blog.service.AuthService;
import com.shengdingbox.blog.service.SysUserService;
import com.shengdingbox.blog.utils.SessionUtil;
import com.zhouzifei.tool.plugin.oauth.RequestFactory;
import com.zhouzifei.tool.util.BeanConvertUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserService userService;

    @Override
    public boolean login(String source, String code, String auth_code) {
        AuthRequest authRequest = RequestFactory.getInstance(source).getRequest();
        AuthResponse response = authRequest.login(StringUtils.isEmpty(code) ? auth_code : code);
        if (response.ok()) {
            AuthUser authUser = (AuthUser) response.getData();
            User newUser = BeanConvertUtil.doConvert(authUser, User.class);
            newUser.setSource(authUser.getSource().toString());
            if (null != authUser.getGender()) {
                newUser.setGender(authUser.getGender().getCode());
            }
            User user = userService.getByUuidAndSource(authUser.getUuid(), authUser.getSource().toString());
            newUser.setUserType(UserTypeEnum.USER);
            if (null != user) {
                newUser.setId(user.getId());
                userService.updateSelective(newUser);
            } else {
                userService.insert(newUser);
            }
            SessionUtil.setUser(newUser);
            return true;
        }
        log.warn("[{}] {}", source, response.getMsg());
        return false;
    }

    @Override
    public boolean revoke(String source, Long userId) {
        return false;
    }

    @Override
    public void logout() {
        SessionUtil.removeUser();
    }
}
