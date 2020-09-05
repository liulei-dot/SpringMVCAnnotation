package com.atguigu.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Liulei
 * @create 2020-08-14 23:19
 */
public class HelloDao {
    public String sayHello(String name){

        return "Hello "+name;
    }
}
