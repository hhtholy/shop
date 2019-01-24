package com.mini.service.impl;

import com.mini.dao.PropertyValueDao;
import com.mini.entity.Product;
import com.mini.entity.Property;
import com.mini.entity.PropertyValue;
import com.mini.service.PropertyService;
import com.mini.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-18 18:34
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    //注入 dao
    @Autowired
    private PropertyValueDao propertyValueDao;

    //注入 属性service

    @Autowired
    private PropertyService propertyService;


    /**
     * 初始化属性值
     * @param product
     */
    @Override
    public void init(Product product) {
          //查询出该产品下的所属的分类 下的属性   1分类和产品有关系 ---获取分类  2分类和属性有关系
        List<Property> propertys = propertyService.getPropertyList(product.getCategory());

        //遍历属性  电视分类下属性 有颜色 重量等  电视1（产品） 电视2（产品） 产品都应该有对应的属性

        //现在的绑定模式 属性1(颜色) + 具体产品1 对应一个属性值 （红色）; 属性2（重量） + 具体产品1 对应一个属性值（10kg）
        for (Property property:propertys){

            PropertyValue propertyValue = getByPropertyAndProduct(property, product);

            if(propertyValue ==  null){
                    propertyValue = new PropertyValue();
                    propertyValue.setProduct(product);
                    propertyValue.setProperty(property);
                    propertyValueDao.save(propertyValue);  //暂时还没绑定值
            }
        }
    }

    /**
     *  根据属性 和 产品  查询属性值      颜色 电视1    红（结果）
     * @param property
     * @param product
     * @return
     */
    @Override
    public PropertyValue getByPropertyAndProduct(Property property, Product product) {
        return propertyValueDao.findByPropertyAndProduct(property,product);
    }

    /**
     * 根据 产品获取属性值列表
     * @param product
     * @return
     */
    @Override
    public List<PropertyValue> getPropertyByProduct(Product product) {
        return propertyValueDao.findByProductOrderByIdDesc(product);
    }

    /**
     * 更新属性值
     * @param propertyValue
     */
    @Override
    public void updatePropertyValue(PropertyValue propertyValue) {
        propertyValueDao.save(propertyValue);
    }
}
