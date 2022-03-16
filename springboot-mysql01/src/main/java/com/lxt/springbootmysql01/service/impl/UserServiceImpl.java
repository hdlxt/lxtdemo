package com.lxt.springbootmysql01.service.impl;

import com.lxt.springbootmysql01.entity.User;
import com.lxt.springbootmysql01.mapper.UserMapper;
import com.lxt.springbootmysql01.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
