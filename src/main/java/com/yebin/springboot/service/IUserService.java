package com.yebin.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yebin.springboot.controller.dto.UserDTO;
import com.yebin.springboot.entity.User;

public interface IUserService extends IService<User> {
    boolean login(UserDTO userDTO);

}
