package com.pic.alicia.fairy.service;


import java.util.List;

public interface FairyServiceIn {
     /**
      * 随机获取壁纸链接
      * @return List<String>
      */
     List<String> randomPic();

     /**
      * 返回总图片数量
      * @return int
      */
     int numberOfPictures();

     /**
      * 最近更新
      * @param page 页数
      * @return List<String
      */
     List<String> latestUpdate(Integer page);
}
