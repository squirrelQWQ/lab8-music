package com.example.lab8music.controller;

import com.example.lab8music.entity.MusicData;
import com.example.lab8music.entity.MusicInfo;
import com.example.lab8music.service.MusicDataService;
import com.example.lab8music.service.MusicInfoService;
import com.example.lab8music.utils.DeleteMp3Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MusicDataController {
    @Autowired
    private MusicDataService musicDataService;
    @Autowired
    private MusicInfoService musicInfoService;

//    上传MP3音乐文件
    @PostMapping("/MusicUpload")
    public String uploadMusic(@RequestPart("file") MultipartFile file ,
                              @RequestPart("title") String title,
                              @RequestPart("artist") String artist,
                              RedirectAttributes redirectAttributes){
//        对用户上传的文件进行判断
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("UploadResult", "文件为空");
            return "redirect:/jump/toMusicSearch";
        }
        if(file.getSize() > 1024*1024*20){
            redirectAttributes.addFlashAttribute("UploadResult", "文件大小超过20M");
            return "redirect:/jump/toMusicSearch";
        }
        if(!file.getOriginalFilename().endsWith(".mp3")){
            redirectAttributes.addFlashAttribute("UploadResult", "文件格式不正确");
            return "redirect:/jump/toMusicSearch";
        }

        String fileName = file.getOriginalFilename();
        MusicData musicData = new MusicData();
        musicData.setTitle(fileName);

        try {
            musicData.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        /获取当前时间戳
        long currentTimeMillis = System.currentTimeMillis();

        // 创建Timestamp对象
        Timestamp gmt_created = new Timestamp(currentTimeMillis);
        Timestamp gmt_modified = new Timestamp(currentTimeMillis);
        MusicInfo musicInfo = new MusicInfo();
        musicInfo.setTitle(title);
        musicInfo.setArtist(artist);
        musicInfo.setGmt_created(gmt_created);
        musicInfo.setGmt_modified(gmt_modified);

//        这里两个插入操作因该设置为事务，先写着，以后再改
        if(musicDataService.uploadMusic(musicData)){
            musicInfo.setMusic_data_id(musicData.getID());
            if(musicInfoService.addMusicInfo(musicInfo)){
                redirectAttributes.addFlashAttribute("UploadResult", title+"文件上传成功");
            }
        }
        else{
            redirectAttributes.addFlashAttribute("UploadResult", title+"文件上传失败");
        }

        return "redirect:/jump/toMusicSearch";
    }

    @GetMapping("/musicPlay")
    public String musicPlay(Model model) throws IOException {

        long length = 5;
        List<MusicData> musicDataList = musicDataService.getRandomMusicList(length);
        // 获取静态资源目录的 Resource
        Resource staticResource = new ClassPathResource("static/music");
        Path musicFolderPath = Paths.get(staticResource.getURI());
        // 删除.mp3文件
        DeleteMp3Files.deleteMp3Files(musicFolderPath);

        // 设置文件名并保存到 static/music 目录下
        for (MusicData musicData : musicDataList) {
            String fileName = UUID.randomUUID().toString() + ".mp3";    //随机生成一个文件名
            musicData.setFileName(fileName);

            try {
                // 创建目标文件路径
                File targetFile = new File(staticResource.getFile(), fileName);
                // 写入文件内容
                try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                    fos.write(musicData.getData());
                }
                System.out.println("File "+fileName+" created successfully.");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to create "+fileName+" file.");
            }
        }

        ArrayList<String> pathList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<Integer>  countList = new ArrayList<>();
        for (MusicData musicData : musicDataList) {
            countList.add(pathList.size());
            pathList.add("/music/"+musicData.getFileName());
            titleList.add(musicData.getFileName());
        }

        model.addAttribute("countList",countList);
        model.addAttribute("pathList", pathList);
        model.addAttribute("titleList",titleList);

        return "musicPlay";
    }

}
