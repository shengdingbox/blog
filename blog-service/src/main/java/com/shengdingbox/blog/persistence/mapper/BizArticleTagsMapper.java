package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.business.vo.ArticleTagsConditionVO;
import com.shengdingbox.blog.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/BizArticleTagsMapper.java
import com.shengdingbox.blog.business.vo.ArticleTagsConditionVO;
import com.shengdingbox.blog.persistence.beans.BizArticleTags;
import com.shengdingbox.blog.plugin.BaseMapper;
=======
import com.shengdingbox.blog.persistence.beans.BizArticleTags;
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/persistence/mapper/BizArticleTagsMapper.java

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface BizArticleTagsMapper extends BaseMapper<BizArticleTags> {

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizArticleTags> findPageBreakByCondition(ArticleTagsConditionVO vo);
}
