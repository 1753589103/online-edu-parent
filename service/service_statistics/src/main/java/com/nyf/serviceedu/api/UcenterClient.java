package com.nyf.serviceedu.api;

import com.nyf.serviceedu.api.impl.UcenterClientImpl;
import com.nyf.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    //根据日期，获取那天注册人数
    @GetMapping("/serviceUcenter/ucenter/countRegister/{day}")
    public R countRegister(@PathVariable String day);
}
