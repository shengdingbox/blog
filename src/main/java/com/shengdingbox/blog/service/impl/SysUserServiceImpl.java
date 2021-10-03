package com.shengdingbox.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.User;
import com.shengdingbox.blog.entity.UserPwd;
import com.shengdingbox.blog.enums.UserNotificationEnum;
import com.shengdingbox.blog.enums.UserPrivacyEnum;
import com.shengdingbox.blog.enums.UserStatusEnum;
import com.shengdingbox.blog.persistence.beans.SysUser;
import com.shengdingbox.blog.persistence.mapper.SysUserMapper;
import com.shengdingbox.blog.service.SysUserService;
import com.shengdingbox.blog.vo.UserConditionVO;
import com.zhouzifei.tool.exception.ServiceException;
import com.zhouzifei.tool.exception.CommentException;
import com.zhouzifei.tool.holder.RequestHolder;
import com.zhouzifei.tool.html.IpUtil;
import com.zhouzifei.tool.html.encryption.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insert(User user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setRegIp(IpUtil.getIpAddress(RequestHolder.getRequest()));
        user.setPrivacy(UserPrivacyEnum.PUBLIC.getCode());
        user.setNotification(UserNotificationEnum.DETAIL);
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUserMapper.insertSelective(user.getSysUser());
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<User> users) {
        Assert.notNull(users, "Users不可为空！");
        List<SysUser> sysUsers = new ArrayList<>();
        String regIp = IpUtil.getIpAddress(RequestHolder.getRequest());
        for (User user : users) {
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
            user.setRegIp(regIp);
            user.setPrivacy(UserPrivacyEnum.PUBLIC.getCode());
            user.setNotification(UserNotificationEnum.DETAIL);
            sysUsers.add(user.getSysUser());
        }
        sysUserMapper.insertList(sysUsers);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysUserMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(User user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommentException("密码加密失败");
            }
        } else {
            user.setPassword(null);
        }
        return sysUserMapper.updateByPrimaryKeySelective(user.getSysUser()) > 0;
    }

    @Override
    public User getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(primaryKey);
        return null == sysUser ? null : new User(sysUser);
    }

    @Override
    public List<User> listAll() {
        List<SysUser> sysUsers = sysUserMapper.selectAll();

        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            users.add(new User(sysUser));
        }
        return users;
    }

    @Override
    public PageInfo<User> findPageBreakByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new User(su));
        }
        PageInfo bean = new PageInfo<SysUser>(sysUsers);
        bean.setList(users);
        return bean;
    }

    /**
     * 更新用户最后一次登录的状态信息
     *
     * @param user
     * @return
     */
    @Override
    public User updateUserLastLoginInfo(User user) {
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.getIpAddress(RequestHolder.getRequest()));
            user.setPassword(null);
            this.updateSelective(user);
        }
        return user;
    }

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    @Override
    public User getByUserName(String userName) {
        User user = new User(userName, null);
        SysUser sysUser = this.sysUserMapper.selectOne(user.getSysUser());
        return null == sysUser ? null : new User(sysUser);
    }

    /**
     * 通过角色Id获取用户列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<User> listByRoleId(Long roleId) {
        List<SysUser> sysUsers = sysUserMapper.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new User(su));
        }
        return users;
    }

    /**
     * 修改密码
     *
     * @param userPwd
     * @return
     */
    @Override
    public boolean updatePwd(UserPwd userPwd) throws Exception {
        if (!userPwd.getNewPassword().equals(userPwd.getNewPasswordRepeat())) {
            throw new ServiceException("新密码不一致！");
        }
        User user = this.getByPrimaryKey(userPwd.getId());
        if (null == user) {
            throw new ServiceException("用户编号错误！请不要手动操作用户ID！");
        }

        if (!user.getPassword().equals(PasswordUtil.encrypt(userPwd.getPassword(), user.getUsername()))) {
            throw new ServiceException("原密码不正确！");
        }
        user.setPassword(userPwd.getNewPassword());

        return this.updateSelective(user);
    }

    @Override
    public User getByUuidAndSource(String uuid, String source) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(source)) {
            return null;
        }
        SysUser user = new SysUser();
        user.setUuid(uuid);
        user.setSource(source);
        user = sysUserMapper.selectOne(user);
        return null == user ? null : new User(user);
    }


}
