package me.emma.imageservice.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emma.imageservice.utils.S3Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping()
@Slf4j
@AllArgsConstructor
public class UploadController {

    private S3Util s3Util;

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        log.info("upload file: {}", file.getOriginalFilename());

        try {
            // get original filename
            String originalFilename = file.getOriginalFilename();
            // get file extension
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // create object name
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = s3Util.upload(file, objectName);
            return filePath;

        } catch (Exception e) {
            log.error("upload file error", e);
            return e.getMessage();
        }

    }

    @DeleteMapping("/delete/{image}")
    public ResponseEntity<String> deleteImage(@PathVariable String image) {
        log.info("delete image: {}", image);
        s3Util.deleteImage(image);
        return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
    }
}
