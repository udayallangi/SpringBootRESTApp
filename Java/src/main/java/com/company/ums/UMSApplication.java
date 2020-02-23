package com.company.ums;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAutoConfiguration
public class UMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(UMSApplication.class, args);
    }
    
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor threadExec = new ThreadPoolTaskExecutor();
        threadExec.setCorePoolSize(4);
        threadExec.setMaxPoolSize(6);
        threadExec.setThreadNamePrefix("ImgurREST-");
        threadExec.setQueueCapacity(200);
        threadExec.initialize();
        return threadExec;
    }
}
