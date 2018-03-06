package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * @Author: hjg
 * @Date: Create in 2018/2/27 10:58
 * @Description:
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page, int rows);

    TaotaoResult addItem(TbItem item, TbItemDesc itemDesc);
}
