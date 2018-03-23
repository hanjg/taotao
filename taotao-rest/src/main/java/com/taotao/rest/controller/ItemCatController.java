package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hjg
 * @Date: Create in 2018/3/23 14:13
 * @Description:
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * @param callback 回调函数名称
     */
    @RequestMapping(value = "itemcat/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();

        //选取前14类商品
        List newData = new ArrayList<>(14);
        List<?> data = catResult.getData();
        for (int i = 0; i < 14; i++) {
            newData.add(data.get(i));
        }
        catResult.setData(newData);

        return callback + "(" + JsonUtils.objectToJson(catResult) + ")";
    }
   /* @RequestMapping("/itemcat/all")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }*/

}
