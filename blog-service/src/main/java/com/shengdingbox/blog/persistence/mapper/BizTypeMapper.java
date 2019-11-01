package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.business.vo.TypeConditionVO;
import com.shengdingbox.blog.persistence.beans.BizType;
import com.shengdingbox.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/BizTypeMapper.java
import com.shengdingbox.blog.business.vo.TypeConditionVO;
import com.shengdingbox.blog.persistence.beans.BizType;
import com.shengdingbox.blog.plugin.BaseMapper;

=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/BizTypeMapper.java
/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface BizTypeMapper extends BaseMapper<BizType> {

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizType> findPageBreakByCondition(TypeConditionVO vo);

    List<BizType> listParent();

    List<BizType> listTypeForMenu();
}
