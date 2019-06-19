package com.example.demo.controller;

import com.example.demo.fegininterface.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/30 18:03
 **/
@RestController
public class ConsumerController implements ComputeClient {


    @Override
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(Integer a, Integer b) {
        return 10 * 20;
    }
}
