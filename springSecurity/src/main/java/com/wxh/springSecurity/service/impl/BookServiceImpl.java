package com.wxh.springSecurity.service.impl;

import com.wxh.springSecurity.entity.Books;
import com.wxh.springSecurity.entity.BorrowDetails;
import com.wxh.springSecurity.entity.BorrowList;
import com.wxh.springSecurity.mapper.BookMapper;
import com.wxh.springSecurity.mapper.UserMapper;
import com.wxh.springSecurity.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: WXH
 * @Date: 2022/9/11 - 09 - 11 - 14:33
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper mapper;
    @Resource
    UserMapper userMapper;
    @Override
    public List<Books> getAllBook() {
        return mapper.getAllBook();
    }

    @Override
    public void deleteBook(int bid) {
        mapper.deleteBook(bid);
    }

    @Override
    public void addBook(String title,String desc,double price) {
        mapper.addBook(title,desc,price);
    }

    @Override
    public void addBorrow(int bid, int id) {
        Integer sid = userMapper.getSidByUid(id);
        if (sid==null) return;
        mapper.addBorrow(bid,sid);
    }

    @Override
    public List<Books> borrowList() {
        List<Books> books=mapper.getAllBook();
        List<Integer> borrows=mapper.borrowList()
                .stream()
                .map(BorrowList::getBid)
                .collect(Collectors.toList());
        return books
                .stream()
                .filter(book->!borrows.contains(book.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Books> getAllBorrowBookById(int id) {
        Integer sid=userMapper.getSidByUid(id);
        if (sid==null) return Collections.emptyList();
        return mapper.borrowListBySid(sid)
                .stream()
                .map(borrow-> mapper.getBookById(borrow.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public void returnBook(int bid, int id) {
        Integer sid = userMapper.getSidByUid(id);
        if (sid==null) return;
        mapper.deleteBorrow(bid,sid);
    }

    @Override
    public List<BorrowDetails> getBorrowDetails() {
        return mapper.borrowDetails();
    }
}
