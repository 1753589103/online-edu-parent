package com.nyf.serviceedu.controller;

import com.nyf.utils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/nyf")
@CrossOrigin //解决跨域问题
public class EduLoginController {
    //login
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin").data("avatar","http://www.weixintouxiang.cn/weixin/20140607090832328.gif");
    }
}
