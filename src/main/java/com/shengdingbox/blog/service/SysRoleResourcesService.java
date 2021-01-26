package com.shengdingbox.blog.service;


import com.shengdingbox.blog.entity.RoleResources;
import com.zhouzifei.tool.dto.AbstractService;

/**
 * 角色资源
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public interface SysRoleResourcesService extends AbstractService<RoleResources, Long> {

    /**
     * 添加角色资源
     *
     * @param roleId
     * @param resourcesId
     */
    void addRoleResources(Long roleId, String resourcesId);

    /**
     * 通过角色id批量删除
     *
     * @param roleId
     */
    void removeByRoleId(Long roleId);
}
