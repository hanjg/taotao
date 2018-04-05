package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.rest.dao.RedisDao;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/4 17:29
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Value("${CONTENT_REDIS_KEY}")
    private String CONTENT_REDIS_KEY;
    @Value("${ITEM_CATAGORY_LIST_REDIS_KEY}")
    private String ITEM_CATAGORY_LIST_REDIS_KEY;

    @Autowired
    private RedisDao redisDao;

    @Override
    public TaotaoResult syncContent(long catagoryId) {
        try {
            redisDao.hdel(CONTENT_REDIS_KEY, String.valueOf(catagoryId));
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult syncItemCatList() {
        try {
            redisDao.del(ITEM_CATAGORY_LIST_REDIS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
