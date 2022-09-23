package com.wxh.springSecurity.controller.api;

import com.wxh.springSecurity.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Auther: WXH
 * @Date: 2022/9/8 - 09 - 08 - 22:22
 */
@Controller
@RequestMapping("/api/auth")
public class RegisterController {
    @Resource
    AuthService authService;
    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public String register(@RequestParam("username") String name,@RequestParam("sex") String sex,@RequestParam("grade") String grade,@RequestParam("password") String password){
        authService.register(name, sex, grade, password);
        return "redirect:/login";
    }
}
