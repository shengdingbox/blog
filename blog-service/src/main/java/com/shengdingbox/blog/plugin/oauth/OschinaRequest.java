package com.shengdingbox.blog.plugin.oauth;

import com.shengdingbox.blog.framework.property.JustAuthProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/plugin/oauth/OschinaRequest.java
import com.shengdingbox.blog.framework.property.JustAuthProperties;

=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/plugin/oauth/OschinaRequest.java
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthOschinaRequest;
import me.zhyd.oauth.request.AuthRequest;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Component
public class OschinaRequest implements OauthRequest, InitializingBean {

    @Autowired
    private JustAuthProperties properties;

    @Override
    public AuthRequest getRequest() {
        AuthConfig authConfig = properties.getOschina();
        return new AuthOschinaRequest(AuthConfig.builder()
                .clientId(authConfig.getClientId())
                .clientSecret(authConfig.getClientSecret())
                .redirectUri(authConfig.getRedirectUri())
                .build());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RequestFactory.registerRequest("oschina", this);
    }
}
