package com.mini.service;

import com.mini.entity.Order;
import com.mini.entity.OrderItem;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-22 11:57
 */
public interface OrderItemService {


    // 根据 Order去获取 该订单下的所有订单项
    public List<OrderItem> getOrderItemsByOrder(Order order);
}
