package com.mini.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hht
 * @create 2019-01-21 20:40
 *
 * 评价
 *
 * 一个用户有多个评价
 *
 * 一个产品有多个评价
 */
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;  //外键 用户

    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;  //外键 产品


    private String content; //评价内容

    private Date createDate;  //创建时间

}
