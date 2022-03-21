package com.nyf.serviceedu.service;

import com.nyf.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
