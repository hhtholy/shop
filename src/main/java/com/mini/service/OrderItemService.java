package com.mini.service;

import com.mini.entity.Order;
import com.mini.entity.OrderItem;
import com.mini.entity.Product;
import com.mini.entity.User;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-22 11:57
 */
public interface OrderItemService {


    // 根据 Order去获取 该订单下的所有订单项
    public List<OrderItem> getOrderItemsByOrder(Order order);


    //根据 Product 去获取 该产品所属于的订单项
     List<OrderItem> getOrderItemByProduct(Product product);

     //根据用户  去获取  该用户所在的订单项

    public List<OrderItem> getOrderItemsByUser(User user);

     //更新 订单项的信息
    public void updateOrderItem(OrderItem orderItem);

}
