package com.yebin.springboot.config;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.yebin.springboot.controller.dto.UserDTO;


@EnableWebSecurity //给spring托管
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能也只有对应有权限的人才能访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/user/login").permitAll() //antMatchers("/")地址，.permitAll()所有人
                .antMatchers("/user/**").hasRole("vip1");
//                .antMatchers("/level2/**").hasRole("vip2")
//                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认回到登录页面，需要开启登录页面
        //login
        http.formLogin().loginPage("/user/login");
        http.rememberMe().rememberMeParameter("remember");

    }


    //认证
    //密码编码：
    //spring secutiry5.0+新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据正常应该从数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1")
                //.and()添加多个
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("xiaoming").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2");
    }
}