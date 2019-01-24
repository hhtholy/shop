package com.mini.service.impl;

import com.mini.dao.OrderDao;
import com.mini.entity.Order;
import com.mini.entity.OrderItem;
import com.mini.entity.Product;
import com.mini.service.OrderItemService;
import com.mini.service.OrderService;
import com.mini.service.ProductImageService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author hht
 * @create 2019-01-22 15:19
 * 订单相关的 业务层
 */

@Service
public class OrderServiceImpl implements OrderService {

    //注入 orederitemService
    @Autowired
    private OrderItemService orderItemService;

    //注入productImageService

    @Autowired
    private ProductImageService productImageService;

    //注入OrderDao
    @Autowired
    private OrderDao orderDao;

    /**
     * 填充订单的数据
     * @param order
     */
    @Override
    public void fillOrder(Order order) {
            //查出该订单下的所有订单项
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
        float total = 0;
        //总的数量 产品数量
          int totalnum = 0;
          //接受每一个订单项中对应的产品
        Product result = null;
        for (OrderItem item:orderItems){
             result = item.getProduct();
             total += result.getPromotePrice() * item.getNumber();

             totalnum += item.getNumber();
           //？
            productImageService.setFirstImageForProduct(result);

        }
          order.setTotal(total);
          order.setTotalNumber(totalnum);
          order.setOrderItems(orderItems);

    }

    /**
     * 填充订单的数据
     * @param list
     */

    @Override
    public void fillOrder(List<Order> list) {
         for (Order order:list){
             fillOrder(order);
         }

    }

    /**
     * 实现订单数据的分页查询
     * @param currentpage
     * @param size
     * @param navigateNum
     * @return
     */

    @Override
    public Page<Order> getOrdersPage(Integer currentpage, Integer size, Integer navigateNum) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Pageable pageable =new PageRequest(currentpage,size,sort);

        org.springframework.data.domain.Page<Order> result = orderDao.findAll(pageable);

        return new Page<>(result,navigateNum);
    }


    /**
     * 移除 订单项里面的 订单的引用
     * @param order
     */
    @Override
    public void removeOrderForOrderItems(Order order) {
         //获取 order下的订单项
        List<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem item:orderItems){
              item.setOrder(null);
        }
    }

    @Override
    public void removeOrderForOrderItems(List<Order> orders) {
                 for (Order order:orders){
                     removeOrderForOrderItems(order);
                 }
    }

    /**
     * 获取订单数据  根据id
     * @param id
     * @return
     */

    @Override
    public Order getOrderById(Integer id) {
        Order order = new Order();
        order.setId(id);
        Example<Order> example = Example.of(order);
        Optional<Order> optional = orderDao.findOne(example);
        Order result = null;
        if(optional.isPresent()){
            result = optional.get();
        }
        return result;
    }

    /**
     * 更新订单数据
     * @param order
     */

    @Override
    public void updateOrder(Order order) {

        orderDao.save(order);

    }
}
