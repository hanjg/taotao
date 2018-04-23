package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.RedisDao;
import com.taotao.order.pojo.BuyerRate;
import com.taotao.order.pojo.OrderState;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: hjg
 * @Date: Create in 2018/4/23 14:10
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private RedisDao redisDao;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;


    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        //获得订单号
        long orderId = getOrderId();

        //补全订单信息并插入数据库
        order.setOrderId(String.valueOf(orderId));
        order.setStatus(OrderState.NOT_PAID.getStatus());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setBuyerRate(BuyerRate.NOT_RATED.getStatus());
        orderMapper.insert(order);

        //补全并插入订单详情
        for (TbOrderItem tbOrderItem : itemList) {
            long orderDetailId = redisDao.incr(ORDER_DETAIL_GEN_KEY);
            tbOrderItem.setId(String.valueOf(orderDetailId));
            tbOrderItem.setOrderId(String.valueOf(orderId));
            orderItemMapper.insert(tbOrderItem);
        }

        //补全并插入物流信息
        orderShipping.setOrderId(String.valueOf(orderId));
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId);

    }

    private long getOrderId() {
        String string = redisDao.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(string)) {
            redisDao.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        return redisDao.incr(ORDER_GEN_KEY);
    }
}
