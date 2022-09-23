package com.wxh.springSecurity.controller.api;

import com.wxh.springSecurity.entity.Books;
import com.wxh.springSecurity.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Auther: WXH
 * @Date: 2022/9/11 - 09 - 11 - 16:16
 */
@Controller
@RequestMapping("/api/admin")
public class AdminApiController {
    @Resource
    BookService bookService;
    @RequestMapping(value = "/deleteBook",method = RequestMethod.GET)
    public String deleteBook(@RequestParam("bid") int bid){
        bookService.deleteBook(bid);
        return "redirect:/page/admin/book";
    }
    @RequestMapping(value = "/addBook",method = RequestMethod.POST)
    public String addBook(@RequestParam("title") String title,@RequestParam("desc") String desc,@RequestParam("price") double price){
        bookService.addBook(title,desc,price);
        return "redirect:/page/admin/book";
    }
}
