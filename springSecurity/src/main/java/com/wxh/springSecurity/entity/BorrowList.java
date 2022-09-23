package com.wxh.springSecurity.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: WXH
 * @Date: 2022/9/12 - 09 - 12 - 14:39
 */
@Data
public class BorrowList {
    int id;
    int bid;
    int sid;
    Date date;
}
