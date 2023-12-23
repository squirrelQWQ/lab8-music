package com.example.lab8music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这个controller进行页面的跳转
 *
 */
@Controller
@RequestMapping("/jump")
public class jumpController {
    @GetMapping("/toMusicPlay")
    public String toMusicPlay() {

        return "musicPlay";
    }

    @GetMapping("/toAdminLogin")
    public String toAdminLogin() {
        return "adminLogin";
    }

    @GetMapping("/toMusicUpload")
    public String toMusicUpload() {
        return "musicUpload";
    }

    @GetMapping("/toTest")
    public String toTest(){
        return "test";
    }

    @GetMapping("/toMusicSearch")
    public String toMusicSearch(@ModelAttribute("UploadResult") String UploadResult , Model model){
//        先获取上传的结果
        model.addAttribute("UploadResult" , UploadResult);
//        进行查询操作，以便在musicSearch页面进行展示，免得查询界面太单调

        return "musicSearch";
    }
}