package com.atguigu.controller;

import com.atguigu.domain.Person;
import com.atguigu.messageQueue.DeferredResultQueue;
import com.atguigu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author Liulei
 * @create 2020-08-14 23:20
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;
    @ResponseBody
    @RequestMapping("/hello")
    public String test(){
        String s = helloService.sayHello("tomcat……");
        return s;
    }
    @RequestMapping("/test")
    public String test2(){
        return "success";
    }
    @RequestMapping("/person")
    public String test2(@RequestParam Person person){
        System.out.println(person);
        int i=1/0;
        return "success";
    }
    @RequestMapping(value = "/fileupload")
    public String test22(@RequestParam(value = "desc",required = false) String desc, @RequestParam(value = "file") MultipartFile file) throws IOException {
        System.out.println("文件名："+file.getOriginalFilename());
        System.out.println("desc："+desc);
        //保存文件，必须使用绝对路径
        file.transferTo(new File("D:\\Ideal Projects\\SpringMVCAnnotation\\src\\test\\测试.jpg"));
        return "success";
    }
    @ResponseBody
    @RequestMapping("/async")
    public Callable<String> async(){
        System.out.println("主线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("副线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("副线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
                return "Callable<String> async01()";
            }
        };
        System.out.println("主线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        return callable;
    }
    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<>(Long.valueOf(5000),"fail....");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }
    @ResponseBody
    @RequestMapping("/create")
    public String create(){
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success===>"+order;
    }
}
