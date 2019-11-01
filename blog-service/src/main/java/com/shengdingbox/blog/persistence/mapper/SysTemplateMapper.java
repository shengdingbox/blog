package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.business.vo.TemplateConditionVO;
import com.shengdingbox.blog.persistence.beans.SysTemplate;
import com.shengdingbox.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysTemplateMapper.java
import com.shengdingbox.blog.business.vo.TemplateConditionVO;
import com.shengdingbox.blog.persistence.beans.SysTemplate;
import com.shengdingbox.blog.plugin.BaseMapper;

=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/SysTemplateMapper.java
/**
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface SysTemplateMapper extends BaseMapper<SysTemplate> {

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<SysTemplate> findPageBreakByCondition(TemplateConditionVO vo);
}
