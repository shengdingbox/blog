package com.shengdingbox.blog.controller;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Comment;
import com.shengdingbox.blog.enums.TemplateKeyEnum;
import com.shengdingbox.blog.service.BizCommentService;
import com.shengdingbox.blog.service.MailService;
import com.shengdingbox.blog.vo.CommentConditionVO;
import com.zhouzifei.tool.consts.ResponseStatus;
import com.zhouzifei.tool.dto.PageResult;
import com.zhouzifei.tool.dto.ResponseVO;
import com.zhouzifei.tool.exception.CommentException;
import com.zhouzifei.tool.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论管理类
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RestController
@RequestMapping("/comment")
public class RestCommentController {
    @Autowired
    private BizCommentService commentService;
    @Autowired
    private MailService mailService;

    @RequiresPermissions("comments")
    @PostMapping("/list")
    public PageResult list(CommentConditionVO vo) {
        PageInfo<Comment> pageInfo;
        pageInfo = commentService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("comment:reply")
    @PostMapping(value = "/reply")
    @BussinessLog("回复评论")
    public ResponseVO reply(Comment comment) {
        try {
            commentService.commentForAdmin(comment);
        } catch (CommentException e){
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"comment:batchDelete", "comment:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除评论[{1}]")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            commentService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 条评论");
    }

    @RequiresPermissions("comments")
    @PostMapping("/get/{id}")
    @BussinessLog("获取评论[{1}]详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.commentService.getByPrimaryKey(id));
    }

    @RequiresPermissions("comments")
    @PostMapping("/edit")
    @BussinessLog("编辑评论")
    public ResponseVO edit(Comment comment) {
        try {
            commentService.updateSelective(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("评论修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

    @RequiresPermissions("comment:audit")
    @PostMapping("/audit")
    @BussinessLog("审核评论")
    public ResponseVO audit(Comment comment, String contentText, Boolean sendEmail) {
        try {
            commentService.updateSelective(comment);
            if(!StringUtils.isEmpty(contentText)){
                comment.setContent(contentText);
                commentService.commentForAdmin(comment);
            }
            if(null != sendEmail && sendEmail){
                Comment commentDB = commentService.getByPrimaryKey(comment.getId());
                mailService.send(commentDB, TemplateKeyEnum.TM_COMMENT_AUDIT, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("评论审核失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

    /**
     * 正在审核的
     *
     * @param comment
     * @return
     */
    @RequiresUser
    @PostMapping("/listVerifying")
    public ResponseVO listVerifying(Comment comment) {
        return ResultUtil.success(null, commentService.listVerifying(10));
    }

}
