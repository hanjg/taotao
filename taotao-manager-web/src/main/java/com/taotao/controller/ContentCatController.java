package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/26 23:22
 * @Description:
 */
@Controller
@RequestMapping("/content/category")
public class ContentCatController {

    @Autowired
    private ContentCatService contentCatService;

    /**
     * @param parentId 初始请求不带参数，默认为0,获得根节点
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getCategoryList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
        return contentCatService.getCategoryList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult addContentCategory(long parentId, String name) {
        return contentCatService.addContentCategory(parentId, name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(long id) {
        return contentCatService.deleteContentCategory(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id, String name) {
        return contentCatService.updateContentCategory(id, name);
    }
}
