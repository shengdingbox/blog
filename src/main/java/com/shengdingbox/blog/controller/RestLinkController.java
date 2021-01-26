package com.shengdingbox.blog.controller;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Link;
import com.shengdingbox.blog.enums.LinkSourceEnum;
import com.shengdingbox.blog.enums.TemplateKeyEnum;
import com.shengdingbox.blog.service.MailService;
import com.shengdingbox.blog.service.SysLinkService;
import com.shengdingbox.blog.vo.LinkConditionVO;
import com.zhouzifei.tool.annotation.BussinessLog;
import com.zhouzifei.tool.consts.ResponseStatus;
import com.zhouzifei.tool.dto.PageResult;
import com.zhouzifei.tool.dto.ResponseVO;
import com.zhouzifei.tool.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 友情链接
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RestController
@RequestMapping("/link")
public class RestLinkController {
    @Autowired
    private SysLinkService linkService;
    @Autowired
    private MailService mailService;

    @RequiresPermissions("links")
    @PostMapping("/list")
    public PageResult list(LinkConditionVO vo) {
        PageInfo<Link> pageInfo = linkService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("link:add")
    @PostMapping(value = "/add")
    @BussinessLog("添加友情链接")
    public ResponseVO add(Link link) {
        link.setSource(LinkSourceEnum.ADMIN);
        linkService.insert(link);
        mailService.send(link, TemplateKeyEnum.TM_LINKS);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"link:batchDelete", "link:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除友情链接")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            linkService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个友情链接");
    }

    @RequiresPermissions("link:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取友情链接详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.linkService.getByPrimaryKey(id));
    }

    @RequiresPermissions("link:edit")
    @PostMapping("/edit")
    @BussinessLog("编辑友情链接")
    public ResponseVO edit(Link link) {
        try {
            linkService.updateSelective(link);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("友情链接修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
