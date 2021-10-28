package com.andrei.stepantcev.messenger;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MessengerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MessengerApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(100);
    }
}