package com.nyf.serviceacl.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nyf.serviceacl.entity.UserRole;
import com.nyf.serviceacl.mapper.UserRoleMapper;
import com.nyf.serviceacl.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
