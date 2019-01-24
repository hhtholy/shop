package com.mini.dao;

import com.mini.entity.Product;
import com.mini.entity.ProductImage;
import com.mini.entity.Property;
import com.mini.entity.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-18 18:34
 */
public interface PropertyValueDao extends JpaRepository<PropertyValue,Integer> {
    //根据产品 去查询属性值
    List<PropertyValue> findByProductOrderByIdDesc(Product product);
    //根据产品和属性 去获取属性值
    PropertyValue findByPropertyAndProduct(Property property,Product product);

}
