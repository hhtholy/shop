package com.mini.service;

import com.mini.entity.Category;
import com.mini.entity.Property;
import com.mini.util.Page;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-15 15:12
 */
public interface PropertyService {

    //查询 属性值  一个分类下的属性值  分页
    public Page<Property> getPropertyPage(Integer cid,Integer currentPage,Integer size,int navigatePages);

    //查询 属性值  一个分类下的属性值 不分页
    public List<Property> getPropertyList(Category category);

    //添加属性
    public Property addProperty(Property property);

    //删除属性
    public String deleteProperty(Integer id);

    //根据id 获取属性
    public Property getProperty(Integer id);

    // 更新属性
    public Property updateProperty(Property property);
}
