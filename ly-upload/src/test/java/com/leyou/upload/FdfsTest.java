package com.leyou.upload;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan("com.leyou.upload.configura")
public class FdfsTest {
    
    @Autowired
    private FastFileStorageClient storageClient;
    
    @Autowired
    private ThumbImageConfig thumbImageConfig;
    
    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("C:\\Users\\17123\\Pictures\\Saved Pictures\\pexels-photo-91228.jpeg");
        // 上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpeg", null);
        // 带分组的路径
        System.out.println("带分组的路径" + storePath.getFullPath());
        // 不带分组的路径
        System.out.println("不带分组的路径" + storePath.getPath());
    }
    
    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("C:\\Users\\17123\\Pictures\\Saved Pictures\\pexels-photo-91228.jpeg");
        // 上传并且生成缩略图 上传原图并生成缩略图
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "jpeg", null);
        // 带分组的路径
        System.out.println("带分组的路径" + storePath.getFullPath());
        // 不带分组的路径
        System.out.println("不带分组的路径" + storePath.getPath());
        // 获取缩略图路径
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println("获取缩略图路径" + path);
    }
}