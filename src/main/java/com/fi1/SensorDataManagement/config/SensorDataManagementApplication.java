package com.fi1.SensorDataManagement.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.fi1.SensorDataManagement.*",
        "com.fi1.SensorDataManagement.controller",
        "com.fi1.SensorDataManagement.service"})
@EnableJpaRepositories(basePackages = "com.fi1.SensorDataManagement.repository")
@EntityScan({"com.fi1.SensorDataManagement.dal"})
public class SensorDataManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorDataManagementApplication.class, args);
    }

}
