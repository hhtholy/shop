package com.mini.dao;

import com.mini.entity.Product;
import com.mini.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-17 19:26
 */
public interface ProductImageDao extends JpaRepository<ProductImage,Integer> {

    public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product,String type);
}
