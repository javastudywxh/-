package com.wxh.springSecurity.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: WXH
 * @Date: 2022/9/12 - 09 - 12 - 16:56
 */
@Data
public class BorrowDetails {
    int id;
    String book_title;
    String user_name;
    Date time;
}
