package com.shengdingbox.blog.controller;

import com.github.pagehelper.PageInfo;
import com.shengdingbox.blog.service.BizFileService;
import com.shengdingbox.blog.vo.FileConditionVO;
import com.zhouzifei.tool.annotation.BussinessLog;
import com.zhouzifei.tool.dto.ResponseVO;
import com.zhouzifei.tool.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RestController
@RequestMapping("/file")
public class RestFileController {
    @Autowired
    private BizFileService fileService;

    @RequiresPermissions("files")
    @PostMapping("/list")
    public PageInfo list(FileConditionVO vo) {
        vo.setPageSize(20);
        return fileService.findPageBreakByCondition(vo);
    }

    @RequiresPermissions("files")
    @PostMapping(value = "/remove")
    @BussinessLog("删除文件，ids:{1}")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        fileService.remove(ids);

        return ResultUtil.success("成功删除 [" + ids.length + "] 张图片");
    }

    @RequiresPermissions("files")
    @PostMapping(value = "/add")
    @BussinessLog("添加文件")
    public ResponseVO add(MultipartFile[] file) {
        if (null == file || file.length == 0) {
            return ResultUtil.error("请至少选择一张图片！");
        }
        int res = fileService.upload(file);
        return ResultUtil.success("成功上传" + res + "张图片");
    }
}
