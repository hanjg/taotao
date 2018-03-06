package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import java.util.List;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/1 10:54
 * @Description:
 */
public interface ItemCatService {

    List<EasyUITreeNode> getItemCatList(long parentId);
}
