package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

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

    TaotaoResult userLogin(String username, String password);

    TaotaoResult getUser(String token);

    TaotaoResult userLogout(String token);
}
