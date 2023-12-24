package com.example.lab8music.mapper;

import com.example.lab8music.entity.MusicData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MusicDataMapper {
    public int uploadMusic(MusicData musicData);

    List<MusicData> getRandomMusicList(long length);
    int deleteMusicDataById(Long ID);
}
