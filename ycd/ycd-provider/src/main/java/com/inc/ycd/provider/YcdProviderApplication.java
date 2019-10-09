package com.inc.ycd.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class YcdProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(YcdProviderApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
    }

}



