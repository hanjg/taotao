package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.RedisDao;
import com.taotao.rest.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/3 13:36
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {

    /**
     * 主页内容在redis使用的key
     */
    @Value("${CONTENT_REDIS_KEY}")
    private String CONTENT_REDIS_KEY;

    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private RedisDao redisDao;

    @Override
    public List<TbContent> getContentList(long catagroyId) {
        //从缓存中读取
        try {
            String cache = redisDao.hget(CONTENT_REDIS_KEY, String.valueOf(catagroyId));
            if (cache != null && !cache.isEmpty()) {
                return JsonUtils.jsonToList(cache, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(catagroyId);

        List<TbContent> list = contentMapper.selectByExample(example);

        //放入缓存
        try {
            String listjson = JsonUtils.objectToJson(list);
            redisDao.hset(CONTENT_REDIS_KEY, String.valueOf(catagroyId), listjson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
