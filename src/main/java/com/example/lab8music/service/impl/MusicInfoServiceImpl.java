package com.example.lab8music.service.impl;

import com.example.lab8music.entity.MusicInfo;
import com.example.lab8music.mapper.MusicInfoMapper;
import com.example.lab8music.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicInfoServiceImpl implements MusicInfoService {

    @Autowired
    MusicInfoMapper musicInfoMapper;
    @Override
    public List<MusicInfo> getAllMusic() {
        return musicInfoMapper.getAllMusic();
    }

    @Override
    public Boolean deleteMusicById(Long ID) {
        return musicInfoMapper.deleteMusicById(ID) > 0;
    }

    @Override
    public MusicInfo getMusicById(Long ID) {
        return musicInfoMapper.getMusicById(ID);
    }

    @Override
    public boolean updateMusicById(MusicInfo musicInfo) {
        return musicInfoMapper.updateMusicById(musicInfo) > 0;
    }

    @Override
    public List<MusicInfo> getMusicByTitle(String keyword) {
        return musicInfoMapper.getMusicByTitle(keyword);
    }

    @Override
    public boolean addMusicInfo(MusicInfo musicInfo) {
        return musicInfoMapper.addMusicInfo(musicInfo) > 0;
    }

    @Override
    public List<MusicInfo> getPaginatedData(int start, int size) {
        return musicInfoMapper.getPaginatedData(start, size);
    }

    @Override
    public int getMusicInfoCount() {
        return musicInfoMapper.getMusicInfoCount();
    }


}
