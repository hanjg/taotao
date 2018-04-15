package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/14 16:19
 * @Description:
 */
public interface ItemService {

    TaotaoResult getItemBaseInfo(long itemId);

    TaotaoResult getItemDesc(long itemId);

    TaotaoResult getItemParam(long itemId);
}
