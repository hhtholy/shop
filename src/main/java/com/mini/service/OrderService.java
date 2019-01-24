package com.mini.service;

import com.mini.entity.Order;
import com.mini.util.Page;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-22 11:57
 */
public interface OrderService {

    //填充订单的订单项  以及订单的其他信息 比如订单总数 总金额
    public void fillOrder(Order order);
    public void fillOrder(List<Order> list);


    //订单列表分页展示
    public Page<Order> getOrdersPage(Integer currentpage,Integer size,Integer navigateNum);

    //避免转换为json的时候出现的死循环的问题
    public void removeOrderForOrderItems(Order order);

    public void removeOrderForOrderItems(List<Order> order);
}
