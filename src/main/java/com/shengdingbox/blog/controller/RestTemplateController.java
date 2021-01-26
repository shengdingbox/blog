package com.shengdingbox.blog.controller;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Template;
import com.shengdingbox.blog.service.SysTemplateService;
import com.shengdingbox.blog.vo.TemplateConditionVO;
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
 * 模板管理
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RestController
@RequestMapping("/template")
public class RestTemplateController {
    @Autowired
    private SysTemplateService templateService;

    @RequiresPermissions("templates")
    @PostMapping("/list")
    public PageResult list(TemplateConditionVO vo) {
        PageInfo<Template> pageInfo = templateService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("template:add")
    @PostMapping(value = "/add")
    @BussinessLog("添加模板")
    public ResponseVO add(Template template) {
        templateService.insert(template);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"template:batchDelete", "template:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除模板")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            templateService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个模板");
    }

    @RequiresPermissions("template:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取模板详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.templateService.getByPrimaryKey(id));
    }

    @RequiresPermissions("template:edit")
    @PostMapping("/edit")
    @BussinessLog("编辑模板")
    public ResponseVO edit(Template template) {
        try {
            templateService.updateSelective(template);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("模板修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
