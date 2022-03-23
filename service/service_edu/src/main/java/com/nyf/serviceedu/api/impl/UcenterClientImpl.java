package com.nyf.serviceedu.api.impl;

import com.nyf.serviceedu.api.UcenterClient;
import com.nyf.serviceedu.entity.vo.UcenterMemberVo;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberVo getMemberInfoById(String memberId) {
        return null;
    }
}
