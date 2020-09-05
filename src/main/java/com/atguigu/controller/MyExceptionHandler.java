package com.atguigu.controller;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Liulei
 * @create 2020-08-06 10:12
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleException(Exception ex){
        System.out.println("异常是："+ex);
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }
}
