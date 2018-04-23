package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/23 15:03
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(Order order) {
        //调用服务层的服务
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
        if (!StringUtils.isBlank(json)) {
            //获取订单号
            TaotaoResult taotaoResult = TaotaoResult.format(json);
            if (taotaoResult.getStatus() == 200) {
                //jackson根据数据的大小转换为Integer或者Long类型
                Object orderId = taotaoResult.getData();
                return orderId.toString();
            }
        }

        return "";
    }
}
