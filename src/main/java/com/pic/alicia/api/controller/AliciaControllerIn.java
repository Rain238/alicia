package com.pic.alicia.api.controller;

import org.springframework.web.servlet.ModelAndView;

public interface AliciaControllerIn {
    /**
     * @Author: LightRain
     * @Date: 18/10/2022 下午 12:43
     * @Param: [sort, type, num]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 根据Sort类型查询数据表并随机返回Url
     */
    ModelAndView ApiAllRandom(String sort, String type, Integer num);
}