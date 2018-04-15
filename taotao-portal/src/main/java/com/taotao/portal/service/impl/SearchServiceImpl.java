package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/14 11:57
 * @Description:
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_SERVICE_URL}")
    private String SEARCH_SERVICE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        //设置查询参数
        Map<String, String> params = new HashMap<>();
        params.put("q", queryString);
        params.put("page", String.valueOf(page));

        try {
            //执行查询
            String res = HttpClientUtil.doGet(SEARCH_SERVICE_URL, params);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(res, SearchResult.class);
            if (taotaoResult.getStatus() == 200) {
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
