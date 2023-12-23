package com.example.lab8music.service;

import com.example.lab8music.entity.MusicData;
import com.example.lab8music.entity.MusicInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MusicDataService {
    boolean uploadMusic(MusicData musicData);

    List<MusicData> getRandomMusicList(long length);
}
