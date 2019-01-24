package com.mini.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author hht
 * @create 2019-01-16 10:31
 *
 * 产品表
 *
 *
 *  `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `subTitle` varchar(255) DEFAULT NULL COMMENT '小标题',
    `originalPrice` float DEFAULT NULL COMMENT '原始价格',
    `promotePrice` float DEFAULT NULL COMMENT '优惠价格 ',
    `stock` int(11) DEFAULT NULL COMMENT '库存',
    `cid` int(11) DEFAULT NULL,
    `createDate` datetime DEFAULT NULL COMMENT '创建日期',
    PRIMARY KEY (`id`),
    KEY `fk_product_category` (`cid`),
    CONSTRAINT `fk_product_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
 */
@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 一个分类对应多个产品
     */
    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    //产品 图片  后台显示 产品的时候  需要用到  用的是单图的第一个
    @Transient
    private  Integer productImageId;

    private String name; // 产品名称

    private String subTitle; //小标题

    private Float originalPrice; //原始价格

    private Float promotePrice;  //优惠价格

    private Integer stock; //库存

    private Date createDate; //创建时间


    @Transient
    private List<ProductImage> productSingleImages;  //单个产品集合
    @Transient
    private List<ProductImage> productDetailImages; //详情图片 集合
    @Transient
    private Integer saleCount; //销量
    @Transient
    private Integer reviewCount; //累计评价

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
