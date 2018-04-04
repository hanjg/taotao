package com.taotao.rest.service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/23 13:58
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class ItemCatServiceImplTest {

    @Autowired
    private ItemCatService itemCatService;

    @Test
    public void getItemCatList() throws Exception {
        CatResult result = itemCatService.getItemCatList();
        Assert.assertEquals(19, result.getData().size());
        FileUtils.write(new File("catTest.json"), JsonUtils.objectToJson(result));
    }

}