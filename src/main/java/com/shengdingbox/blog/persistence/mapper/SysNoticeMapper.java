package com.shengdingbox.blog.persistence.mapper;

import java.util.List;

import com.shengdingbox.blog.core.BaseMapper;
import com.shengdingbox.blog.persistence.beans.SysNotice;
import com.shengdingbox.blog.vo.NoticeConditionVO;

import org.springframework.stereotype.Repository;


/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@Repository
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    /**
     * 分页查询
     * @param vo
     *
     * @return
     */
    List<SysNotice> findPageBreakByCondition(NoticeConditionVO vo);
}
