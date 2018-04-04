package com.taotao.portal.service;

import static org.junit.Assert.*;

import com.taotao.portal.pojo.IndexAd;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/3 14:53
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    @Test
    public void getIndexAdList() throws Exception {
        List<IndexAd> list = contentService.getIndexAdList();
        assertEquals(3, list.size());
        for (IndexAd indexAd : list) {
            System.out.println(ReflectionToStringBuilder.toString(indexAd));
        }
    }

}