package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/1 11:03
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestItemCat {

    @Autowired
    private ItemCatService itemCatService;

    @Test
    public void testItemCat() {
        List<EasyUITreeNode> list = itemCatService.getItemCatList(0);
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
        Assert.assertEquals(19, list.size());

        List<EasyUITreeNode> childList = itemCatService.getItemCatList(1);
        System.out.println(ReflectionToStringBuilder.toString(childList.get(0)));
        Assert.assertEquals(12, childList.size());
    }
}
