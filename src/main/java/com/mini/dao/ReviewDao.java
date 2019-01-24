package com.mini.dao;

import com.mini.entity.Product;
import com.mini.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-21 20:53
 */
public interface ReviewDao extends JpaRepository<Review,Integer> {

    //根据 产品 查询 该产品下所有的评价

    public List<Review> findByProductOrderByIdDesc(Product product);
    //根据产品 查询 该产品下的所有评价的数量
    int countByProduct(Product product);
}
