package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.IndexAd;
import com.taotao.portal.service.ContentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/3 13:58
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {

    private static final Integer HEIGHT = 240;
    private static final Integer WIDTH = 670;
    private static final Integer WIDTH_B = 550;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${INDEX_AD_URL}")
    private String INDEX_AD_URL;

    @Override
    public List<IndexAd> getIndexAdList() {
        List<IndexAd> indexAdList = new ArrayList<>();

        try {
            //调用服务层服务
            String restJson = HttpClientUtil.doGet(REST_BASE_URL + INDEX_AD_URL);

            //取出返回的TaoTaoResult数据
            TaotaoResult taotaoResult = TaotaoResult.formatToList(restJson, TbContent.class);
            List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
            //转化成需要的IndexAd列表
            for (TbContent content : contentList) {
                IndexAd indexAd = new IndexAd();
                indexAd.setSrc(content.getPic());
                indexAd.setHeight(HEIGHT);
                indexAd.setWidth(WIDTH);
                indexAd.setSrcB(content.getPic2());
                indexAd.setWidthB(WIDTH_B);
                indexAd.setHeightB(HEIGHT);
                indexAd.setHref(content.getUrl());
                indexAd.setAlt(content.getSubTitle());

                //注意将indexAd加入结果列表
                indexAdList.add(indexAd);
            }
            return indexAdList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return indexAdList;
    }
}
