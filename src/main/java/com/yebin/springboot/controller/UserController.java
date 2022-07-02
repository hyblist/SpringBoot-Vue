package com.yebin.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yebin.springboot.controller.dto.UserDTO;
import com.yebin.springboot.entity.User;
import com.yebin.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")//统一加/user，如
public class UserController {


    @Resource
    private IUserService userService;

    //查询所有数据
    @GetMapping
    private List<User> findAll() {
        return userService.list();
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) ){
            return false;
        }
        return userService.login(userDTO);
    }


    @PostMapping    //post请求
    private boolean save(@RequestBody User user) {  //@RequestBody可以把前端传来的对象转成java对象
        //新增或更新
        return userService.saveOrUpdate(user);
    }

    //删除
    @DeleteMapping("/{id}")
    private boolean delete(@PathVariable Integer id) {  //@PathVariable接收id
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping ("/del/batch")
    private boolean deleteBatch(@RequestBody List<Integer> ids) {  //@RequestBody接收数组
        System.out.println(ids);
        return userService.removeByIds(ids);
    }

    //mybatis-plus分页查询
    @GetMapping("/page")
    public IPage<User> findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "") String username,//(defaultValue = "")设置默认值
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String address
            ){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)){//if判断空，解决数据库null数据问题
            queryWrapper.like("username",username);
        }
        if (!"".equals(username)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(username)){
            queryWrapper.like("address",address);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }

    // 导出--->建议看官网
    public void export(HttpServletResponse response) throws Exception{
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        //
    }


}
















