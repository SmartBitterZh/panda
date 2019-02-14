package com.shotdog.panda.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 *
 * @author ziming  Create At 2018-12-01 13:26
 *
 */
@Configuration
public class PandaWebMvcConfigurer implements WebMvcConfigurer {


    public void addFormatters(FormatterRegistry registry) {


        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
}
