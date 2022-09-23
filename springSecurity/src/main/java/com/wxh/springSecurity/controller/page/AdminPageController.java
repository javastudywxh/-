package com.wxh.springSecurity.controller.page;

import com.wxh.springSecurity.service.AuthService;
import com.wxh.springSecurity.service.BookService;
import com.wxh.springSecurity.service.StatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Auther: WXH
 * @Date: 2022/9/10 - 09 - 10 - 16:27
 */
@Controller
@RequestMapping("/page/admin")
public class AdminPageController {
    @Resource
    AuthService authService;
    @Resource
    BookService bookService;
    @Resource
    StatusService statusService;
    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        model.addAttribute("user",authService.findUser(session));
        model.addAttribute("borrowDetails",bookService.getBorrowDetails());
        model.addAttribute("counts", statusService.getCounts());
        return "/admin/index";
    }
    @RequestMapping("/book")
    public String book(HttpSession session, Model model){
        model.addAttribute("user",authService.findUser(session));
        model.addAttribute("bookList",bookService.getAllBook());
        return "/admin/book";
    }
    @RequestMapping("/add")
    public String add(HttpSession session, Model model){
        model.addAttribute("user",authService.findUser(session));
        return "/admin/add-book";
    }
}
