package com.inc.ycd.consumer.feign;


import com.inc.ycd.ycdapi.api.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("HELLO-SERVICE")
public interface HelloServiceFg extends HelloService {
}
