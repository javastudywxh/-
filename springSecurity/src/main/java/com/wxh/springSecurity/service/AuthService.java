package com.wxh.springSecurity.service;

import com.wxh.springSecurity.entity.AuthUser;

import javax.servlet.http.HttpSession;

/**
 * @Auther: WXH
 * @Date: 2022/9/9 - 09 - 09 - 10:09
 */
public interface AuthService {
    void register(String name,String sex,String grade,String password);
    AuthUser findUser(HttpSession session);
}
