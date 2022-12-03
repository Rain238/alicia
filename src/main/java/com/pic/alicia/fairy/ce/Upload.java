package com.pic.alicia.fairy.ce;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

/**
 * @Author: LightRain
 * @Description: 接收上传图片的Servlet
 * @DateTime: 18/10/2022 下午 4:48
 **/
@Controller
public class Upload {
    private final Logger logger = LoggerFactory.getLogger(Upload.class);//生成日志

    @PostMapping("/uploadServlet")
    public String s(@NotNull HttpServletRequest request) {
        System.out.println(1);
        //创建输入流
        InputStream is = null;
        //创建输出流
        FileOutputStream fos = null;
        //创建字节打印流
        PrintWriter pw = null;
        try {
            //获取储存文件的路径
            String path = Thread.currentThread().getContextClassLoader().getResource("imgPath.txt").getPath();
            //将储存文件的路径给字节打印流执行写入数据的操作
            pw = new PrintWriter(new FileWriter(new File(path), true), true);
            //获取上传的图片集合
            Collection<Part> parts = request.getParts();
            for (Part img : parts) {
                //请求头中包含图片名字信息
                String imgHeader = img.getHeader("Content-Disposition");
                //截取字符串获得图片名字                                                                                           //截取字符串获取文件后缀名
                String imgName = imgHeader.substring(imgHeader.lastIndexOf("=") + 2, imgHeader.length() - 5)+imgHeader.substring(imgHeader.lastIndexOf("."), imgHeader.length() - 1);
                findAddImg(imgName,is,fos,img);
                pw.println(imgName);
            }
            //释放资源
            close(is, fos, pw);

        } catch (IOException e) {
            logger.error("IO异常：{},{}", e.fillInStackTrace(), e.getSuppressed());
        } catch (ServletException e) {
            logger.error("Servlet异常：{},{}", e.fillInStackTrace(), e.getSuppressed());
        } finally {
            //释放资源
            try {
                close(is, fos, pw);
            } catch (IOException e) {
                logger.error("释放资源IO异常：{},{}", e.fillInStackTrace(), e.getSuppressed());
            }
        }
        return "/index.html";
    }


    public boolean findAddImg(String filename, InputStream is, FileOutputStream fos, Part img) {
        String sourcePath = "E:\\测试\\"+filename;
        //数据返回
        try {
            //将上传的图片复制到输入流中
            is = img.getInputStream();
            //输出流
            fos = new FileOutputStream(new File(sourcePath));
            byte[] bytes = new byte[1024];//设置字节大小
            int length = 0;
            while ((length = is.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }
            // 获得文件输入流
            is = new FileInputStream(sourcePath);
            // 从流里将图片写入缓冲图片区
            ImageIO.read(is);
            //关闭输入流
            is.close();
            fos.close();
        } catch (IOException e) {
            logger.error("批量上传图片时错误：{}", e.fillInStackTrace(), e.getSuppressed());
            return false;
        }
        return true;
    }

    private void close(InputStream is, FileOutputStream fos, PrintWriter pw) throws IOException {
        if (is != null) {
            is.close();
        }
        if (fos != null) {
            fos.close();
        }
        if (pw != null) {
            pw.close();
        }
    }
}

