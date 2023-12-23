package com.example.lab8music.service;

import com.example.lab8music.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    public boolean searchByNamePassword(User user);
}
