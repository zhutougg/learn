package com.example.demo;

import com.example.demo.rest.SayHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class DemoApplication {
    private static final Logger LOG = Logger.getLogger(DemoApplication.class.getName());
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    SayHiService sayHiService;


    @Value("${greeting}")
    String welcomeText;

    @RequestMapping(value="/test")
    public String home(){
        sayHiService.say();
        return "Eureka Client application "+welcomeText;
    }
    @RequestMapping("/")
    public String index() {
        LOG.log(Level.INFO, "Index API is calling");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create("http://eurekaprovider/info");     // fails here
        return restTemplate.getForObject(uri, String.class);
    }
}
