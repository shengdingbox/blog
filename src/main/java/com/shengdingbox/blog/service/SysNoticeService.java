package com.shengdingbox.blog.service;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.Notice;
import com.shengdingbox.blog.vo.NoticeConditionVO;
import com.zhouzifei.tool.dto.SysNoticeDTO;

import java.util.List;


/**
 * 系统通知
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public interface SysNoticeService extends AbstractService<Notice, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Notice> findPageBreakByCondition(NoticeConditionVO vo);

    /**
     * 获取已发布的通知列表
     *
     * @return
     */
    List<SysNoticeDTO> listRelease();
}
