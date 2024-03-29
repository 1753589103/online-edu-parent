package com.nyf.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.EduCourse;
import com.nyf.serviceedu.entity.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息方法
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoForm courseInfoForm);
    //根据课程id查询课程确认信息
    public CoursePublishVo getPublishCourseInfo(String courseId);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourse(String id);

    List<EduCourse> selectHotCourse();

    //前台多条件分页查询
    Map<String, Object> getCourseFrontInfo(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    //前台根据课程id，查询课程基础信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
