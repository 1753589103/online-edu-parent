package com.nyf.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nyf.serviceedu.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.vo.BannerQuery;

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
}
