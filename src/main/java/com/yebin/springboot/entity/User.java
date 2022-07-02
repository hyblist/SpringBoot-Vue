//entity 英 [ˈentəti] 实体
package com.yebin.springboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yebin.springboot.controller.dto.UserDTO;
import lombok.Data;

//实体类。get/set
@Data
@TableName(value = "sys_user")  //mybatis-plus不表面数据库表会报错
public class User extends UserDTO {
    @TableId(type = IdType.AUTO) //mybatis-plus指名为主键，IdType.AUTO：自动增加，可以看官方文档
    private Integer id;
    private String username;
    @JsonIgnore //忽略某个字段不给前端展示
    private String password;
    private String nickname;
    private String email;
    private String phone;
//    @TableField(value = "address") //mybatis-plus可以绑定数据库字段名，然后下面的变量名可以随意改
    private String address; //mybatis-plus可以绑定后，可以随意改
}


