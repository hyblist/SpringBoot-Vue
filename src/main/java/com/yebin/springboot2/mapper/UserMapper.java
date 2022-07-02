package com.yebin.springboot2.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yebin.springboot2.entity.User;
//Mapper专门用来与数据库交互的
//@Mapper //把UserMapper，注入bean,引入mybatis-plus后更换配置注解位置
public interface UserMapper extends BaseMapper<User> {
}
