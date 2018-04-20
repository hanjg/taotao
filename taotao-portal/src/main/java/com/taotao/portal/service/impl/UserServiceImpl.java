package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/18 22:28
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${TT_TOKEN}")
    public String TT_TOKEN;
    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    public String SSO_USER_TOKEN;

    @Override
    public TbUser getUser(String token) {
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            if (!StringUtils.isBlank(json)) {
                TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
                if (result.getStatus() == 200) {
                    TbUser user = (TbUser) result.getData();
                    return user;
                }
            }
            LOGGER.debug("get json from sso: {}", json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
