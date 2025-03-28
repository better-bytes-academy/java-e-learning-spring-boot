package com.example.onlinelearning;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class service_test {
    @GetMapping("/hello")
    public String helloworld(){
        return "hello world";
    }
}
