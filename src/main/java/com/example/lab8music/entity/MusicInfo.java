package com.example.lab8music.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Data
public class MusicInfo {
    private Long ID;
    private String title;
    private String artist;
    private String album;
    private Long sort;
    private Long status;
    private Timestamp gmt_created;      //字段创建时间
    private Timestamp gmt_modified;    //最近修改时间
    private String link_status;
    private Long music_data_id;
}
