package com.wxh.springSecurity.service.impl;

import com.wxh.springSecurity.entity.Status;
import com.wxh.springSecurity.mapper.BookMapper;
import com.wxh.springSecurity.mapper.UserMapper;
import com.wxh.springSecurity.service.StatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: WXH
 * @Date: 2022/9/12 - 09 - 12 - 17:40
 */
@Service
public class StatusServiceImpl implements StatusService {
    @Resource
    BookMapper bookMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public Status getCounts() {
        return new Status(userMapper.getStudentCount(),bookMapper.getBookCount(),bookMapper.getBorrowCount());
    }
}
