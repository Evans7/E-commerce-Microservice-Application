package me.emma.productservice.feign;


import me.emma.productservice.config.FeignMultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "image-service", configuration = FeignMultipartSupportConfig.class)
public interface ImageClient {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart("file") MultipartFile file);

    @DeleteMapping(value = "images/delete/{image}")
    String deleteImage(@PathVariable String image);

}
