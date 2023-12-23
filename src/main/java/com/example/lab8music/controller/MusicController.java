package com.example.lab8music.controller;

import com.example.lab8music.entity.MusicInfo;
import com.example.lab8music.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MusicController {
    @Autowired
    private MusicInfoService musicService;


//    按照歌名搜索
    @GetMapping("/MusicSearchByTitle")
    public String MusicSearch(@RequestParam("keyword") String keyword,Model model){
        List<MusicInfo> musicInfo = musicService.getMusicByTitle(keyword);
//        若查询不到歌曲就返回一个消息，若查询到了则在musicInfo中返回歌曲信息
        if(musicInfo.isEmpty()){
            model.addAttribute("searchByTitleResult","没有找到该歌曲");
            model.addAttribute("showTable",false);  // 没查到信息就不显示table
        }else{
            model.addAttribute("showTable",true);  //查到信息就显示table
        }

        model.addAttribute("musicInfo",musicInfo);
        return "musicSearch";
    }

//    搜索所有歌曲信息
    @GetMapping("/MusicSearchAll")
    public String showMusicInfoPage(Model model) {
        List<MusicInfo> musicInfoList = musicService.getAllMusic();
        model.addAttribute("musicInfoList", musicInfoList);
        return "musicList";
    }

    @PostMapping("/MusicDelete")
    public String deleteMusicInfo(@RequestParam Long id,
                                  @RequestParam String title,
                                  Model model) {
        if (musicService.deleteMusicById(id)) {
            model.addAttribute("DeleteResult","音乐: 《"+ title+"》 删除成功！");
        } else {
            model.addAttribute("DeleteResult","音乐: 《"+title+"》 删除失败！");
        }
        return "musicSearch";
    }

    @GetMapping("/MusicEdit/{ID}")
    public String editMusicInfo(@PathVariable Long ID, Model model) {
        MusicInfo musicInfo = musicService.getMusicById(ID);
        System.out.println(musicInfo);
        model.addAttribute("musicInfo", musicInfo); //将查询结果发给前端
        return "musicUpdate";
    }
    @PostMapping("/MusicUpdate")
    public String updateMusicInfo(  @RequestParam("ID") Long ID,
                                    @RequestParam("title") String title,
                                    @RequestParam("artist") String artist,
                                    @RequestParam("album") String album,
                                    Model model) {

        MusicInfo musicInfo = musicService.getMusicById(ID);
        System.out.println(musicInfo);
        musicInfo.setTitle(title);
        musicInfo.setArtist(artist);
        musicInfo.setAlbum(album);
        System.out.println(musicInfo);

        if (musicService.updateMusicById(musicInfo)) {
            // If update is successful, add a success message attribute
            model.addAttribute("UpdateResult","音乐: 《"+ title+"》 更新成功！");
        } else {
            // If update fails, add an error message attribute
            model.addAttribute("UpdateResult","音乐: 《"+title+"》 更新失败！");
        }
        return "musicSearch";
    }

}







