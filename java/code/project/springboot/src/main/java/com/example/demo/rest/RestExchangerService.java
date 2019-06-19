package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/29 10:35
 **/
@RestController
public class RestExchangerService {
  /*  @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/template/products",method = RequestMethod.POST)
    public String getProductList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String products(){
        return "xxxxbbbb";
    }*/
}
