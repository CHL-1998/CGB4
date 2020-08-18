package com.jt.service.impl;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @Author huilong
 * @create 2020/8/5 15:44
 */
@Service
@PropertySource("classpath:/properties/image.properties")//指定properties为属性赋值
public class FileServiceImpl implements FileService {

    private static Set<String> imageTypeSet = new HashSet<>();
    @Value("${image.localDirPath}")
    private String localDirPath;
    @Value("${image.urlPath}")
    private String urlPath;

    static{
        imageTypeSet.add(".jpg");
        imageTypeSet.add(".png");
        imageTypeSet.add(".gif");
    }

    /**
     *
     * 1、检验文件有效性
     * 2、检验文件是否有恶意程序
     * 3、提高用户检索图片的效率
     * 4、为了防止重名图片的提交，提供自定义图片名称
     * 5、实现图片的物理上传
     * 6、准备访问图片的虚拟路径
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile) {

        //检验图片类型  1、利用正则表达式进行校验 2、利用集合进行校验 Set 数据是否存在即可
        //获取文件名+后缀名并全部转换为小写避免后缀名为大写的情况
        String fileName = uploadFile.getOriginalFilename().toLowerCase();
        String fileType = fileName.substring(fileName.lastIndexOf("."));



        String regex = "\\.(jpg|png|gif)\\b"; //正则表达式判断文件类型
        if(!Pattern.matches(regex,fileType))
            return ImageVO.fail();
        /*if(!imageTypeSet.contains(fileType))
            return new ImageVO(1);//图片上传失败
            //return ImageVO.fail();图片上传失败*/

        //2、判断文件是否为恶意程序，文件是否有图片的特有属性
        //2.1、将上传的文件类型利用图片的api进行转换，如果转化不成功则一定不是图片
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            //2.2 校验是否有图片的特有属性
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            //2.3 校验高度宽度是否有值
            if(width == 0 || height == 0)return ImageVO.fail();
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVO.fail(); //不是图片，返回失败
        }
        //3、实现分目录存储
        //方案一：利用hash之后每隔2-3位数截取之后拼接
        //方案二： 以时间为单位进行分隔 /yyyy/MM/dd/
        //3.1 利用工具api将时间转化为指定的格式
        String datePath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        //3.2 动态生成文件目录 两部分构成：根目录，时间目录
        String localDir = localDirPath + datePath;


        //判断目录是否存在，如果不存在构建目录
        File dirFile = new File(localDir);
        if(!dirFile.exists())dirFile.mkdirs();

        //4、防止文件重名，需要自定义文件名称
        String uuid = UUID.randomUUID().toString().replace("-","");
        String uuidFileName = uuid + fileType;

        //5、实现文件上传，准备文件全路径
        String realFilePath = localDir + uuidFileName;

        //5.1、封装文件的真实对象
        File imageFile = new File(realFilePath);

        try {
            uploadFile.transferTo(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            ImageVO.fail();//文件上传失败
        }

        return ImageVO.success(urlPath+datePath+uuidFileName);
    }
}
