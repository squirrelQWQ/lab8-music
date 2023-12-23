package com.example.lab8music.mapper;

import com.example.lab8music.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    public User searchByNamePassword(User user);
}
