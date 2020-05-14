package com.smc.sector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@SpringBootApplication
@EnableJpaAuditing
public class SectorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SectorServiceApplication.class, args);
    }
}
