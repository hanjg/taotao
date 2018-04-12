package com.taotao.search.dao.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/11 10:59
 * @Description:
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrClient solrClient;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        SearchResult result = new SearchResult();

        QueryResponse queryResponse = solrClient.query(query);
        //获取查询结果
        SolrDocumentList solrDocuments = queryResponse.getResults();
        //获取高亮显示
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        //发现的条数
        result.setRecordCount(solrDocuments.getNumFound());

        List<Item> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocuments) {
            Item item = new Item();

            item.setId((String) solrDocument.get("id"));

            //高亮显示标题
            List<String> highlightList = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (highlightList != null && highlightList.size() > 0) {
                title = highlightList.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);

            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            item.setCategoryName((String) solrDocument.get("item_category_name"));

            itemList.add(item);
        }

        result.setList(itemList);
        return result;

    }
}
