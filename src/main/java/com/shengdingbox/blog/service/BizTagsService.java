package com.shengdingbox.blog.service;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Tags;
import com.shengdingbox.blog.vo.TagsConditionVO;
import com.zhouzifei.tool.dto.AbstractService;


/**
 * 标签
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public abstract class BizTagsService implements AbstractService<Tags, Long> {

    public abstract PageInfo<Tags> findPageBreakByCondition(TagsConditionVO vo);

    public abstract Tags getByName(String name);
}
