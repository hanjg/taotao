package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.RedisDao;
import com.taotao.rest.service.ItemService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/14 16:20
 * @Description:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Autowired
    private RedisDao redisDao;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${ITEM_BASE_KEY}")
    private String ITEM_BASE_KEY;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;


    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;


    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {
        //该商品redis中商品基本信息对应的key
        String key = REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_BASE_KEY;

        //缓存中读取
        try {
            String json = redisDao.get(key);
            if (!StringUtils.isBlank(json)) {
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return TaotaoResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询基本信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        //写入缓存
        try {
            redisDao.set(key, JsonUtils.objectToJson(item));
            redisDao.expire(key, REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDesc(long itemId) {
        //该商品redis中商品描述对应的key
        String key = REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY;

        //读取缓存
        try {
            String json = redisDao.get(key);
            if (!StringUtils.isBlank(json)) {
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询基本信息
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        //写入缓存
        try {
            redisDao.set(key, JsonUtils.objectToJson(itemDesc));
            redisDao.expire(key, REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult getItemParam(long itemId) {
        //该商品redis中商品参数对应的key
        String key = REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY;

        //读取缓存
        try {
            String json = redisDao.get(key);
            if (!StringUtils.isBlank(json)) {
                TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return TaotaoResult.ok(itemParamItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询基本信息
        TbItemParamItem itemParamItem = null;

        TbItemParamItemExample example = new TbItemParamItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            itemParamItem = list.get(0);
        }

        //写入缓存
        try {
            redisDao.set(key, JsonUtils.objectToJson(itemParamItem));
            redisDao.expire(key, REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok(itemParamItem);
    }

}
