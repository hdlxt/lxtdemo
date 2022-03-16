package com.lxt.springbootmysql01.service.impl;

import com.lxt.springbootmysql01.entity.Student;
import com.lxt.springbootmysql01.mapper.StudentMapper;
import com.lxt.springbootmysql01.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
