package com.pic.alicia.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LightRain
 * @Description: 获取图床的源图链接
 * @DateTime: 18/10/2022 下午 7:04
 **/
public class GetTheSourceImageLinkOfTheImageBed {
    public static void main(String[] args) throws IOException, IOException {
        List<String> strings = textList();
        ArrayList<String> urlList = new ArrayList<String>();
        //创建字节打印流
        PrintWriter pw = new PrintWriter(new FileWriter(new File("D:\\IDEA\\temporary\\alicia\\src\\main\\resources\\url.txt"), true), true);;
        for (String url : strings) {
            URL urls = new URL(url);
            //把url传进去并给一个超时时间
            Document parse = Jsoup.parse(urls, 30000);
            //System.out.println(parse);
            Elements a_href = parse.select("#embed-code-2[value]");
            urlList.add(a_href.val());
        }
        for (String u : urlList) {
            pw.println(u);
        }
    }

    /**
     * 每次读取一行
     * @return List<String>
     * @throws IOException
     */
    public static List<String> textList() throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        InputStream is = new FileInputStream("D:\\IDEA\\temporary\\alicia\\src\\main\\resources\\file.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String str = null;
        while (true) {
            str = reader.readLine();
            if (str != null)
                arrayList.add(str);
            else {
                break;
            }
        }
        return arrayList;
    }
}
