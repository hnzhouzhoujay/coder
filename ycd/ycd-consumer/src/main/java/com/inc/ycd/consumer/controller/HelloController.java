package com.inc.ycd.consumer.controller;

import com.inc.ycd.consumer.feign.HelloServiceFg;
import com.inc.ycd.consumer.hystrix.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Administrator
 * @Date 2019-04-11 11:34
 * @Description TODO
 **/

@RestController
public class HelloController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloServiceFg helloServiceFg;

    @Autowired
    HystrixService hystrixService;


    @GetMapping("/hello")
    public String hello(){
        return restTemplate.getForObject("http://HELLO-SERVICE/hello",String.class);
    }


    @GetMapping("/feign-hello")
    public String feignHello(@RequestParam("name") String name){
        return helloServiceFg.sayHello1(name);
    }

    @GetMapping("/hystrix-hello")
    public String hystrixHello(){
        return hystrixService.hystrixHello();
    }




}
