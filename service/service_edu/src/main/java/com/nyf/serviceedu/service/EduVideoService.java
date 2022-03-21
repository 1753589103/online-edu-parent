package com.nyf.serviceedu.service;

import com.nyf.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);
}
