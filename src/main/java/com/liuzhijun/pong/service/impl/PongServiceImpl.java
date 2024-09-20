package com.liuzhijun.pong.service.impl;

import com.liuzhijun.pong.config.RateLimiter;
import com.liuzhijun.pong.service.PongService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PongServiceImpl implements PongService {

    @Resource
    private RateLimiter rateLimiter;

    @Override
    public boolean allowRequest() {
        return rateLimiter.allowRequest();
    }
}
