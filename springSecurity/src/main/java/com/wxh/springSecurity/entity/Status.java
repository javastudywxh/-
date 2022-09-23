package com.wxh.springSecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: WXH
 * @Date: 2022/9/12 - 09 - 12 - 17:39
 */
@Data
@AllArgsConstructor
public class Status {
    int userCount;
    int bookCount;
    int borrowCount;
}
