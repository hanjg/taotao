package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/11 11:25
 * @Description:
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
