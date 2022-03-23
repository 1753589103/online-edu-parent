package com.nyf.serviceedu.mapper;

import com.nyf.serviceedu.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author NiuYeFan
 * @since 2022-03-22
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getCountRegister(String day);
}
