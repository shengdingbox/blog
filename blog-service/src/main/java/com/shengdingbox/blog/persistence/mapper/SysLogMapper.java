package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.business.vo.LogConditionVO;
import com.shengdingbox.blog.persistence.beans.SysLog;
import com.shengdingbox.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysLogMapper.java
import com.shengdingbox.blog.business.vo.LogConditionVO;
import com.shengdingbox.blog.persistence.beans.SysLog;
import com.shengdingbox.blog.plugin.BaseMapper;

=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysLogMapper.java
/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<SysLog> findPageBreakByCondition(LogConditionVO vo);
}
