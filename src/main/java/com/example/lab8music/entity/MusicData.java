package com.example.lab8music.entity;

import lombok.Data;

@Data
public class MusicData {
    private Long ID;
    private String title;
    private byte[] data;
    private Long sort;
    private Long music_info_ID;
    private String fileName;  // 从数据库中读取data后把文件转换为.mp3格式存放在/static/music目录下，这是文件名
}
