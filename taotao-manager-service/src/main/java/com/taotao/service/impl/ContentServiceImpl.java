package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/1 21:20
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(int page, int rows, long categoryId) {
        //分页处理,获得记录
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExample(example);

        //获得记录的总条数
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        result.setTotal(total);
        ;
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());

        //contentId根据自增主键获得
        contentMapper.insert(content);

        return TaotaoResult.ok(content);
    }
}
