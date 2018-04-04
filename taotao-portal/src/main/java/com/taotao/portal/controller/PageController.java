package com.taotao.portal.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.IndexAd;
import com.taotao.portal.service.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hjg
 * @Date: Create in 2018/2/28 12:01
 * @Description: 页面跳转
 */
@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        List<IndexAd> indexAdList = contentService.getIndexAdList();
        model.addAttribute("ad1", JsonUtils.objectToJson(indexAdList));
        return "index";
    }

}
