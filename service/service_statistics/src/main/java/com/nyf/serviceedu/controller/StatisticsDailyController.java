package com.nyf.serviceedu.controller;


import com.nyf.serviceedu.service.StatisticsDailyService;
import com.nyf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/staservice/daily")
//@CrossOrigin //解决跨域问题
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;

    //统计某一天注册人数
    @PostMapping("/createStatisticsByDay/{day}")
    public R createStatisticsByDay(@PathVariable String day){
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){

        Map<String,Object> map = dailyService.getShowData(type,begin,end);

        return R.ok().data(map);
    }

}

