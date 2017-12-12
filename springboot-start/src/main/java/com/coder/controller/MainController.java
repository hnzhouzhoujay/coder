package com.coder.controller;

import com.coder.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述
 * @作者 zhoujie
 * @创建时间 2017/12/12 15:23.
 */
@RestController
public class MainController {

    @Autowired
    MainPageService mainPageService;

    @RequestMapping("/")
    public String homePage(){
        return "hello boot ,"+mainPageService.getMainContent();
    }
}
