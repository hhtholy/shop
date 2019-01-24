package com.mini.controller;

import com.mini.entity.Product;
import com.mini.entity.Property;
import com.mini.entity.PropertyValue;
import com.mini.service.ProductService;
import com.mini.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-18 18:35
 */
@RestController
public class PropertyValueController {


    //注入Service
    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ProductService productService;

    /**
     * 获取属性值 列表
     * @return
     */
    @GetMapping("/products/{pid}/propertyvalues")
      public List<PropertyValue> getPropertyValues(@PathVariable("pid") Integer pid){

        //根据 产品 id 去查询产品
        Product product = productService.getProduct(pid);
        //初始化该产品的一些属性值
         propertyValueService.init(product);

         //列出产品的属性值
        List<PropertyValue> propertyValueList = propertyValueService.getPropertyByProduct(product);
        return propertyValueList;
    }

    /**
     * 更新属性值 的值
     * @param propertyValue
     * @return
     */
    @PutMapping("/propertyvalues")
    public PropertyValue updatePropertyValue(@RequestBody PropertyValue propertyValue){

        propertyValueService.updatePropertyValue(propertyValue);

        return propertyValue;

    }
}
