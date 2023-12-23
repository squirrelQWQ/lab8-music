package com.example.lab8music.service.impl;

import com.example.lab8music.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    @Autowired
    private MusicInfoService musicInfoService;

    @GetMapping("/searchPage")
    public String getPaginatedData(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                   Model model) {
        Page<YourEntity> yourEntities = yourService.getPaginatedData(page, size);

        model.addAttribute("yourEntities", yourEntities.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", yourEntities.getTotalPages());

        return "yourTemplate";
    }
}
