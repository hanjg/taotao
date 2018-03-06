package com.taotao.service;

import com.taotao.common.pojo.PictureUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/4 12:19
 * @Description:
 */
public interface PictureService {

    PictureUploadResult uploadPicture(MultipartFile uploadFile);
}
