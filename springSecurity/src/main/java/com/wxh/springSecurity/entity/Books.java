package com.wxh.springSecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: WXH
 * @Date: 2022/9/11 - 09 - 11 - 14:48
 */
@Data
@AllArgsConstructor
public class Books {
    int bid;
    String title;
    String desc;
    double price;
}
