package com.nyf.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nyf.serviceedu.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.vo.BannerQuery;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-21
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();

    void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery);

    @CacheEvict(value = "banner", allEntries=true)
    void saveBanner(CrmBanner banner);

    //CacheEvict用于更新或删除，allEntries属性清楚缓存
    @CacheEvict(value = "banner", allEntries=true)
    void updateBannerById(CrmBanner banner);

    //CacheEvict用于更新或删除，allEntries属性清楚缓存
    @CacheEvict(value = "banner", allEntries=true)
    void removeBannerById(String id);
}
