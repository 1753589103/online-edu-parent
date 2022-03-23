package com.nyf.serviceedu.schedule;

import com.nyf.serviceedu.service.StatisticsDailyService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService dailyService;

    //（0/5 * * * * ？）：每隔5秒执行一次
    @Scheduled(cron = "0 0 1 * * ? ")//指定cron表达式规则
    public void task01(){
        dailyService.createStatisticsByDay(DateUtils.formatDate(org.apache.commons.lang3.time.DateUtils.addDays(new Date(), -1)));

    }
}
