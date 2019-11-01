package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.persistence.beans.SysRole;
import com.shengdingbox.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import com.shengdingbox.blog.business.vo.RoleConditionVO;
<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysRoleMapper.java
import com.shengdingbox.blog.persistence.beans.SysRole;
import com.shengdingbox.blog.plugin.BaseMapper;
=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysRoleMapper.java

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    List<SysRole> queryRoleListWithSelected(Integer userId);

    List<SysRole> listRolesByUserId(Long userId);
}
