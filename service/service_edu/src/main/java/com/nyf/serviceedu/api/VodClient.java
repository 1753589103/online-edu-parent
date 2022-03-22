package com.nyf.serviceedu.api;

import com.nyf.serviceedu.api.impl.VodClientImpl;
import com.nyf.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {
    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAliyunVideoById/{id}")
    public R removeAliyunVideoById(@PathVariable("id") String id);

    //根据多个视频id删除多个阿里云视频
    @DeleteMapping("/eduvod/video/removeBatch")
    public R removeBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
