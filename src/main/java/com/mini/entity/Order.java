package com.mini.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author hht
 * @create 2019-01-21 21:05
 */

@Entity
@Table(name = "order_")
public class Order {

           @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
           @Column(name = "id")
           private Integer id;

            @ManyToOne
            @JoinColumn(name = "uid")
            private User user;  //一个用户拥有多个订单

          private String orderCode;  //订单号

          private String address; // 地址
          private String post; //邮编
          private String receiver; //收货人地址
          private String mobile; //电话
          private String userMessage; //用户的备注信息
          private Date createDate; //创建日期
          private Date payDate; //支付日期
          private Date deliveryDate; //发货日期
          private Date confirmDate; //确认时间
          private String status; //订单的状态

        @Transient
        private List<OrderItem> orderItems;  //该订单下的订单项

        @Transient
        private float total; //该订单的总计金额
        @Transient
        private int totalNumber;  //该订单的总计数量
        @Transient
        private String statusDesc;  //订单的状态的描述


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }






}
