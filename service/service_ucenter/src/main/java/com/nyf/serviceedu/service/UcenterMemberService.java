package com.nyf.serviceedu.service;

import com.nyf.serviceedu.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nyf.serviceedu.entity.vo.LoginVo;
import com.nyf.serviceedu.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);
}
