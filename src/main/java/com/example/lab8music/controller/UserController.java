package com.example.lab8music.controller;

import com.example.lab8music.entity.User;
import com.example.lab8music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    @Autowired
    private User user;
    @Autowired
    private UserService userService;
    @GetMapping("/adminLogin")
    public String adminLogin(@RequestParam("username") String username ,
                             @RequestParam("password") String password){
        user.setUsername(username);
        user.setPassword(password);

        if (userService.searchByNamePassword(user)){
            System.out.println("查询成功，予以登录");
        }
        else{
            System.out.println("查询失败，不准登录");
            return "adminLogin";
        }
        System.out.println(user);

        // 返回响应
        return "musicSearch";
    }
}

