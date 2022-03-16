package com.lxt.springbootmysql01.controller;


import com.lxt.springbootmysql01.entity.User;
import com.lxt.springbootmysql01.service.IUserService;
import com.lxt.springbootmysql01.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-03-16
 */
@Controller
@RequestMapping("/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {



}

