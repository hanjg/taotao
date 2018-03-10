package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.ItemParamWithName;
import com.taotao.service.ItemParamService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/7 20:50
 * @Description:
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        //设置查询条件，满足cid的商品目录
        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        //执行查询,注意使用withBLOBs，否则查询结果不会附带大文本。
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }

        return TaotaoResult.ok();

    }

    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItemParam> paramList = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());

        //查询规格对应的名称并封装
        List<ItemParamWithName> paramWithNameList = new ArrayList<>(paramList.size());
        for (TbItemParam itemParam : paramList) {
            String name = getItemCatName(itemParam.getItemCatId());
            paramWithNameList.add(new ItemParamWithName(itemParam, name));
        }

        //总记录条数
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(paramList);
        long total = pageInfo.getTotal();

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(total);
        result.setRows(paramWithNameList);

        return result;

    }


    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        //补全创建和更新时间
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());

        itemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }

    private String getItemCatName(long itemCatId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemCatId);

        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            return list.get(0).getName();
        }
        return null;
    }
}
