package com.nyf.serviceedu.service;

import com.nyf.serviceedu.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-23
 */
public interface TOrderService extends IService<TOrder> {

    String createOrders(String courseId, String memberId);
}
