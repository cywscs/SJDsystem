package com.example.sjsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SjsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SjsystemApplication.class, args);
    }

}
