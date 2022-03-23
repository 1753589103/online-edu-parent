package com.nyf.serviceedu.controller;

import com.nyf.serviceedu.entity.CrmBanner;
import com.nyf.serviceedu.service.CrmBannerService;
import com.nyf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/cmsservice/bannerFront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有幻灯片
    @GetMapping("getAll")
    public R getAll(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return R.ok().data("list",list);
    }



}
