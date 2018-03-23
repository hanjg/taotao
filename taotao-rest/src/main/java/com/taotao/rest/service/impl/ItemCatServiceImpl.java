package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/23 13:37
 * @Description:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public CatResult getItemCatList() {
        CatResult result = new CatResult();
        List<?> list = getListByParentId(0);
        result.setData(list);
        return result;
    }

    /**
     * 递归获得商品类目
     */
    private List<?> getListByParentId(long parentId) {
        List list = new ArrayList<>();

        //查询条件
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
        for (TbItemCat itemCat : itemCatList) {
            if (itemCat.getIsParent()) {
                String url = "/products/" + itemCat.getId() + ".html";
                String name = null;
                if (parentId == 0) {
                    name = "<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>";
                } else {
                    name = itemCat.getName();
                }
                List<?> items = getListByParentId(itemCat.getId());
                list.add(new CatNode(url, name, items));
            } else {
                String s = "/products/" + itemCat.getId() + ".html|" + itemCat.getName();
                list.add(s);
            }
        }

        return list;
    }
}
