package com.shengdingbox.blog.service;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.UpdateRecorde;
import com.shengdingbox.blog.vo.UpdateRecordeConditionVO;
import com.zhouzifei.tool.dto.AbstractService;


/**
 * 更新记录
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public abstract class SysUpdateRecordeService implements AbstractService<UpdateRecorde, Long> {

    public abstract PageInfo<UpdateRecorde> findPageBreakByCondition(UpdateRecordeConditionVO vo);
}
