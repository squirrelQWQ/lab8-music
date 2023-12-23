package com.example.lab8music.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import org.springframework.util.FileSystemUtils;
/**
 * 提供在target中的static目录下文件操作
 * */
public class StaticFileUtil {

    /**
     * 删除指定文件夹下的所有内容（包括子文件夹和文件）。
     *
     * @param folderPath 要删除内容的文件夹路径
     */
    public static void deleteFolderContents(String folderPath) {
        HashMap<String , Integer> fileCounts = new HashMap<>();
        fileCounts.put("mp3文件",getMp3FileCount(folderPath));
        fileCounts.put("其它文件",getNonMp3FileCount(folderPath));

        System.out.println(folderPath + "目录下共有:\n\t" + fileCounts.get("mp3文件") + "个mp3文件，\n\t" + fileCounts.get("其它文件") + "个其它文件");
    }

    /***
     * 获取 mp3文件数目
     */
    private static int getMp3FileCount(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));

            if (files != null) {
                return files.length;
            } else {
                System.err.println("无法获取文件列表");
            }
        } else {
            System.err.println("文件夹不存在或不是一个目录");
        }

        return 0;
    }

/***
 * 获取非 mp3文件数目
 */
    private static int getNonMp3FileCount(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> !name.toLowerCase().endsWith(".mp3"));

            if (files != null) {
                return files.length;
            } else {
                System.err.println("无法获取文件列表");
            }
        } else {
            System.err.println("文件夹不存在或不是一个目录");
        }
        return 0;
    }

}
