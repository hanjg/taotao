package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/15 17:25
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        LOGGER.debug("param: {}, type: {}, callback: {}", param, type, callback);

        TaotaoResult result = null;

        //参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = TaotaoResult.build(400, "内容不能为空");
        }
        if (type == null) {
            result = TaotaoResult.build(400, "类型不能为空");
        } else if (type != 1 && type != 2 && type != 3) {
            result = TaotaoResult.build(400, "类型错误");
        }
        //校验出错
        if (result != null) {
            return getResult(callback, result);

        }

        //调用服务
        try {
            result = userService.checkData(param, type);
        } catch (Exception e) {
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        return getResult(callback, result);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult addUser(TbUser user) {
        LOGGER.debug("user: {}", ReflectionToStringBuilder.toString(user));
        try {
            TaotaoResult result = userService.addUser(user);
            return result;
        } catch (Exception e) {
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username, String password, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            TaotaoResult result = userService.userLogin(username, password, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/logout/{token}")
    @ResponseBody
    public Object userLogout(@PathVariable String token, String callback, HttpServletRequest request,
            HttpServletResponse response) {
        LOGGER.debug("token: {}, callback: {}", token, callback);
        TaotaoResult result = null;
        try {
            result = userService.userLogout(token, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        return getResult(callback, result);
    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUser(@PathVariable String token, String callback) {
        TaotaoResult result = null;
        try {
            result = userService.getUser(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        return getResult(callback, result);
    }

    /**
     * jsonp调用则返回包含TaoTaoResult的js片段，否则返回TaoTaoResult
     */
    private Object getResult(String callback, TaotaoResult result) {
        if (callback != null) {
            //jsonp调用
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            LOGGER.debug("jacksonVaule: {}", JsonUtils.objectToJson(mappingJacksonValue));
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "redirect:/page/login";
    }

    @RequestMapping("/showRegister")
    public String showRegister() {
        return "redirect:/page/register";
    }

    @RequestMapping("/showLogout/{token}")
    public String showLogout(@PathVariable String token, String callback) {
        String url = "/user/logout/" + token + (callback == null ? "" : "?callback=" + callback);
        LOGGER.debug("logout url: {}", url);
        return "redirect:" + url;
    }
}
