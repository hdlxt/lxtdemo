package com.lxt.springbootmysql01.controller;


import com.lxt.springbootmysql01.entity.User;
import com.lxt.springbootmysql01.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private IUserService userService;

    @GetMapping("/write")
    public boolean write(){
        return userService.save(User.builder().name("234").build());
    }

    @GetMapping("/read")
    public User read(){
        return userService.getById(1);
    }
}

