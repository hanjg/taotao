package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/23 13:31
 * @Description: 商品类目节点
 */
public class CatNode {

    @JsonProperty("u")
    private String url;

    @JsonProperty("n")
    private String name;

    /**
     * 子节点。子节点若为叶节点则为String，若非叶节点则为CatNode
     */
    @JsonProperty("i")
    private List<?> items;

    public CatNode(String url, String name, List<?> items) {
        this.url = url;
        this.name = name;
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public List<?> getItems() {
        return items;
    }
}
