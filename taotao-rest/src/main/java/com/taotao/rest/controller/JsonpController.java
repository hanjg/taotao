package com.taotao.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hjg
 * @Date: Create in 2018/2/28 12:01
 * @Description:
 */
@Controller
public class JsonpController {

    @RequestMapping("/jsonp")
    @ResponseBody
    public String showIndex(String callback) {
        return callback + "(" + "{\n"
                + "  \"id\": 1,\n"
                + "  \"name\": \"name\"\n"
                + "}" + ")";

    }

}
