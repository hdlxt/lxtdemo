package com.lxt.springbootmysql01.mapper;

import com.lxt.springbootmysql01.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-03-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
