package com.shengdingbox.blog.util.FileClient;

import java.io.File;
import java.io.InputStream;

import com.shengdingbox.blog.entity.VirtualFile;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD:blog-service/src/main/java/com/shengdingbox/blog/util/FileClient/ApiClient.java
import com.shengdingbox.blog.entity.VirtualFile;

=======
>>>>>>> origin/origin:blog-service/src/main/java/com/shengdingbox/blog/util/FileClient/ApiClient.java
/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.shengdingbox.com
 * @date 2019年7月16日
 * @since 1.0
 */
public interface ApiClient {

    VirtualFile uploadImg(MultipartFile file);

    VirtualFile uploadImg(File file);

    VirtualFile uploadImg(InputStream is, String key);

    boolean removeFile(String key);
}
