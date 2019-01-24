package com.mini.service;

import com.mini.entity.Product;
import com.mini.entity.Review;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-21 20:57
 *
 * 评价相关的业务接口
 */
public interface ReviewService {

    //根据产品 获取该产品下的评价
    public List<Review> getReviews(Product product);

    //根据产品 获取评价数量
    public int getReviewCount(Product product);

    //添加评价
    public void addReview(Review review);


}
