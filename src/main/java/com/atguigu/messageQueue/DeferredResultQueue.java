package com.atguigu.messageQueue;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Liulei
 * @create 2020-08-17 10:30
 */
public class DeferredResultQueue {
    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<DeferredResult<Object>>();

    public static void save(DeferredResult<Object> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get( ){
        return queue.poll();
    }
}
