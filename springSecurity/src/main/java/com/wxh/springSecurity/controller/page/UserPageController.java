package com.wxh.springSecurity.controller.page;

import com.wxh.springSecurity.entity.AuthUser;
import com.wxh.springSecurity.service.AuthService;
import com.wxh.springSecurity.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Auther: WXH
 * @Date: 2022/9/10 - 09 - 10 - 16:34
 */
@Controller
@RequestMapping("/page/user")
public class UserPageController {
    @Resource
    AuthService authService;
    @Resource
    BookService bookService;
    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        model.addAttribute("user",authService.findUser(session));
        model.addAttribute("bookList",bookService.borrowList());
        return "/user/index";
    }
    @RequestMapping("/book")
    public String book(HttpSession session, Model model){
        AuthUser user=authService.findUser(session);
        model.addAttribute("user",authService.findUser(session));
        model.addAttribute("bookList",bookService.getAllBorrowBookById(user.getId()));
        return "/user/book";
    }
}
