package com.shotdog.panda.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * check the application is ok
 * @author ziming  Create At 2018-11-24 11:19
 *
 */
@RestController
public class PingController {


    @GetMapping("/ping")
    public String ping() {


        return "pong";
    }


}
