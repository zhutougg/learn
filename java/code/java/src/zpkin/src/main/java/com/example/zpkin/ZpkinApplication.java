package com.example.zpkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ZpkinApplication {
    private static final Logger LOG = Logger.getLogger(ZpkinApplication.class.getName());
    public static void main(String[] args) {
        SpringApplication.run(ZpkinApplication.class, args);
    }

    @Autowired
    ServiceA serviceA;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String index(){
        LOG.log(Level.INFO, "Index API is calling");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://eurekaprovider/say","fdfdfd",String.class);
        System.out.println(responseEntity.getBody());
        return "Welcome Sleuth!";
    }





}
