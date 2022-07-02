package com.yebin.springboot2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yebin.springboot2.controller.dto.UserDTO;
import com.yebin.springboot2.entity.User;

public interface IUserService extends IService<User> {
    boolean login(UserDTO userDTO);

}
