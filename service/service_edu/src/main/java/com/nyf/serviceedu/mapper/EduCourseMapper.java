package com.nyf.serviceedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nyf.serviceedu.entity.EduCourse;
import com.nyf.serviceedu.entity.vo.CoursePublishVo;
import com.nyf.serviceedu.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
