package com.dev.ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class PhotoService {

    @Value("(upload-dir)")
    private String uploadDir;

    public String uploadPhoto(MultipartFile file) throws IOException {

        String originalName = file.getOriginalFilename();

        String extension = originalName.substring(originalName.lastIndexOf('.'));

        String newFileName = UUID.randomUUID().toString() + extension;

        Path dir = Path.of(uploadDir);
        Files.createDirectories(dir);

        Path url = dir.resolve(newFileName);

        Files.copy(file.getInputStream(), url, StandardCopyOption.REPLACE_EXISTING);

        return uploadDir + '/' + newFileName;
    }

}