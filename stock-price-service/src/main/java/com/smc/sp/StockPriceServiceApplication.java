package com.smc.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class StockPriceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockPriceServiceApplication.class, args);
    }
}
