package com.wxh.springSecurity.service;

import com.wxh.springSecurity.entity.Books;
import com.wxh.springSecurity.entity.BorrowDetails;
import com.wxh.springSecurity.entity.BorrowList;

import java.util.List;

/**
 * @Auther: WXH
 * @Date: 2022/9/11 - 09 - 11 - 14:33
 */
public interface BookService {
    List<Books> getAllBook();
    void deleteBook(int bid);
    void addBook(String title,String desc,double price);
    void addBorrow(int bid,int id);
    List<Books> borrowList();
    List<Books> getAllBorrowBookById(int id);
    void returnBook(int bid,int id);
    List<BorrowDetails> getBorrowDetails();
}
