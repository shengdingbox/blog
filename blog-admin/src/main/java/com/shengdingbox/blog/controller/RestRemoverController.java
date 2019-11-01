package com.shengdingbox.blog.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.shengdingbox.blog.framework.object.ResponseVO;
import com.shengdingbox.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shengdingbox.blog.business.annotation.BussinessLog;
import com.shengdingbox.blog.business.consts.DateConst;
import com.shengdingbox.blog.business.service.RemoverService;

import cn.hutool.core.date.DateUtil;
import me.zhyd.hunter.config.HunterConfig;

/**
 * Remover：搬运工(英语渣渣，实在想不出好玩的名字了)
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RestController
@RequestMapping("/remover")
public class RestRemoverController {

    @Autowired
    private RemoverService removerService;

    @PostMapping("/run")
    @ResponseBody
    @BussinessLog("运行文章搬运工")
    public void run(Long typeId, HunterConfig config, HttpServletResponse response) throws IOException, InterruptedException {
        removerService.run(typeId, config, response.getWriter());
    }

    @PostMapping("/stop")
    @ResponseBody
    @BussinessLog("停止文章搬运工")
    public ResponseVO stop() {
        try {
            removerService.stop();
        } catch (Exception e) {
            return ResultUtil.error(e.getMessage());
        }
        return ResultUtil.success("程序已停止运行，当前时间 " + DateUtil.format(new Date(), DateConst.YYYY_MM_DD_HH_MM_SS_EN));
    }

    @PostMapping("/single")
    @ResponseBody
    @BussinessLog("抓取单个文章")
    public void single(Long typeId, String[] url, boolean convertImg, HttpServletResponse response) throws IOException, InterruptedException {
        removerService.crawlSingle(typeId, url, convertImg, response.getWriter());
    }

}
