package com.pic.alicia.api.service;

import com.pic.alicia.api.mapping.AliciaMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.security.SecureRandom;
import java.util.*;

@Component
@AllArgsConstructor
public class AliciaService implements AliciaServiceIn {
    private final AliciaMapping am;
    private final static int MAX = 60;//返回结果最大值
    private final static String ALL = "all";//全部随机
    private final static String RANDOM = "random";//全部随机
    private final static String CAT = "cat";//兽耳
    private final static String PC = "pc";//横屏
    private final static String MP = "mp";//竖屏
    private final static String TOP = "top";//推荐
    private final static String XING = "xing";//银发
    private final static String YIN = "yin";//星空
    private final static String ASTRINGENT = "ast";//涩图
    private final static String R18 = "r18";//R18

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 6:10
     * @Param: [sort, type, num]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 根据Sort类型查询数据表并随机返回Url
     */
    @Override
    public ModelAndView ApiRandom(String sort, String type, Integer num) {
        //转换为小写
        if (sort != null) sort = sort.toLowerCase();
        if (type != null) type = type.toLowerCase();
        //url集合返回多条url链接并存入map内一并返回
        ArrayList<String> arrayList = new ArrayList<>();
        //获取随机数
        SecureRandom r = new SecureRandom();
        //创建返回视图
        ModelAndView modelAndView = null;
        switch (Objects.requireNonNull(sort)) {
            case ALL -> modelAndView = r(sort, type, num, arrayList, r, ALL);
            case RANDOM -> modelAndView = r(sort, type, num, arrayList, r, RANDOM);
            case CAT -> modelAndView = r(sort, type, num, arrayList, r, CAT);
            case PC -> modelAndView = r(sort, type, num, arrayList, r, PC);
            case MP -> modelAndView = r(sort, type, num, arrayList, r, MP);
            case TOP -> modelAndView = r(sort, type, num, arrayList, r, TOP);
            case XING -> modelAndView = r(sort, type, num, arrayList, r, XING);
            case YIN -> modelAndView = r(sort, type, num, arrayList, r, YIN);
            case ASTRINGENT -> modelAndView = r(sort, type, num, arrayList, r, ASTRINGENT);
        }
        if (!Objects.equals(modelAndView, null))
            return modelAndView;
        else
            return setTypeHint(aliciaMap(401, null, "sort未知的类型"));
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:43
     * @Param: [sort, arrayList, r, t]
     * @Return: java.util.ArrayList<java.lang.String>
     * @Description: 获取图片地址
     */
    private ArrayList<String> getPicUrl(String sort, ArrayList<String> arrayList, SecureRandom r, String t) {
        switch (t) {
            case ALL, RANDOM -> arrayList.add(aliciaUrl(sort, r, am.aliciaAllCount()));
            case CAT -> arrayList.add(aliciaUrl(sort, r, am.aliciaCatCount()));
            case PC -> arrayList.add(aliciaUrl(sort, r, am.aliciaPcCount()));
            case MP -> arrayList.add(aliciaUrl(sort, r, am.aliciaMpCount()));
            case TOP -> arrayList.add(aliciaUrl(sort, r, am.aliciaTopCount()));
            case XING -> arrayList.add(aliciaUrl(sort, r, am.aliciaXingCount()));
            case YIN -> arrayList.add(aliciaUrl(sort, r, am.aliciaYinCount()));
            case ASTRINGENT -> arrayList.add(aliciaUrl(sort, r, am.aliciaAstCount()));
        }
        return arrayList;
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:44
     * @Param: [sort, r, t]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 返回图片
     */
    private ModelAndView backToImage(String sort, SecureRandom r, String t) {
        ModelAndView modelAndView = null;
        switch (t) {
            case ALL, RANDOM -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaAllCount()));
            case CAT -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaCatCount()));
            case PC -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaPcCount()));
            case MP -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaMpCount()));
            case TOP -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaTopCount()));
            case XING -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaXingCount()));
            case YIN -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaYinCount()));
            case ASTRINGENT -> modelAndView = modelAndView(aliciaUrl(sort, r, am.aliciaAstCount()));
        }
        return modelAndView;
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:44
     * @Param: [sort, r, count]
     * @Return: java.lang.String
     * @Description: 封装Url获取
     */
    private String aliciaUrl(String sort, SecureRandom r, int count) {
        //生成一个随机数最小为1最大为当前数据表中的最大值
        int random = r.nextInt(count) + 1;
        String getUrl = null;
        switch (sort) {
            case ALL, RANDOM -> getUrl = am.queryAllRandom(random).replaceAll("\\r\\n", "");
            case CAT -> getUrl = am.queryCatRandom(random).replaceAll("\\r\\n", "");
            case PC -> getUrl = am.queryPcRandom(random).replaceAll("\\r\\n", "");
            case MP -> getUrl = am.queryMpRandom(random).replaceAll("\\r\\n", "");
            case TOP -> getUrl = am.queryTopRandom(random).replaceAll("\\r\\n", "");
            case XING -> getUrl = am.queryXingRandom(random).replaceAll("\\r\\n", "");
            case YIN -> getUrl = am.queryYinRandom(random).replaceAll("\\r\\n", "");
            case ASTRINGENT -> getUrl = am.queryAstRandom(random).replaceAll("\\r\\n", "");
        }
        return getUrl;
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 6:11
     * @Param: [sort, type, num, arrayList, random, t]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 根据Sort类型查询数据表并随机返回Url
     */
    private ModelAndView r(String sort, String type, Integer num, ArrayList<String> arrayList, SecureRandom random, String t) {
        if (sort == null || Objects.equals(sort, ""))
            return setTypeHint(aliciaMap(401, null, "sort参数未设置"));
        if (sort.equals(t) && type == null)
            return backToImage(sort, random, t);
        if (sort.equals(t) && type.equals(""))
            return setTypeHint(aliciaMap(401, null, "请将Type设置为Json"));
        if (sort.equals(t) && !type.equals("json"))
            return setTypeHint(aliciaMap(401, null, "请将Type设置为Json"));
        if (sort.equals(t)) {
            return backToImageList(sort, num, arrayList, random, t);
        }
        return null;
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:37
     * @Param: [sort, num, arrayList, random, type]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 返回图像列表
     */
    private ModelAndView backToImageList(String sort, Integer num, ArrayList<String> arrayList, SecureRandom random, String type) {
        if (num == null) {
            return setTypeHint(aliciaMap(200, getPicUrl(sort, arrayList, random, type), ""));
        }
        if (num < 1) num = 1;
        if (num > MAX) num = MAX;//超出条数限制将强制设置为60条
        ArrayList<String> urlList = null;
        //循环获取url并添加到ArrList集合内
        for (int i = 0; i < num; i++) urlList = getPicUrl(sort, arrayList, random, type);
        return setTypeHint(aliciaMap(200, urlList, ""));
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:44
     * @Param: [aliciaUrl]
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 封装视图
     */
    private ModelAndView modelAndView(String aliciaUrl) {
        return new ModelAndView(String.format("redirect:%s", aliciaUrl));
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:35
     * @Param: []
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Description: 设置返回结果
     */
    private ModelAndView setTypeHint(Map<String, Object> aliciaMap) {
        return new ModelAndView(new MappingJackson2JsonView()).addAllObjects(aliciaMap);
    }

    /**
     * @Author: LightRain
     * @Date: 21/10/2022 下午 5:52
     * @Param: [code, arrayList, info]
     * @Return: java.util.Map<java.lang.String, java.lang.Object>
     * @Description: 封装返回结果Map
     */
    private Map<String, Object> aliciaMap(int code, ArrayList<String> arrayList, String info) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("pic", arrayList);
        map.put("info", info);
        return map;
    }
}
