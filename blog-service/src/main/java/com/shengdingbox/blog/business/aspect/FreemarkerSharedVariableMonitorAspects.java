package com.shengdingbox.blog.business.aspect;

import java.util.Date;
import java.util.Map;

import com.shengdingbox.blog.business.enums.ConfigKeyEnum;
import com.shengdingbox.blog.business.service.SysConfigService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/business/aspect/FreemarkerSharedVariableMonitorAspects.java
import com.shengdingbox.blog.business.enums.ConfigKeyEnum;
import com.shengdingbox.blog.business.service.SysConfigService;

=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/business/aspect/FreemarkerSharedVariableMonitorAspects.java
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于监控freemarker自定义标签中共享变量是否发生变化，发生变化时实时更新到内存中
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Slf4j
@Component
@Aspect
@Order(1)
public class FreemarkerSharedVariableMonitorAspects {

    private static volatile long configLastUpdateTime = 0L;
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    private SysConfigService configService;

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
        // 切面切入点
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        Map config = configService.getConfigs();
        if (null == config) {
            log.error("config为空");
            return;
        }
        Long updateTime = ((Date) config.get(ConfigKeyEnum.UPDATE_TIME.getKey())).getTime();
        if (updateTime == configLastUpdateTime) {
            log.debug("config表未更新");
            return;
        }
        log.debug("config表已更新，重新加载config到shared variable");
        configLastUpdateTime = updateTime;
        try {
            configuration.setSharedVariable("config", config);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }
}
