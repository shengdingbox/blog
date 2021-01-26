package com.shengdingbox.blog.service;


import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.entity.File;
import com.shengdingbox.blog.vo.FileConditionVO;
import com.zhouzifei.tool.dto.AbstractService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public abstract class BizFileService implements AbstractService<File, Long> {

    public abstract PageInfo<File> findPageBreakByCondition(FileConditionVO vo);

    public abstract File selectFileByPathAndUploadType(String filePath, String uploadType);

    public abstract void remove(Long[] ids);

    public abstract int upload(MultipartFile[] file);
}
