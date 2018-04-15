package com.taotao.portal.service;

import com.taotao.portal.pojo.Item;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/14 17:25
 * @Description:
 */
public interface ItemService {

    Item getItemBase(long itemId);

    String getItemDesc(Long itemId);

    String getItemParam(Long itemId);
}
