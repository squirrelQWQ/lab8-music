package com.example.lab8music.service;

import com.example.lab8music.entity.MusicInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MusicInfoService {
    List<MusicInfo> getAllMusic();

    Boolean deleteMusicById(Long ID);
    MusicInfo getMusicById(Long ID);

    boolean updateMusicById(MusicInfo musicInfo);

    List<MusicInfo> getMusicByTitle(String keyword);

    boolean addMusicInfo(MusicInfo musicInfo);

    List<MusicInfo> getPaginatedData(int start, int size);
    int getMusicInfoCount();
}
