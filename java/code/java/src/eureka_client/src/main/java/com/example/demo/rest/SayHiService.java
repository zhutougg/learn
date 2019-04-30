package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/30 15:24
 **/
@Service
public class SayHiService {
    @Autowired
    private RestTemplate restTemplate;

    public void say()  {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://eurekaprovider/say","fdfdfd",String.class);
        System.out.println(responseEntity.getBody());
    }
}
