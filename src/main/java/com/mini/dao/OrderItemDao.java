package com.mini.dao;

import com.mini.entity.Order;
import com.mini.entity.OrderItem;
import com.mini.entity.Product;
import com.mini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-22 11:31
 */
public interface OrderItemDao extends JpaRepository<OrderItem,Integer> {
        //根据产品去查询订单项
      public List<OrderItem> findByProduct(Product product);

       //根据订单去查订单项
    public List<OrderItem> findByOrderOrderById(Order order);

       //根据产品去 查订单项
       public List<OrderItem> findByProductOrderById(Product product);

       //根据 用户去查询 订单项
      public List<OrderItem> findByUserAndOrderIsNotNull(User user);



}
