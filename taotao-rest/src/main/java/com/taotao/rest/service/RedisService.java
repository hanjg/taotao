package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/4 17:23
 * @Description:
 */
public interface RedisService {

    TaotaoResult syncContent(long catagoryId);

    TaotaoResult syncItemCatList();
}
