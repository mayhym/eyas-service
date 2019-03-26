package com.eyas.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by yixuan on 2019/1/2.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    String hello(){
        return "hello!";
    }
}
