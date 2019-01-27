package com.mini.dao;

import com.mini.entity.Category;
import com.mini.entity.Product;
import com.mini.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-15 13:53
 */
public interface ProductDao extends JpaRepository<Product,Integer>{

        //根据分类查询出对应的产品  分页的方式
       Page<Product> findByCategory(Category category, Pageable pageable);

       List<Product> findByCategory(Category category);



}
