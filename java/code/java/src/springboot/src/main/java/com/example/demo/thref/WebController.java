package com.example.demo.thref;

import com.example.demo.scheduler.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/4/29 15:35
 **/
@Controller
public class WebController {
    @Autowired
    Scheduler scheduler;
    @RequestMapping(value = "/hello")
    public String index() {
        scheduler.fixedRateSch();
        return "upload";
    }
}