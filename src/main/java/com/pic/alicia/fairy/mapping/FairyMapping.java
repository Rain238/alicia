package com.pic.alicia.fairy.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FairyMapping {
    /**
     * 查询表内最大记录值
     * @return int
     */
    @Select("select count(id) from alicia_all")
    int getRecordMax();

    /**
     * 查询图片链接
     * @param random 随机数
     * @return String
     */
    @Select("select url from alicia_all where id=#{random}")
    String getUrl(int random);

    /**
     * 倒序查询并分页
     * @return
     */
    @Select("select url from alicia_all order by id desc limit #{startIndex}, #{pageSize}")
    List<String> reverseQuery(int startIndex, int pageSize);
}
