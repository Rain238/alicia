package com.pic.alicia.api.controller;

import com.pic.alicia.api.annotation.CurrentLimiting;
import com.pic.alicia.api.service.AliciaServiceIn;
import com.pic.alicia.api.limiter.RequestRateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * API请求接口控制器层
 */
@Controller
@AllArgsConstructor
public class AliciaController implements AliciaControllerIn {
    private final AliciaServiceIn alicia;
    private static final AtomicInteger DATA = new AtomicInteger(0);
    //请求速率限制器
    private static final RequestRateLimiter HANDLE = new RequestRateLimiter();

    /**
     * @Author: LightRain
     * @Date: 18/10/2022 下午 12:42
     * @Param: [sort, type, num]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: API请求接口
     */
    @RequestMapping("api/index")
    @CurrentLimiting()//ip请求次数限制注解
    public ModelAndView ApiAllRandom(String sort, String type, Integer num) {
        //提交队列请求并返回队列是否已上限
        String getRequest = HANDLE.submitRequest(DATA.getAndIncrement());
        //处理队列请求
        HANDLE.handleRequest(y -> alicia.ApiRandom(sort, type, num));
        //队列上限将跳转到404页面
        if (!getRequest.equals("成功向队列加入新的请求")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/404.html");
            return modelAndView;
        }
        return alicia.ApiRandom(sort, type, num);
    }
}
