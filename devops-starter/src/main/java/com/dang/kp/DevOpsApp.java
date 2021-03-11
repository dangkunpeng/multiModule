package com.dang.kp;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class DevOpsApp {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        SpringApplication.run(DevOpsApp.class, args);
        log.info("APP start in {} SECONDS", stopwatch.elapsed(TimeUnit.SECONDS));
    }
}
