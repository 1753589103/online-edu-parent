package com.nyf.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "com.nyf")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
