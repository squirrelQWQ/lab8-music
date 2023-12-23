package com.example.lab8music.service.impl;

import com.example.lab8music.entity.User;
import com.example.lab8music.mapper.UserMapper;
import com.example.lab8music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean searchByNamePassword(User user) {
        User user1 = userMapper.searchByNamePassword(user);
        return user1 != null;
    }
}
