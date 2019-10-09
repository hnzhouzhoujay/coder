package com.inc.ycd.ycdapi.api;

import com.inc.ycd.ycdapi.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/helloo")
public interface HelloService {

    @GetMapping("/sayHello")
    public String sayHello();

    @GetMapping("/sayHello1")
    public String sayHello1(@RequestParam("name") String name);


    @GetMapping("/helloUser")
    public User sayHello1(@RequestParam("name") String name, @RequestParam("name") Integer age);
}
