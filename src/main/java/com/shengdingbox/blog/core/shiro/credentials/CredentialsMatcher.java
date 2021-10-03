package com.shengdingbox.blog.core.shiro.credentials;

import com.zhouzifei.tool.html.encryption.PasswordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Shiro-密码凭证匹配器（验证密码有效性
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        try {
            dbPassword = PasswordUtil.decrypt(dbPassword, utoken.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //进行密码的比对
        return this.equals(inPassword, dbPassword);
    }
}
