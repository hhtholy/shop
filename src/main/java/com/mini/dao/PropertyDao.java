package com.mini.dao;

import com.mini.entity.Category;
import com.mini.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-15 13:53
 */
public interface PropertyDao extends JpaRepository<Property,Integer>{

        //查询一个分类下的所有属性 分页的方式
       Page<Property> findByCategory(Category category, Pageable pageable);

        //不分页的方式
       List<Property> findByCategory(Category category);
}
