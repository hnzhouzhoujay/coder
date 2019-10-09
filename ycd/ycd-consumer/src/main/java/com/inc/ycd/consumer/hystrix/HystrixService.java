package com.inc.ycd.consumer.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Administrator
 * @Date 2019-09-18 10:28
 * @Description TODO
 **/
@Service
public class HystrixService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hystrixHelloFail")
    public String hystrixHello(){
        return restTemplate.getForObject("http://HELLO-SERVICE/hello",String.class);
    }

    public String hystrixHelloFail(){
        return "error";
    }



}
