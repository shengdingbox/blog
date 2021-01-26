package com.shengdingbox.blog.service;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Comment;
import com.shengdingbox.blog.vo.CommentConditionVO;
import com.zhouzifei.tool.dto.AbstractService;
import com.zhouzifei.tool.exception.CommentException;

import java.util.List;
import java.util.Map;

/**
 * 评论
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public interface BizCommentService extends AbstractService<Comment, Long> {

    PageInfo<Comment> findPageBreakByCondition(CommentConditionVO vo);

    Map<String, Object> list(CommentConditionVO vo);

    /**
     * admin发表评论
     *
     * @param comment
     * @return
     */
    void commentForAdmin(Comment comment) throws CommentException;

    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    Comment comment(Comment comment) throws CommentException;

    /**
     * 查询近期评论
     *
     * @param pageSize
     * @return
     */
    List<Comment> listRecentComment(int pageSize);

    /**
     * 查询未审核的评论
     *
     * @param pageSize
     * @return
     */
    List<Comment> listVerifying(int pageSize);

    /**
     * 点赞
     *
     * @param id
     */
    void doSupport(Long id);

    /**
     * 点踩
     *
     * @param id
     */
    void doOppose(Long id);
}
