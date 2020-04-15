package com.wisely.ch9_3_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ch935Application {

    static {
        System.setProperty("java.security.auth.login.config", "D:/kafka_client_jaas.conf");
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch935Application.class, args);
    }
}
