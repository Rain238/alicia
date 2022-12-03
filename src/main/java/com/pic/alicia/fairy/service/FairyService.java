package com.pic.alicia.fairy.service;

import com.pic.alicia.fairy.mapping.FairyMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FairyService implements FairyServiceIn {
    private FairyMapping fairyMapping;

    /**
     * 随机获取壁纸链接
     *
     * @return List<String>
     */
    public List<String> randomPic() {
        ArrayList<String> arrayList = new ArrayList<>();
        //查询数据库all表最大值
        int max = fairyMapping.getRecordMax();
        //根据最大值生成随机数
        SecureRandom r = new SecureRandom();
        for (int i = 0; i < 30; i++) {
            int random = r.nextInt(max);
            String url = fairyMapping.getUrl(random);
            arrayList.add(url);
        }
        return arrayList;
    }

    /**
     * 返回总图片数量
     *
     * @return
     */
    @Override
    public int numberOfPictures() {
        //查询数据库all表最大值
        return fairyMapping.getRecordMax();
    }

    /**
     * 最近更新
     *
     * @param page 页数
     * @return
     */
    @Override
    public List<String> latestUpdate(Integer page) {
        //倒序查询全部表并分页展示
        if (page <= 0) page = 1;
        int recordMax = fairyMapping.getRecordMax();//总记录条数
        int pageSize = 30;//每页展示条数
        int totalPage = recordMax % pageSize == 0 ? (recordMax / pageSize) : (recordMax / pageSize + 1);//计算总页数
        if (page>=totalPage) page = totalPage;
        int startIndex = (page - 1) * pageSize;//开始索引，当前页数-1*每页展示条数
        return fairyMapping.reverseQuery(startIndex, pageSize);
    }
}
