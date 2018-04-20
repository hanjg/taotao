package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/15 17:18
 * @Description:
 */
public interface UserService {

    /**
     * @param type 1、2、3分别代表username、phone、email
     */
    TaotaoResult checkData(String content, Integer type);

    TaotaoResult addUser(TbUser user);

    TaotaoResult userLogin(String username, String password, HttpServletRequest request,
            HttpServletResponse response);

    TaotaoResult getUser(String token);

    TaotaoResult userLogout(String token, HttpServletRequest request,
            HttpServletResponse response);
}
