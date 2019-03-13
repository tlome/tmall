package com.how2java.tmall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tl on 2018/10/12.
 */
public class UploadedImageFile {

    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
