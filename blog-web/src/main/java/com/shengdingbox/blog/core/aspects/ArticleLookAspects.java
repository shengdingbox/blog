package com.shengdingbox.blog.core.aspects;

import java.util.Date;

import com.shengdingbox.blog.core.schedule.ArticleLookTask;
import com.shengdingbox.blog.framework.holder.RequestHolder;
import com.shengdingbox.blog.util.IpUtil;
import com.shengdingbox.blog.util.SessionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.shengdingbox.blog.business.entity.ArticleLook;

import lombok.extern.slf4j.Slf4j;

/**
 * 文章浏览记录aop操作
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
public class ArticleLookAspects {

    @Autowired
    private ArticleLookTask task;

    @Pointcut("execution(* com.shengdingbox.blog.controller.RenderController.article(..))")
    public void pointcut() {
        // 切面切入点
    }

    //@Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String userIp = IpUtil.getRealIp(RequestHolder.getRequest());
            Long articleId = (Long) args[1];
            ArticleLook articleLook = new ArticleLook();
            articleLook.setArticleId(articleId);
            articleLook.setUserIp(userIp);
            articleLook.setLookTime(new Date());
            if (SessionUtil.getUser() != null) {
                articleLook.setUserId(SessionUtil.getUser().getId());
            }
            task.addLookRecordToQueue(articleLook);
        }
    }
}
