package com.example.lab8music.service.impl;

import com.example.lab8music.entity.MusicData;
import com.example.lab8music.mapper.MusicDataMapper;
import com.example.lab8music.service.MusicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MusicDataServiceImpl implements MusicDataService {
    @Autowired
    MusicDataMapper musicDataMapper;
    @Override
    public boolean uploadMusic(MusicData musicData) {
        return musicDataMapper.uploadMusic(musicData) > 0;
    }

//    随机查找10条音乐数据
    @Override
    public List<MusicData> getRandomMusicList(long length) {

        return musicDataMapper.getRandomMusicList(length);
    }
}
