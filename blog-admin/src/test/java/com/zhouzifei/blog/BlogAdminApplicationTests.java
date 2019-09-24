package com.zhouzifei.blog;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhouzifei.blog.business.enums.FileUploadType;
import com.zhouzifei.blog.util.FileClient.FileUploader;
import com.zhouzifei.blog.entity.VirtualFile;
import com.zhouzifei.blog.plugin.file.GlobalFileUploader;
import com.zhouzifei.blog.util.PasswordUtil;
/**
 * 
 * @author Dabao (17611555590@163.com)
 * @version 1.0
 * @website https://www.zhouzifei.com
 * @date 2019年7月16日
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BlogAdminApplicationTests {

    @Test
    public void uploadFile() {
        FileUploader uploader = new GlobalFileUploader();
        File file = new File("C:\\Users\\yadon\\Desktop\\新建文件夹\\web-index-pc.png");
        VirtualFile virtualFile = uploader.upload(file, FileUploadType.SIMPLE.getPath(), true);
        System.out.println(virtualFile);
    }
    
    @Test
    public  void password() {
    	
    	try {
			String encrypt = PasswordUtil.encrypt("xiaobao", "xiaofei");
			System.out.println(encrypt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}
