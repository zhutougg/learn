package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/30 15:27
 **/
@RestController
public class SayController {


    @RequestMapping(value = "/say")
    public String say(@RequestBody String name) {
        System.out.println("ribbon "+name);
        return name + " ribbon " + "13";
    }
}
