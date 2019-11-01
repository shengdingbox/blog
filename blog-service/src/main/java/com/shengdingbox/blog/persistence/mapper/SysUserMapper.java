package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.persistence.beans.SysUser;
import com.shengdingbox.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import com.shengdingbox.blog.business.vo.UserConditionVO;
<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysUserMapper.java
import com.shengdingbox.blog.persistence.beans.SysUser;
import com.shengdingbox.blog.plugin.BaseMapper;
=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysUserMapper.java

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findPageBreakByCondition(UserConditionVO vo);

    List<SysUser> listByRoleId(Long roleId);

}
