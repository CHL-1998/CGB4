package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author huilong
 * @create 2020/8/5 15:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ImageVO {
    private Integer error=0; //确认是否有错误， 正常0，错误1
    private String url; //图片访问的虚拟地址
    private Integer width; //宽度
    private Integer height; //高度

    public static ImageVO fail(){
        return new ImageVO(1,null,null,null );
    }

    public static ImageVO success(String url,Integer width,Integer height){
        return new ImageVO(0,url,width,height);
    }

    public static ImageVO success(String url){
        return new ImageVO(0,url,null,null);
    }

    public ImageVO(Integer error){
        this.error=error;
    }

    public ImageVO(String url,Integer width,Integer height){
        this.url = url;
        this.width = width;
        this.height = height;
    }
}
