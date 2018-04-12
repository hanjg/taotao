package com.taotao.search;

import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
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
 * @Date: Create in 2018/4/10 23:27
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    public void getItemList() throws Exception {
        List<Item> list = itemMapper.getItemList();
        Assert.assertEquals(3099, list.size());
        for (int i = 0; i < 10; i++) {
            System.out.println(ReflectionToStringBuilder.toString(list.get(i)));
        }
        int descnull = 0;
        for (Item item : list) {
            if (item.getItemDesc() == null) {
                descnull++;
            }
        }
        System.out.println("descnull: " + descnull);
    }


}
