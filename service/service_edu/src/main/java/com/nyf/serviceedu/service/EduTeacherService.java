package com.nyf.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nyf.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
