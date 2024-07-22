package me.emma.imageservice.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.imageservice.utils.S3Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/upload")
@Slf4j
@AllArgsConstructor
public class UploadController {

    private S3Util s3Util;

    @PostMapping
    public ResponseEntity<String> upload(MultipartFile file) {
        log.info("upload file: {}", file.getOriginalFilename());

        try {
            // get original filename
            String originalFilename = file.getOriginalFilename();
            // get file extension
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // create object name
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = s3Util.upload(file, objectName);
            return new ResponseEntity<>(filePath, HttpStatus.OK);

        } catch (Exception e) {
            log.error("upload file error", e);
            return new ResponseEntity<>("Failed to upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
