package com.zhouzifei.blog.persistence.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhouzifei.blog.business.vo.ArticleTagsConditionVO;
import com.zhouzifei.blog.persistence.beans.BizArticleTags;
import com.zhouzifei.blog.plugin.BaseMapper;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.zhouzifei.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface BizArticleTagsMapper extends BaseMapper<BizArticleTags>{

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<BizArticleTags> findPageBreakByCondition(ArticleTagsConditionVO vo);
}
