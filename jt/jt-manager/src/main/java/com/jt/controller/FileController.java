package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author huilong
 * @create 2020/8/5 14:22
 */
@RestController
public class FileController {


    @Autowired
    private FileService fileService;
    /**
     * 完成文件上传的入门案例
     * 利用springmvc中的工具api实现文件上传简化
     * 实现步骤：
     * 1、接收资源文件
     * 2、准备文件上传目录
     * 3、准备文件上传全路径
     */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage){

        //文件上传路径
        String fileDirPath = "d:/study/java/jt_project/images";
        File dirFile = new File(fileDirPath);
        if(!dirFile.exists()){
            //如果路径不存在，创建目录
            dirFile.mkdirs();//创建多级
        }

        //准备文件全路径，路径+文件名
        String filename = fileImage.getOriginalFilename();//获取资源文件的方法：文件名称.后缀

        File realFile = new File(fileDirPath+"/"+filename);

        //将字节信息输出到文件中
        try {
            fileImage.transferTo(realFile); //实现文件的上传
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "file upload ok";
    }


    /**
     * 1、检验文件有效性
     * 2、检验文件是否有恶意程序
     * 3、提高用户检索图片的效率
     * 4、为了防止重名图片的提交，提供自定义图片名称
     * 5、实现图片的物理上传
     * 6、准备访问图片的虚拟路径
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")
    public ImageVO upload(MultipartFile uploadFile){
        return fileService.upload(uploadFile);

    }


}
