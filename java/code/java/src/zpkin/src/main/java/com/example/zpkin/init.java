package com.example.zpkin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/5/7 18:26
 **/
@Configuration
public class init {
    @Bean
    RestTemplate restTemplate(){ return new RestTemplate(); }
}
