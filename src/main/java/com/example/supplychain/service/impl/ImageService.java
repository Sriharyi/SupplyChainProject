package com.example.supplychain.service.impl;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ImageService {
    private String imageFolder = "E:/Intern/java/Springboot/SupplyChainProjectImages/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = imageFolder + file.getOriginalFilename();

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }
}
