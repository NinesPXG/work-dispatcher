package com.pxg.demo;

import com.pxg.dispatcher.client.EnableRmc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRmc
@SpringBootApplication
@MapperScan(basePackages = "com.pxg.demo.infrastructure.repository.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
