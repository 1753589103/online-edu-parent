package com.nyf.serviceedu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nyf.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nyf.serviceedu.entity.EduVideo;
import com.nyf.serviceedu.entity.vo.CoursePublishVo;

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

}
