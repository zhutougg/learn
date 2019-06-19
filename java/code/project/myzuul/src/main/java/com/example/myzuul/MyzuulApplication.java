package com.example.myzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableZuulProxy
public class MyzuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyzuulApplication.class, args);
    }

}
