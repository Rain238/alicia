package com.pic.alicia.api.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 请求接口Dao层
 */
@Mapper
public interface AliciaMapping {
    /**
     * 查询alicia_all表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_all")
    int aliciaAllCount();

    /**
     * 从alicia_all表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_all WHERE id=#{random}")
    String queryAllRandom(int random);

    /**
     * 查询alicia_cat表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_cat")
    int aliciaCatCount();

    /**
     * 从alicia_all表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_cat WHERE id=#{random}")
    String queryCatRandom(int random);

    /**
     * 查询alicia_pc表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_pc")
    int aliciaPcCount();

    /**
     * 从alicia_pc表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_pc WHERE id=#{random}")
    String queryPcRandom(int random);

    /**
     * 查询alicia_mp表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_mp")
    int aliciaMpCount();

    /**
     * 从alicia_mp表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_mp WHERE id=#{random}")
    String queryMpRandom(int random);

    /**
     * 查询alicia_top表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_top")
    int aliciaTopCount();

    /**
     * 从alicia_top表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_top WHERE id=#{random}")
    String queryTopRandom(int random);

    /**
     * 查询alicia_xing表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_xing")
    int aliciaXingCount();

    /**
     * 从alicia_xing表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_xing WHERE id=#{random}")
    String queryXingRandom(int random);

    /**
     * 查询alicia_yin表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_yin")
    int aliciaYinCount();

    /**
     * 从alicia_yin表内随机获取url链接
     *
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_yin WHERE id=#{random}")
    String queryYinRandom(int random);
    /**
     * 查询alicia_astringent表最大条数
     *
     * @return int
     */
    @Select("SELECT COUNT(url) FROM alicia_astringent")
    int aliciaAstCount();
    /**
     * 从alicia_astringent表内随机获取url链接
     * @param random 随机数
     * @return String
     */
    @Select("SELECT url FROM alicia_astringent WHERE id=#{random}")
    String queryAstRandom(int random);


}
