package com.pic.alicia.api.limiter;


import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * 请求速率限制器 @Version: V1.0
 */
public class RequestRateLimiter {
    // 请求队列
    private final ConcurrentLinkedQueue<Request> bucket = new ConcurrentLinkedQueue<>();
    // 队列上限
    private static final int BUCKET_CAPACITY = 100;
    // 漏桶下沿水流速度
    private final RateLimiter rateLimiter = RateLimiter.create(10.0D);
    // 请求监视器
    private final Monitor requestMoniter = new Monitor();
    // 处理监视器
    private final Monitor handleMoniter = new Monitor();

    /**
     * 请求实体
     */
    public static class Request {
        private int data;

        public Request(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Request{" + "data=" + data + '}';
        }
    }

    public String submitRequest(int data) {
      return  this.submitRequest(new Request(data));
    }

    public String submitRequest(Request request) {
        // 请求监视器，创建监视向导，队列数据量小于上限
        if (requestMoniter.enterIf(requestMoniter.newGuard(() -> bucket.size() < BUCKET_CAPACITY))) {
            try {
                boolean result = bucket.offer(request);
                if (result) {
                    return "成功向队列加入新的请求";
                } else {
                    return "加入新请求失败";
                }
            } finally {
                requestMoniter.leave();
            }
        } else {
            // 队列已满
            return "请求队列已上限";
        }
    }

    // 处理请求方法
    public void handleRequest(Consumer<Request> consumer) {
        if (handleMoniter.enterIf(handleMoniter.newGuard(() -> !bucket.isEmpty()))) {
            try {
                // 匀速处理
                rateLimiter.acquire();
                consumer.accept(bucket.poll());
            } finally {
                handleMoniter.leave();
            }
        }
    }
}