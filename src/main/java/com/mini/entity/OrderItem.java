package com.mini.entity;

import javax.naming.Name;
import javax.persistence.*;

/**
 * @author hht
 * @create 2019-01-21 21:06
 */


@Entity
@Table(name = "orderitem")
public class OrderItem {

    /**
     * 一个产品在多个订单项里
     *
     * 一个订单 拥有多个订单项
     *
     * 一个用户有多个订单项
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "oid")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
