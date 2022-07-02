package com.yebin.springboot.config;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 跨域问题解决.cors();
 * 但是莫名奇妙接口被拦截
 */

/**
 * anyRequest          |   匹配所有请求路径
 * access              |   SpringEl表达式结果为true时可以访问
 * anonymous           |   匿名可以访问
 * denyAll             |   用户不能访问
 * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
 * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
 * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
 * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
 * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
 * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
 * permitAll           |   用户可以任意访问
 * rememberMe          |   允许通过remember-me登录的用户访问
 * authenticated       |   用户登录后可访问
 */


@EnableWebSecurity //给spring托管
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //暂时关闭SpringSecurity token 认证校验，token认证后期再添加
        http.csrf().disable().antMatcher("/user/login").anonymous();
        //首页所有人可以访问，功能也只有对应有权限的人才能访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/user/login").anonymous() //antMatchers("/")地址，.permitAll()所有人
//                .antMatchers("/user/**").hasRole("vip1")
//                .antMatchers("/level2/**").hasRole("vip2")
//                .antMatchers("/level3/**").hasRole("vip3")
                .and()
                .cors()
                .and()
                .formLogin()
                .and()
                .sessionManagement()
                // 最大会话数设置
                .maximumSessions(1)
                // 阻止新会话登录
                .maxSessionsPreventsLogin(true);


        //没有权限默认回到登录页面，需要开启登录页面
        //login
        http.formLogin().loginPage("/user");
//        http.rememberMe().rememberMeParameter("remember");

    }


    //认证
    //密码编码：
    //spring secutiry5.0+新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据正常应该从数据库中读
        System.out.println("认证");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip0")
                //.and()添加多个
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("xiaoming").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2");
    }
}