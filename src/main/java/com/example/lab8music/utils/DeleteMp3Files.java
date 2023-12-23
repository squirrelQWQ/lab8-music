package com.example.lab8music.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Component
public class DeleteMp3Files {

    public static void deleteMp3Files(Path folderPath) throws IOException {
        Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().toLowerCase().endsWith(".mp3")) {
                    Files.delete(file);
                    System.out.println("成功删除文件: " + file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                // 处理访问文件失败的情况
                System.err.println("访问文件失败: " + file);
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("删除.mp3文件完成！");
    }
}
