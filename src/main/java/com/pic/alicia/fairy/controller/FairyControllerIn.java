package com.pic.alicia.fairy.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface FairyControllerIn {
    /**
     * 默认跳转到首页
     *
     * @param model
     * @return
     */
    String index(Model model);

    /**
     * 随机壁纸请求接口
     *
     * @param response
     * @return
     */
    List<String> randomPic(HttpServletResponse response);

    /**
     * 返回总图片数量请求接口
     * @param response
     * @return
     */
    int numberOfPictures(HttpServletResponse response);

    /**
     * 最近更新请求接口
     * @param page 页数
     * @param response
     * @return
     */
    List<String> latestUpdate(Integer page,HttpServletResponse response);
}
