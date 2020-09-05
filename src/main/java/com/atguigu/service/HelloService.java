package com.atguigu.service;

import org.springframework.stereotype.Service;

/**
 * @author Liulei
 * @create 2020-08-14 23:19
 */
@Service
public class HelloService {
    public String sayHello(String name){

        return "Hello "+name;
    }
}
