package com.nyf.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nyf.ExceptionHandler.exception.MyException;
import com.nyf.serviceedu.entity.TOrder;
import com.nyf.serviceedu.service.TOrderService;
import com.nyf.utils.JwtUtils;
import com.nyf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-23
 */
@RestController
@CrossOrigin
@RequestMapping("/eduorder/t-order")
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

    //生成订单的方法
    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        //从请求头中获取用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //判断是否登录
        if (StringUtils.isEmpty(memberId)){
            throw new MyException(20001,"请登录");
        }

        //生成订单，并生成对应的订单号
        String orderNo = tOrderService.createOrders(courseId,memberId);

        return R.ok().data("orderNo",orderNo);
    }

    //根据订单号查询订单信息
    @GetMapping("/getOrderInfoById/{orderNo}")
    public R getOrderInfoById(@PathVariable String orderNo){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder tOrder = tOrderService.getOne(wrapper);
        return R.ok().data("item",tOrder);
    }

}

