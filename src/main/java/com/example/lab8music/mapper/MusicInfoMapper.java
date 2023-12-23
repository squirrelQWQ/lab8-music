package com.example.lab8music.mapper;

import com.example.lab8music.entity.MusicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MusicInfoMapper {
    public List<MusicInfo> getAllMusic();
    public int deleteMusicById(Long ID);

    public MusicInfo getMusicById(Long ID);

    public int updateMusicById(MusicInfo musicInfo);

    List<MusicInfo> getMusicByTitle(String keyword);
    public int addMusicInfo(MusicInfo musicInfo);

    List<MusicInfo> getPaginatedData(int start, int size);
    int getMusicInfoCount();
}
