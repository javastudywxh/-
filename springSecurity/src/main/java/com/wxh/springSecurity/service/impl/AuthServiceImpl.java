package com.wxh.springSecurity.service.impl;

import com.wxh.springSecurity.entity.AuthUser;
import com.wxh.springSecurity.mapper.UserMapper;
import com.wxh.springSecurity.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Auther: WXH
 * @Date: 2022/9/9 - 09 - 09 - 10:09
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    UserMapper mapper;
    @Transactional
    @Override
    public void register(String name, String sex, String grade, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AuthUser user=new AuthUser(0,name,encoder.encode(password),"user");
        if(mapper.register(user)<=0){
            throw new RuntimeException("用户基本信息保存失败");
        }
        if (mapper.addStudent(user.getId(),name,sex,grade)<=0){
            throw new RuntimeException("学生基本信息保存失败");
        }

    }

    @Override
    public AuthUser findUser(HttpSession session) {
        AuthUser user=(AuthUser) session.getAttribute("user");
        if (user==null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            mapper.getUserPasswordByUsername(authentication.getName());
            session.setAttribute("user",user);
        }
        return user;
    }
}
