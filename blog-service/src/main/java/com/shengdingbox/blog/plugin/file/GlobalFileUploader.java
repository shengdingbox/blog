package com.shengdingbox.blog.plugin.file;

import java.io.File;
import java.io.InputStream;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/plugin/file/GlobalFileUploader.java
import com.shengdingbox.blog.entity.VirtualFile;
import com.shengdingbox.blog.exception.GlobalFileException;
import com.shengdingbox.blog.util.FileClient.ApiClient;
import com.shengdingbox.blog.util.FileClient.FileUploader;
=======
import com.shengdingbox.blog.util.FileClient.ApiClient;
import com.shengdingbox.blog.util.FileClient.FileUploader;
import com.shengdingbox.blog.entity.VirtualFile;
import com.shengdingbox.blog.exception.GlobalFileException;
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/plugin/file/GlobalFileUploader.java

/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public class GlobalFileUploader extends BaseFileUploader implements FileUploader {

    @Override
    public VirtualFile upload(InputStream is, String uploadType, String imageUrl, boolean save) {
        ApiClient apiClient = this.getApiClient(uploadType);
        VirtualFile virtualFile = apiClient.uploadImg(is, imageUrl);
        return this.saveFile(virtualFile, save, uploadType);
    }

    @Override
    public VirtualFile upload(File file, String uploadType, boolean save) {
        ApiClient apiClient = this.getApiClient(uploadType);
        VirtualFile virtualFile = apiClient.uploadImg(file);
        return this.saveFile(virtualFile, save, uploadType);
    }

    @Override
    public VirtualFile upload(MultipartFile file, String uploadType, boolean save) {
        ApiClient apiClient = this.getApiClient(uploadType);
        VirtualFile virtualFile = apiClient.uploadImg(file);
        return this.saveFile(virtualFile, save, uploadType);
    }

    @Override
    public boolean delete(String filePath, String uploadType) {
        if (StringUtils.isEmpty(filePath)) {
            throw new GlobalFileException("[文件服务]文件删除失败，文件为空！");
        }

        ApiClient apiClient = this.getApiClient(uploadType);
        return this.removeFile(apiClient, filePath, uploadType);
    }
}
