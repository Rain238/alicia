package com.pic.alicia.api.service;

import org.springframework.web.servlet.ModelAndView;

public interface AliciaServiceIn {
    /**
     * 根据Sort类型查询数据表并随机返回Url
     * @param sort 类型
     * @param type JSON
     * @param num 数量
     * @return ModelAndView
     */
    ModelAndView ApiRandom(String sort, String type, Integer num);
}
