package com.taotao.rest.service;

import com.taotao.pojo.TbContent;
import java.util.List;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/3 13:35
 * @Description:
 */
public interface ContentService {

    List<TbContent> getContentList(long catagroyId);
}
