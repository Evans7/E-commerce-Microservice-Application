//package me.emma.imageservice.consumers;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import me.emma.imageservice.utils.S3Util;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.function.Consumer;
//
//@Configuration
//@Slf4j
//@AllArgsConstructor
//public class Function {
//
//    private final S3Util util;
//
//    @Bean
//    public Consumer<String> deleteImageEventConsumer() {
//        log.info("deleteImageEventConsumer bean registered");
//        return (String image) -> {
//            log.info("received image url: " + image);
//            util.deleteImage(image);
//            log.info("Deleted image from image service: " + image);
//        };
//    }
//}
