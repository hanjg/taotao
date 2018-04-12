package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/11 10:58
 * @Description:
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;
}
