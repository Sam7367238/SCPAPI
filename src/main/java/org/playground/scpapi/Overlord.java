package org.playground.scpapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Overlord {
    public static void main(String[] args) {
        SpringApplication.run(Overlord.class, args);
    }
}
