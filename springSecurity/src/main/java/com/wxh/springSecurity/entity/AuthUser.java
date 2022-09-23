package com.wxh.springSecurity.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: WXH
 * @Date: 2022/9/8 - 09 - 08 - 11:02
 */
@Data
@AllArgsConstructor
public class AuthUser {
    int id;
    String name;
    String password;
    String role;
}
