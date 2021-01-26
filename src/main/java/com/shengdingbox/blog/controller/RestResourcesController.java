package com.shengdingbox.blog.controller;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.core.shiro.ShiroService;
import com.shengdingbox.blog.entity.Resources;
import com.shengdingbox.blog.service.SysResourcesService;
import com.shengdingbox.blog.vo.ResourceConditionVO;
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

import java.util.List;

/**
 * 系统资源管理
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RestController
@RequestMapping("/resources")
public class RestResourcesController {

    @Autowired
    private SysResourcesService resourcesService;
    @Autowired
    private ShiroService shiroService;

    @RequiresPermissions("resources")
    @PostMapping("/list")
    public PageResult getAll(ResourceConditionVO vo) {
        vo.setPageSize(Integer.MAX_VALUE);
        PageInfo<Resources> pageInfo = resourcesService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("role:allotResource")
    @PostMapping("/resourcesWithSelected")
    public ResponseVO<List<Resources>> resourcesWithSelected(Long rid) {
        return ResultUtil.success(null, resourcesService.queryResourcesListWithSelected(rid));
    }

    @RequiresPermissions("resource:add")
    @PostMapping(value = "/add")
    @BussinessLog("添加资源")
    public ResponseVO add(Resources resources) {
        resourcesService.insert(resources);
        //更新权限
        shiroService.updatePermission();
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"resource:batchDelete", "resource:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    @BussinessLog("删除资源")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            resourcesService.removeByPrimaryKey(id);
        }

        //更新权限
        shiroService.updatePermission();
        return ResultUtil.success("成功删除 [" + ids.length + "] 个资源");
    }

    @RequiresPermissions("resource:get")
    @PostMapping("/get/{id}")
    @BussinessLog("获取资源详情")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.resourcesService.getByPrimaryKey(id));
    }

    @RequiresPermissions("resource:edit")
    @PostMapping("/edit")
    @BussinessLog("编辑资源")
    public ResponseVO edit(Resources resources) {
        try {
            resourcesService.updateSelective(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("资源修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }
}
