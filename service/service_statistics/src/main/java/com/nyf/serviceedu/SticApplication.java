package com.nyf.serviceedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.nyf.serviceedu.mapper")
@EnableDiscoveryClient //开启服务发现
@ComponentScan("com.nyf")
public class SticApplication {

        public static void main(String[] args) {
            SpringApplication.run(SticApplication.class,args);
        }
}
