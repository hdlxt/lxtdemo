package com.lxt.springbootdatasources.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxt.springbootdatasources.entity.Role;
import com.lxt.springbootdatasources.mapper.RoleMapper;
import com.lxt.springbootdatasources.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b)-> a.get(0) + a.get(1) - b.get(0) - b.get(1));
    }

}
