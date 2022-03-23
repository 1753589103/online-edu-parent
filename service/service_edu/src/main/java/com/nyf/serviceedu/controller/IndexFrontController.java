package com.nyf.serviceedu.controller;

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
//@CrossOrigin
@RequestMapping("/eduservice/indexFront")
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询前8条热门课程，查询前4个名师
    //查询前8条热门课程，查询前4个名师
    @GetMapping("/index")
    public R index(){
        //调用查询前8热门课程的方法
        List<EduCourse> courseList = eduCourseService.selectHotCourse();
        //查询前4张热门讲师
        List<EduTeacher> teacherList = eduTeacherService.selectHotTeacher();

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

}
