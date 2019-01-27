package com.mini.service.impl;

import com.mini.dao.ReviewDao;
import com.mini.entity.Product;
import com.mini.entity.Review;
import com.mini.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-21 21:00
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    //注入dao
    @Autowired
     private ReviewDao reviewDao;

    /**
     * 获取产品下所有的评价
     * @param product
     * @return
     */

    @Override
    public List<Review> getReviews(Product product) {
        return reviewDao.findByProductOrderByIdDesc(product);
    }


    /**
     * 获取产品下所有的评价的数量
     * @param product
     * @return
     */
    @Override
    public int getReviewCount(Product product) {
        return reviewDao.countByProduct(product);
    }

    /**
     * 添加评价
     * @param review
     */
    @Override
    public void addReview(Review review) {
          reviewDao.save(review);
    }
}
