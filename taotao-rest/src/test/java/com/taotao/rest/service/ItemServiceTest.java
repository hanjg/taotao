package com.taotao.rest.service;

import static org.junit.Assert.*;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/14 17:06
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private long itemId = 152368174080783L;

    @Test
    public void getItemBaseInfo() throws Exception {
        TaotaoResult result = itemService.getItemBaseInfo(itemId);
        System.out.println(JsonUtils.objectToJson(result));
    }

    @Test
    public void getItemDesc() throws Exception {
        TaotaoResult result = itemService.getItemDesc(itemId);
        System.out.println(JsonUtils.objectToJson(result));
    }

    @Test
    public void getItemParam() throws Exception {
        TaotaoResult result = itemService.getItemParam(itemId);
        System.out.println(JsonUtils.objectToJson(result));
    }

}