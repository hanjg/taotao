package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/4 17:33
 * @Description:
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{catagoryId}")
    public TaotaoResult syncContent(@PathVariable Long catagoryId) {
        TaotaoResult result = redisService.syncContent(catagoryId);
        return result;
    }

    @RequestMapping("itemcat/all")
    public TaotaoResult syncItemCatList() {
        TaotaoResult result = redisService.syncItemCatList();
        return result;
    }
}
