package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/14 11:57
 * @Description:
 */
public interface SearchService {

    SearchResult search(String queryString, int page);
}
