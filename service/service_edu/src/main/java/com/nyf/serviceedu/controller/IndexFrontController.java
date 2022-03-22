package com.nyf.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nyf.serviceedu.entity.EduCourse;
import com.nyf.serviceedu.entity.EduTeacher;
import com.nyf.serviceedu.service.EduCourseService;
import com.nyf.serviceedu.service.EduTeacherService;
import com.nyf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/indexFront")
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询前8条热门课程，查询前4个名师
    @GetMapping("/index")
    public R index(){
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(wrapper);

        //查询前4张名师
        QueryWrapper<EduTeacher> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(wrapper1);


        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

}
