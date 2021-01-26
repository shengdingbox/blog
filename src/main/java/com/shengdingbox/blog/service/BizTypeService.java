package com.shengdingbox.blog.service;



import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Type;
import com.shengdingbox.blog.vo.TypeConditionVO;
import com.zhouzifei.tool.dto.AbstractService;


import java.util.List;

/**
 * 分类
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
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
