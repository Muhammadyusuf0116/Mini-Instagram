package org.example.miniinstagram.service.serviceImpl;

import org.example.miniinstagram.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String saveImage(MultipartFile image) throws IOException {

        String originalName = image.getOriginalFilename();

        if (originalName == null || originalName.isEmpty()) {
            throw new RuntimeException("Invalid image");
        }

        String imageFormat =
                originalName
                        .substring(originalName.lastIndexOf("."))
                        .toLowerCase();

        if (!imageFormat.equals(".png")
                && !imageFormat.equals(".jpg")
                && !imageFormat.equals(".jpeg")) {

            throw new RuntimeException("Invalid image format");
        }

        String uniqueName =
                UUID.randomUUID() + imageFormat;

        Path path =
                Paths.get(uploadPath, uniqueName);

        Files.createDirectories(path.getParent());

        image.transferTo(path);

        return uniqueName;
    }

    @Override
    public void deleteImage(String image) throws IOException {

        Path path = Paths.get(uploadPath, image);

        Files.deleteIfExists(path);
    }
}
