package com.lxt.springbootdatasources.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxt.springbootdatasources.entity.User;
import com.lxt.springbootdatasources.mapper.UserMapper;
import com.lxt.springbootdatasources.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
