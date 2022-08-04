package com.example.springboot_mongodb_demo.service;

import com.example.springboot_mongodb_demo.collection.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    String savePhoto(String originalFilename, MultipartFile image) throws IOException;

    Photo getPhoto(String id);
}
