package com.nyf.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nyf.ExceptionHandler.exception.MyException;
import com.nyf.serviceedu.entity.EduCourse;
import com.nyf.serviceedu.entity.EduCourseDescription;
import com.nyf.serviceedu.entity.EduVideo;
import com.nyf.serviceedu.entity.vo.CourseInfoForm;
import com.nyf.serviceedu.entity.vo.CoursePublishVo;
import com.nyf.serviceedu.entity.vo.CourseQuery;
import com.nyf.serviceedu.mapper.EduCourseMapper;
import com.nyf.serviceedu.service.EduChapterService;
import com.nyf.serviceedu.service.EduCourseDescriptionService;
import com.nyf.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nyf.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    EduCourseMapper eduCourseMapper;

    @Autowired
    EduVideoService eduVideoService;

    @Autowired
    EduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //向课程表里面添加课程基本信息
        //CourseInfoForm对象 转换成 EduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.insert(eduCourse);

        if (result<=0){//表示添加失败
            throw new MyException(20001,"添加课程信息失败");
        }

        //获取添加之后课程信息的id
        String cid = eduCourse.getId();

        //想课程简介表里面添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());

        //手动设置描述课程表的id，与上面的课程信息表id关联
        eduCourseDescription.setId(cid);

        eduCourseDescriptionService.save(eduCourseDescription);

        return cid;
    }

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        //查询简介表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //1、修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update <= 0){
            throw new MyException(20001,"修改课程信息失败");
        }

        //2、修改描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseInfoForm.getId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return eduCourseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        //取出值，判断他们是否有值
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        //判断条件值是否为空，如果不为空，拼接条件
        //判断是否有传入教师名
        if (!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title", title);//参数1：数据库字段名； 参数2：模糊查询的值
        }
        //判断是否传入状态
        if (!StringUtils.isEmpty(status)) {
            //构造条件
            wrapper.eq("status", status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(pageParam, wrapper);
    }

    @Override
    public boolean removeCourse(String id) {
        //1、根据课程id删除小节
        eduVideoService.removeVideoByCourseId(id);

        //2、根据课程id删除章节部分
        eduChapterService.removeChapterByCourseId(id);

        //3、根据课程id删除课程描述
        eduCourseDescriptionService.removeById(id);

        //4、根据课程id删除课程本身
        int result = baseMapper.deleteById(id);

        if (result>=1){
            return true;
        }else {
            throw new MyException(20001,"删除失败");
        }
    }

}
