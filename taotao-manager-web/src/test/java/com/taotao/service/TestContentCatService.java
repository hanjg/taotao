package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
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
 * @Date: Create in 2018/3/27 0:05
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestContentCatService {

    @Autowired
    private ContentCatService contentCatService;

    @Test
    public void testAdd() {
        long id = 96;
        String name = "中广告1";
        TaotaoResult result = contentCatService.addContentCategory(id, name);
        Assert.assertTrue(contentCatService.getCategory(id).getIsParent());
        System.out.println(ReflectionToStringBuilder.toString(result.getData()));
    }

    @Test
    public void testQuery() {
        long parentId = 1000;
        List<EasyUITreeNode> list = contentCatService.getCategoryList(parentId);
        Assert.assertEquals(0, list.size());
    }
}
