package com.example.zpkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@RestController
public class ZpkinserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZpkinserverApplication.class, args);
    }

    @RequestMapping("/test")
    public String index(){
        return "ZpkinserverApplication";
    }

}
