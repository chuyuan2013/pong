package com.liuzhijun.pong.config;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RateLimiter {

    private static Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(RateLimiter.class);

    private final int MAX_REQUESTS_PER_SECOND = 1;
    private static final AtomicInteger requestCounter = new AtomicInteger(0);


    public boolean allowRequest() {
        if (requestCounter.get() < MAX_REQUESTS_PER_SECOND) {

            //doSomething();

            requestCounter.incrementAndGet();
            return true;
        }else{
            log.info("Pong service rate limited");
        }
        return false;
    }

    @Scheduled(fixedRate = 1000) // 每秒重置
    public void resetCount() {
        if(requestCounter.get() == 1 ){
            logger.info("requestCounter被重置前的值{}",requestCounter.get());
            requestCounter.set(0);
            logger.info("requestCounter被重置后的值{}",requestCounter.get());
        }

    }
}
