package com.nyf.serviceedu.api;

import com.nyf.serviceedu.entity.vo.EduCourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-edu")
public interface ServiceEduClient {

    //根据课程id，获取课程信息
    @PostMapping("/eduservice/course/getCourseInfoByIdOrder/{courseId}")
    public EduCourseVo getCourseInfoByIdOrder(@PathVariable String courseId);

}
