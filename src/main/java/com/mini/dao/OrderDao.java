package com.mini.dao;

import com.mini.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hht
 * @create 2019-01-22 11:30
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
}
