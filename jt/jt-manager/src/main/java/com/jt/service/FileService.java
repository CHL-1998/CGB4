package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author huilong
 * @create 2020/8/5 15:43
 */
public interface FileService {

    ImageVO upload(MultipartFile uploadFile);
}
