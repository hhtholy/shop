package com.mini.service.impl;

import com.mini.dao.OrderItemDao;
import com.mini.entity.Order;
import com.mini.entity.OrderItem;
import com.mini.entity.Product;
import com.mini.entity.User;
import com.mini.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-22 15:08
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {




     //注入
    @Autowired
      private OrderItemDao orderItemDao;

    /**
     * 根据订单 获取订单下的所有订单项
     * @param order
     * @return
     */
    @Override
    public List<OrderItem> getOrderItemsByOrder(Order order) {
        return orderItemDao.findByOrderOrderById(order);
    }

    /**
     * 根据产品  获取产品所在的订单项
     * @param product
     * @return
     */

    @Override
    public List<OrderItem> getOrderItemByProduct(Product product) {
        return orderItemDao.findByProduct(product);
    }


    /**
     *   根据用户去查询 对应的订单项
     * @param user
     * @return
     */
    @Override
    public List<OrderItem> getOrderItemsByUser(User user) {
        return orderItemDao.findByUserAndOrderIsNotNull(user);
    }

    /**
     * 更新订单项的信息
     * @param orderItem
     */
    @Override
    public void updateOrderItem(OrderItem orderItem) {

         orderItemDao.save(orderItem);

    }
}
