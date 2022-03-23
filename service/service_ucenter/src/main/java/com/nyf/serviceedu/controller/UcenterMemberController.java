package com.nyf.serviceedu.controller;


import com.nyf.serviceedu.entity.UcenterMember;
import com.nyf.serviceedu.entity.vo.LoginVo;
import com.nyf.serviceedu.entity.vo.RegisterVo;
import com.nyf.serviceedu.entity.vo.UcenterMemberVo;
import com.nyf.serviceedu.service.UcenterMemberService;
import com.nyf.utils.JwtUtils;
import com.nyf.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-22
 */
@RestController
@CrossOrigin
@RequestMapping("/serviceUcenter/ucenter")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        //返回token，使用jwt生成
        System.out.println(loginVo.getPassword()+loginVo.getPhone());
        String token = ucenterMemberService.login(loginVo);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getUserInfoForJwt")
    public R getUserInfoForJwt(HttpServletRequest request) {
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库，根据用户id，获取用户信息
        UcenterMember member = ucenterMemberService.getById(id);

        return R.ok().data("userInfo", member);
    }

    //根据用户id查询用户信息
    @PostMapping("/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String memberId){
        UcenterMember member = ucenterMemberService.getById(memberId);
        UcenterMemberVo memberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member,memberVo);

        return memberVo;
    }
}

