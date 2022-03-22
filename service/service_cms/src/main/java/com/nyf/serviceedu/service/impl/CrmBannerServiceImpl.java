package com.nyf.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nyf.serviceedu.entity.CrmBanner;
import com.nyf.serviceedu.entity.vo.BannerQuery;
import com.nyf.serviceedu.mapper.CrmBannerMapper;
import com.nyf.serviceedu.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-21
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> getAllBanner() {
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
    //多条件带分页查询
    @Override
    public void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery) {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();

        if (bannerQuery!=null){
            String name = bannerQuery.getName();
            String begin = bannerQuery.getBegin();
            String end = bannerQuery.getEnd();

            if (!StringUtils.isEmpty(name)){
                wrapper.like("title",name);
            }
            if (!StringUtils.isEmpty(begin)){
                wrapper.ge("gmt_create",begin);
            }
            if (!StringUtils.isEmpty(end)){
                wrapper.le("gmt_modified",end);
            }
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(bannerPage, wrapper);
    }

}
