package com.shengdingbox.blog.service;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shengdingbox.blog.BaseJunitTest;
import com.shengdingbox.blog.business.entity.Comment;
import com.shengdingbox.blog.business.service.BizCommentService;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public class BizCommentServiceImplTest extends BaseJunitTest {

    @Autowired
    private BizCommentService commentService;

    @Test
    public void comment() throws InterruptedException {
        Comment comment = new Comment();
        comment.setPid(1L);
        comment.setNickname("测试");
        comment.setEmail("843977358@qq.com");
        comment.setQq("843977358");
        commentService.comment(comment);

        TimeUnit.SECONDS.sleep(60);
    }
}
