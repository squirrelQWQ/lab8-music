package com.example.lab8music.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class User {
    private Long ID;
    private String username;
    private String password;
}
