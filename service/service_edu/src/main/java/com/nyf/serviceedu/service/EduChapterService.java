package com.nyf.serviceedu.service;

import com.nyf.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String id);
}
