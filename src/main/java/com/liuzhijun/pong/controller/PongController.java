package com.liuzhijun.pong.controller;

import com.liuzhijun.pong.service.PongService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PongController {
    @Resource
    private PongService pongService;

    @PostMapping("/pong")
    public Mono<ResponseEntity<String>> pong() {
        if (pongService.allowRequest()) {
            return Mono.just(ResponseEntity.ok("World"));
        } else {
            return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate limit exceeded"));
        }
    }

}
