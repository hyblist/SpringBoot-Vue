package com.yebin.springboot.service.iml;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yebin.springboot.controller.dto.UserDTO;
import com.yebin.springboot.entity.User;
import com.yebin.springboot.mapper.UserMapper;
import com.yebin.springboot.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

    private static final Log LOG = Log.get();
    @Override
    public boolean login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        try{
            UserDTO one = getOne(queryWrapper);
            return one != null;
        }catch (Exception e){
            LOG.error(e);
            return false;
        }

    }
}
