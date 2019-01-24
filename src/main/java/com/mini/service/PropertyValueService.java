package com.mini.service;

import com.mini.entity.Product;
import com.mini.entity.Property;
import com.mini.entity.PropertyValue;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-18 18:33
 */
public interface PropertyValueService {


    //初始化 属性值
    public void  init(Product product);

    //获取属性值   根据属性和  产品（属于哪款产品）
    public PropertyValue getByPropertyAndProduct(Property property,Product product);

    //获取属性值列表
    public  List<PropertyValue> getPropertyByProduct(Product product);

    //更新属性值
    public void updatePropertyValue(PropertyValue propertyValue);
}
