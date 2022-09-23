package com.wxh.springSecurity.controller.api;

import com.wxh.springSecurity.entity.AuthUser;
import com.wxh.springSecurity.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

/**
 * @Auther: WXH
 * @Date: 2022/9/12 - 09 - 12 - 14:16
 */
@Controller
@RequestMapping("/api/user")
public class UserApiController {
    @Resource
    BookService bookService;
    @RequestMapping(value = "/borrowBook",method = RequestMethod.GET)
    public String borrowBook(@RequestParam("bid") int bid,
                            @SessionAttribute("user") AuthUser user)
    {
        bookService.addBorrow(bid,user.getId());
        return "redirect:/page/user/book";
    }
    @RequestMapping(value = "/returnBook",method = RequestMethod.GET)
    public String returnBook(@RequestParam("id") int bid,
                             @SessionAttribute("user") AuthUser user)
    {
        bookService.returnBook(bid,user.getId());
        return "redirect:/page/user/index";
    }
}
