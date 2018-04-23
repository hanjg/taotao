package com.taotao.portal.interceptor;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/18 22:37
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private UserService userService;

    @Value("${TT_TOKEN}")
    private String TT_TOKEN;
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_PAGE_LOGIN}")
    private String SSO_PAGE_LOGIN;


    /**
     * @return 是否放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
            throws Exception {
        //从cookie中取token，根据token取得用户信息
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUser(token);

        //取不到用户信息
        if (user == null) {
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            String redirectUrl = SSO_BASE_URL + SSO_PAGE_LOGIN
                    + "?redirect=" + request.getRequestURL();
            LOGGER.debug("redirect: {}", redirectUrl);
            response.sendRedirect(redirectUrl);
            return false;
        }

        //用户信息放入request
        request.setAttribute("user",user);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {

    }
}
