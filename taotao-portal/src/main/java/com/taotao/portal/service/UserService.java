package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/18 22:27
 * @Description:
 */
public interface UserService {

    TbUser getUser(String token);
}
