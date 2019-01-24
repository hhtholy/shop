package com.mini.service.impl;

import com.mini.dao.CategoryDao;
import com.mini.dao.PropertyDao;
import com.mini.entity.Category;
import com.mini.entity.Property;
import com.mini.service.PropertyService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.net.ftp.FtpClient;

import java.util.List;
import java.util.Optional;

/**
 * @author hht
 * @create 2019-01-15 15:15
 */
@Service
public class PropertyServiceImpl implements PropertyService {


    //注入 propertydao
    @Autowired
    private PropertyDao propertyDao;

    //注入 Categorydao 根据cid去查询分类

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 分页查询 一个分类下所有属性
     * @param cid
     * @param currentPage
     * @param size
     * @param navigatePages
     * @return
     */
    @Override
    public Page<Property> getPropertyPage(Integer cid, Integer currentPage, Integer size, int navigatePages) {

        //SpringBoot2.0+的版本 findOne方法有变化
        Category category = new Category();
        category.setId(cid);
        Example<Category> example = Example.of(category);
        Optional<Category> categoryOptional = categoryDao.findOne(example);
        Category result = null;
        //如果在查询到值的情况下
        if(categoryOptional.isPresent()){
            result = categoryOptional.get();
        }else {
            System.out.println("没有查询到值");
        }

        //创建分页对象
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Pageable pageable = new PageRequest(currentPage, size, sort);
        org.springframework.data.domain.Page<Property> pageFromJpa = propertyDao.findByCategory(category, pageable);

        return new Page<>(pageFromJpa,navigatePages);
    }

    /**
     * 根据 分类查询 对应的属性 不分页的情况
     * @param category
     * @return
     */
    @Override
    public List<Property> getPropertyList(Category category) {
        return propertyDao.findByCategory(category);
    }

    /**
     * 添加属性
     * @param property
     * @return
     */
    @Override
    public Property addProperty(Property property) {
        Property result = propertyDao.save(property);
        return result;
    }

    /**
     * 删除 属性
     * @param id
     * @return
     */

    @Override
    public String deleteProperty(Integer id) {

        String result = null;
        try {
            propertyDao.deleteById(id);

            result = "success";

        } catch (Exception e){

            result = "failure";
        }
        return result;
    }

    /**
     * 根据id去查询属性
     * @param id
     * @return
     */
    @Override
    public Property getProperty(Integer id) {

        Property property = new Property();

        property.setId(id);

        Example<Property> example = Example.of(property);

        Optional<Property> result = propertyDao.findOne(example);

        Property resultGet = null;

        if(result.isPresent()){

            resultGet = result.get();
        }else {

            System.out.println("没有查询到值");
        }



        return resultGet;
    }

    /**
     * 更新属性
     * @param property
     * @return
     */

    @Override
    public Property updateProperty(Property property) {

        Property result = null;

        try {
            result = propertyDao.save(property);
        }catch (Exception e){
            result = null;

        }
        return result;
    }
}
