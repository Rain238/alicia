package com.pic.alicia.api.annotation;

import java.lang.annotation.*;

/**
 * 自定义ip请求次数限制注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLimiting {
    long time() default 60000; // 限制时间 单位：毫秒(当前一分钟）
    int value() default 100; // 允许请求的次数
}