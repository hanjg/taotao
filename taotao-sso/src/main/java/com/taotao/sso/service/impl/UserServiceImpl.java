package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.RedisDao;
import com.taotao.sso.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/15 17:19
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;
    @Value("${TT_TOKEN}")
    private String TT_TOKEN;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private RedisDao redisDao;

    @Override
    public TaotaoResult checkData(String content, Integer type) {
        //创建查询条件
        TbUserExample example = new TbUserExample();
        Criteria criteria = example.createCriteria();
        if (type == 1) {
            criteria.andUsernameEqualTo(content);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(content);
        } else {
            criteria.andEmailEqualTo(content);
        }

        //执行查询
        Boolean canBeUse = null;
        List<TbUser> userList = userMapper.selectByExample(example);
        if (userList == null || userList.size() == 0) {
            canBeUse = true;
        } else {
            canBeUse = false;
        }

        LOGGER.debug("userList: {}", JsonUtils.objectToJson(userList));

        return TaotaoResult.ok(canBeUse);

    }

    @Override
    public TaotaoResult addUser(TbUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //密码md5化之后存储在数据库
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.insert(user);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult userLogin(String username, String password, HttpServletRequest request,
            HttpServletResponse response) {
        //查询该用户
        TbUserExample example = new TbUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> userList = userMapper.selectByExample(example);

        //用户不存在
        if (userList == null || userList.isEmpty()) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }

        TbUser user = userList.get(0);
        String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
        //密码不匹配
        if (!user.getPassword().equals(md5)) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }

        //生成用户token
        String token = UUID.randomUUID().toString();
        //redis中需要清空用户的密码
        user.setPassword(null);
        //用户信息写入redis
        String key = REDIS_USER_SESSION_KEY + ":" + token;
        redisDao.set(key, JsonUtils.objectToJson(user));
        redisDao.expire(key, SSO_SESSION_EXPIRE);

        //添加写cookie的逻辑，默认有效期为浏览器关闭
        CookieUtils.setCookie(request, response, TT_TOKEN, token);

        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUser(String token) {
        //从redis中查询用户信息
        String json = redisDao.get(REDIS_USER_SESSION_KEY + ":" + token);

        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "此session已经过期，请重新登录");
        }

        //更新过期时间
        redisDao.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

        return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));

    }

    @Override
    public TaotaoResult userLogout(String token, HttpServletRequest request,
            HttpServletResponse response) {
        //删除redis中的key
        String key = REDIS_USER_SESSION_KEY + ":" + token;
        redisDao.del(key);

        //删除cookie
        CookieUtils.deleteCookie(request, response, TT_TOKEN);
        return TaotaoResult.ok();
    }
}
