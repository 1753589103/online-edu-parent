package com.nyf.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideoAliyun(MultipartFile file);

    void removeAliyunVideoById(String id);
}
