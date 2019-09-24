package com.zhouzifei.blog.business.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhouzifei.blog.business.entity.Type;
import com.zhouzifei.blog.business.vo.TypeConditionVO;
import com.zhouzifei.blog.framework.object.AbstractService;

/**
 * 分类
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.zhouzifei.com
 * @date 2019年7月16日
 * @since 1.0
 */
public interface BizTypeService extends AbstractService<Type, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Type> findPageBreakByCondition(TypeConditionVO vo);

    List<Type> listParent();

    List<Type> listTypeForMenu();
}
