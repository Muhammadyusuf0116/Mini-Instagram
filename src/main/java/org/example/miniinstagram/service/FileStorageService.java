package org.example.miniinstagram.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String saveImage(MultipartFile image) throws IOException;

    void deleteImage(String image) throws IOException;

}
