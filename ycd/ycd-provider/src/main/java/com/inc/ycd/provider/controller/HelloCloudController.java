package com.inc.ycd.provider.controller;

import com.inc.ycd.ycdapi.api.HelloService;
import com.inc.ycd.ycdapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019-01-26 12:04
 * @Description TODO
 **/
@RestController
public class HelloCloudController implements HelloService {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello(){
        List<ServiceInstance> instances =  discoveryClient.getInstances("HELLO-SERVICE");
        for(ServiceInstance instance : instances){
            System.out.println("host:"+instance.getHost()+",port:"+instance.getPort());
        }
        return "hello cloud!";
    }

    @Override
    public String sayHello() {
        return "hello world!";
    }

    @Override
    public String sayHello1(String name) {
        return "你好"+name;
    }

    @Override
    public User sayHello1(String name, Integer age) {
        return new User();
    }
}
