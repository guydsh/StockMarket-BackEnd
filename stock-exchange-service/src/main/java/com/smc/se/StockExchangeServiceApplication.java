package com.smc.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaAuditing
public class StockExchangeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockExchangeServiceApplication.class, args);
    }
}
