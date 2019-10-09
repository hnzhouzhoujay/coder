package com.inc.ycd.zuul;

import com.inc.ycd.zuul.filter.MyAccessFilter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class YcdZuulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(YcdZuulApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
    }

    @Bean
    public MyAccessFilter myAccessFilter(){
        return new MyAccessFilter();
    }

}
