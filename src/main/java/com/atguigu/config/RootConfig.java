package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Liulei
 * @create 2020-08-14 23:11
 */
//Spring的容器不扫描controller;父容器
@ComponentScan(value = {"com.atguigu"},excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
public class RootConfig {
}
