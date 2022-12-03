package com.pic.alicia.api.pojo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 接口提示信息Bean
 */

@Component
public class AliciaBean {
    private int code;//状态码
    private ArrayList<String> pic;//url链接
    private String info;//信息

    public int getCode() {
        return code;
    }

    public AliciaBean setCode(int code) {
        this.code = code;
        return this;
    }

    public ArrayList<String> getPic() {
        return pic;
    }

    public AliciaBean setPic(ArrayList<String> pic) {
        this.pic = pic;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public AliciaBean setInfo(String info) {
        this.info = info;
        return this;
    }
}
