package com.zhouzifei.blog.core.schedule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhouzifei.blog.business.entity.ArticleLook;
import com.zhouzifei.blog.business.service.BizArticleLookService;
import com.zhouzifei.blog.business.service.BizArticleService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.zhouzifei.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Slf4j
@Component
public class ArticleLookTask {
    @Autowired
    private BizArticleService bizArticleService;
    @Autowired
    private BizArticleLookService articleLookService;
    @Autowired
    private RedisTemplate redisTemplate;

    private BoundListOperations<String, ArticleLook> listOperations;

    @PostConstruct
    public void init() {
        this.listOperations = redisTemplate.boundListOps("article-look-cache");
    }

    /**
     * 保存文章的浏览记录，先进先出
     */
    public void addLookRecordToQueue(ArticleLook articleLook) {
        if (null == articleLook) {
            return;
        }
        listOperations.rightPush(articleLook);
    }

    /**
     * 每分钟保存一次文章的浏览记录
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
//    @Scheduled(cron = "0/5 * * * * ? ")
    public void save() {
        ArticleLook articleLook = null;
        while (null != (articleLook = listOperations.leftPop())) {
            if (!bizArticleService.isExist(articleLook.getArticleId())) {
                log.warn("{}-该文章不存在！", articleLook.getArticleId());
                return;
            }
            articleLookService.insert(articleLook);
        }
    }

}
