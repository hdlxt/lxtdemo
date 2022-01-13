package com.lxt.springbootdatasources.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxt.springbootdatasources.entity.Role;
import com.lxt.springbootdatasources.mapper.RoleMapper;
import com.lxt.springbootdatasources.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@DS("slave1")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Transactional
    @Override
    public void testTr(){
        Role role = new Role();
        role.setName("role1");
        this.baseMapper.insert(role);
        int i = 1/0;
        Role role1 = new Role();
        role1.setName("role2");
        this.baseMapper.insert(role1);
    }
}
