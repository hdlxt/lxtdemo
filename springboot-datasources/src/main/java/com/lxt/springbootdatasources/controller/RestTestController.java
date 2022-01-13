package com.lxt.springbootdatasources.controller;


import com.lxt.springbootdatasources.entity.Role;
import com.lxt.springbootdatasources.entity.User;
import com.lxt.springbootdatasources.service.RoleService;
import com.lxt.springbootdatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RestTestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("user")
    public boolean testUser(){
        User user = new User();
        user.setName("user1");
        return userService.save(user);
    }
    @RequestMapping("role")
    public boolean testRole(){
        Role role = new Role();
        role.setName("role");
        return roleService.save(role);
    }
    @RequestMapping("userRole")
    public boolean testUserRole(){
        User user = new User();
        user.setName("user1");
        userService.save(user);
        roleService.testTr();
        return true;
    }
}
