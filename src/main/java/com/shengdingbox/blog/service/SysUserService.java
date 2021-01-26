package com.shengdingbox.blog.service;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.User;
import com.shengdingbox.blog.entity.UserPwd;
import com.shengdingbox.blog.vo.UserConditionVO;
import com.zhouzifei.tool.dto.AbstractService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Component
public interface SysUserService extends AbstractService<User, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<User> findPageBreakByCondition(UserConditionVO vo);

    /**
     * 更新用户最后一次登录的状态信息
     *
     * @param user
     * @return
     */
    User updateUserLastLoginInfo(User user);

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    User getByUserName(String userName);

    /**
     * 通过角色Id获取用户列表
     *
     * @param roleId
     * @return
     */
    List<User> listByRoleId(Long roleId);

    /**
     * 修改密码
     *
     * @param userPwd
     * @return
     */
    boolean updatePwd(UserPwd userPwd) throws Exception;


    /**
     * 通过用户的uuid和source查询用户是否存在
     *
     * @param uuid
     * @param source
     * @return
     */
    User getByUuidAndSource(String uuid, String source);
}
