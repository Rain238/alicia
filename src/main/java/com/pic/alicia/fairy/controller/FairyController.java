package com.pic.alicia.fairy.controller;

import com.pic.alicia.fairy.service.FairyServiceIn;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@AllArgsConstructor
public class FairyController implements FairyControllerIn {
    private FairyServiceIn fairyServiceIn;
    @GetMapping("/upload")
    public String upload(@NotNull Model model) {
        model.addAttribute("name", "simonsfan");
        return "/upload.html";
    }
    /**
     * 默认跳转到首页
     *
     * @param model
     * @return String
     */
    @GetMapping("/")
    public String index(@NotNull Model model) {
        model.addAttribute("name", "simonsfan");
        return "/index.html";
    }

    /**
     * 随机壁纸请求映射路径
     *
     * @param response
     * @return List<String>
     */
    @PostMapping("/random")
    @ResponseBody
    public List<String> randomPic(HttpServletResponse response) {
        crossDomain(response);
        return fairyServiceIn.randomPic();
    }

    /**
     * 精选壁纸请求映射路径，精选壁纸一样使用randomPic随机方法
     *
     * @param response
     * @return List<String>
     */
    @PostMapping("/featured")
    @ResponseBody
    public List<String> featuredPic(HttpServletResponse response) {
        crossDomain(response);
        return fairyServiceIn.randomPic();
    }

    /**
     * 返回总图片数量
     *
     * @param response
     * @return
     */
    @PostMapping("/numberOfPictures")
    @ResponseBody
    public int numberOfPictures(HttpServletResponse response) {
        crossDomain(response);
        return fairyServiceIn.numberOfPictures();
    }

    /**
     * 最近更新请求映射路径
     * @param page 页数
     * @param response
     * @return
     */
    @PostMapping("/latestUpdate")
    @ResponseBody
    public List<String> latestUpdate(Integer page,HttpServletResponse response) {
        crossDomain(response);
        return fairyServiceIn.latestUpdate(page);
    }

    /**
     * 设置跨域请求头
     *
     * @param response
     */
    public void crossDomain(@NotNull HttpServletResponse response) {
        //解决跨域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        //接收任何响应头
        response.setHeader("Access-Control-Allow-Headers", "*");
    }
}
