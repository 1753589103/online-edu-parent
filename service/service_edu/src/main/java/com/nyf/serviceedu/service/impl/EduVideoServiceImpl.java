package com.nyf.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nyf.serviceedu.entity.EduVideo;
import com.nyf.serviceedu.mapper.EduVideoMapper;
import com.nyf.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void removeVideoByCourseId(String id) {
        //根据课程id删除小节
        // TODO 删除小节，要删除对应的视频文件
            QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
            wrapper.eq("course_id",id);
            baseMapper.delete(wrapper);

    }
}
