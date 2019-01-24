package com.mini.controller;

import com.mini.entity.Order;
import com.mini.service.OrderService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-22 19:19
 * 订单相关的controller
 */
@RestController
public class OrderController {

      @Autowired
      private OrderService orderService;

    /**
     *
     * 获取订单的分页数据
     * @param currentPage
     * @param size
     * @return
     */

    @GetMapping("/orders")
      public Page<Order> getOrdersPage(@RequestParam(value = "currentPage",defaultValue = "0") Integer currentPage,
                                       @RequestParam(value = "size",defaultValue = "4") Integer size){

        Page<Order> result = orderService.getOrdersPage(currentPage, size, 5);
        List<Order> content = result.getContent();

        orderService.fillOrder(content); //填充订单项信息
        orderService.removeOrderForOrderItems(content); //移除订单项中 的订单引用 避免出现转换json的死循环的问题

        return result;

    }
}
