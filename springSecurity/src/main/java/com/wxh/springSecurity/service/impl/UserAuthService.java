package com.wxh.springSecurity.service.impl;

import com.wxh.springSecurity.entity.AuthUser;
import com.wxh.springSecurity.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: WXH
 * @Date: 2022/9/7 - 09 - 07 - 15:59
 */
@Service
public class UserAuthService implements UserDetailsService {

    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser user = mapper.getUserPasswordByUsername(s);  //从数据库根据用户名获取密码
        if(user == null)
            throw new UsernameNotFoundException("登录失败，用户名或密码错误！");
        return User   //这里需要返回UserDetails，SpringSecurity会根据给定的信息进行比对
                .withUsername(user.getName())
                .password(user.getPassword())   //直接从数据库取的密码
                .roles(user.getRole())   //用户角色
                .build();
    }

}
