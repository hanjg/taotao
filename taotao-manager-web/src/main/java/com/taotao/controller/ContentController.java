package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/1 21:23
 * @Description:
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(int page, int rows, long categoryId) {
        return contentService.getContentList(page, rows, categoryId);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent content) {
        return contentService.addContent(content);
    }
}
