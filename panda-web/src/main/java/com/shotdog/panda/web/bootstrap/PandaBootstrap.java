package com.shotdog.panda.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 *  服务启动主类
 * @author ziming  Create At 2018-11-24 11:05
 *
 */

@SpringBootApplication(scanBasePackages = {"com.shotdog"})
public class PandaBootstrap {

    public static void main(String[] args) {

        SpringApplication.run(PandaBootstrap.class, args);

    }
}
