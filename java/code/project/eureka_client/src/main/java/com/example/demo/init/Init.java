package com.example.demo.init;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/30 15:25
 **/
@Configuration
public class Init {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){ return new RestTemplate(); }

}
