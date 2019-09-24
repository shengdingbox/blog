package com.zhouzifei.blog.persistence.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhouzifei.blog.persistence.beans.SysConfig;
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
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    Map<String, Object> getSiteInfo();
}
