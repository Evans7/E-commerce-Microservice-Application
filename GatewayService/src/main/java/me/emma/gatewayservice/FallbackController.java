package me.emma.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public Flux<String> fallbackMethod() {
        return Flux.just("The service is not running properly. Please try" +
                "again later");
    }
}
