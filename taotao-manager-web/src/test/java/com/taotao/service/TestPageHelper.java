package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: hjg
 * @Date: Create in 2018/2/28 15:48
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestPageHelper {

    @Autowired
    private ItemService itemService;

    @Test
    public void testPageHelper() {
        long total = itemService.getItemList(1, Integer.MAX_VALUE).getTotal();

        EasyUIDataGridResult result = itemService.getItemList(1, 10);
        Assert.assertEquals(10, result.getRows().size());
        Assert.assertEquals(total, result.getTotal());
        for (TbItem item : (List<TbItem>) result.getRows()) {
            System.out.println(ReflectionToStringBuilder.toString(item));
        }
    }
}
