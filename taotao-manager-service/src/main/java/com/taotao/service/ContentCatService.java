package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import java.util.List;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/26 23:16
 * @Description:
 */
public interface ContentCatService {

    /**
     * 根据父节点获得内容分类列表
     */
    List<EasyUITreeNode> getCategoryList(long parentId);

    TbContentCategory getCategory(long id);

    TaotaoResult addContentCategory(long parentId, String name);

    TaotaoResult deleteContentCategory(long id);

    TaotaoResult updateContentCategory(long id, String name);
}
