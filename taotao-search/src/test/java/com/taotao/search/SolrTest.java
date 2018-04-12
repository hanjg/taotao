package com.taotao.search;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/10 23:27
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class SolrTest {

    @Autowired
    private SolrClient solrClient;

    @Test
    public void addDocument() throws Exception {
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "test001");
        document.addField("item_title", "测试商品2");
        document.addField("item_price", 54321);
        //把文档对象写入索引库
        solrClient.add(document);
        //提交
        solrClient.commit();

        //查询
        SolrQuery solrQuery = new SolrQuery("id:test001");
        QueryResponse response = solrClient.query(solrQuery);
        System.out.println(ReflectionToStringBuilder.toString(response.getResults().get(0)));
    }

    @Test
    public void queryDocument() throws Exception {
        SolrQuery solrQuery = new SolrQuery("id:test001");
        QueryResponse response = solrClient.query(solrQuery);
        System.out.println(ReflectionToStringBuilder.toString(response.getResults().get(0)));
        NamedList namedList = response.getResponseHeader();
        System.out.println("status: " + namedList.get("status"));
    }

    @Test
    public void deleteDocument() throws Exception {
        solrClient.deleteByQuery("id:test001");
        solrClient.commit();

        SolrQuery solrQuery = new SolrQuery("id:test001");
        QueryResponse response = solrClient.query(solrQuery);
        Assert.assertEquals(0, response.getResults().size());
    }

}
