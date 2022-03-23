package com.nyf.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAliyun(MultipartFile file);

    void removeAliyunVideoById(String id);
    //根据id删除多个阿里云视频
    void removeMoreVideo(List<String> videoIdList);

    String getPlayAuth(String id);
}
