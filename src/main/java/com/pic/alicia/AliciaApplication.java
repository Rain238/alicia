package com.pic.alicia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class AliciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliciaApplication.class, args);
    }
    /**
     * Description: springboot对文件上传大小控制
     * 文件上传大小设置
     * @interfaceName
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(50)); // MB
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(1024));
        return factory.createMultipartConfig();
    }
}
