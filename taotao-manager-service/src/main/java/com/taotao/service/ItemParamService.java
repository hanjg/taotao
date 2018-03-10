package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/7 20:50
 * @Description:
 */
public interface ItemParamService {

    TaotaoResult getItemParamByCid(long cid);

    EasyUIDataGridResult getItemParamList(int page, int rows);

    TaotaoResult insertItemParam(TbItemParam itemParam);
}
