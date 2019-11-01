package com.shengdingbox.blog.framework.config;

import javax.annotation.PostConstruct;

import com.shengdingbox.blog.business.service.SysConfigService;
import com.shengdingbox.blog.framework.tag.CustomTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.jagregory.shiro.freemarker.ShiroTags;
<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/framework/config/FreeMarkerConfig.java
import com.shengdingbox.blog.business.service.SysConfigService;
import com.shengdingbox.blog.framework.tag.ArticleTags;
import com.shengdingbox.blog.framework.tag.CustomTags;
=======
import com.shengdingbox.blog.framework.tag.ArticleTags;
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/framework/config/FreeMarkerConfig.java

import freemarker.template.TemplateModelException;

/**
 * freemarker配置类
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTags customTags;
    @Autowired
    protected ArticleTags articleTags;
    @Autowired
    private SysConfigService configService;

    /**
     * 添加自定义标签
     */
    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("zhydTag", customTags);
        
        try {
        	configuration.setSharedVariable("articleTag", articleTags);
            configuration.setSharedVariable("config", configService.getConfigs());
            //shiro标签
            configuration.setSharedVariable("shiro", new ShiroTags());
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }
}
