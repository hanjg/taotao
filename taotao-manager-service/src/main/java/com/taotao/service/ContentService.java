package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/1 21:18
 * @Description:
 */
public interface ContentService {

    EasyUIDataGridResult getContentList(int page, int rows, long categoryId);

    TaotaoResult addContent(TbContent content);
}
