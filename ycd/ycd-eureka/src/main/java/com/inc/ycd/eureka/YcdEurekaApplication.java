package com.inc.ycd.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class YcdEurekaApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(YcdEurekaApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
    }

}

