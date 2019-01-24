package com.mini.service.impl;

import com.mini.dao.CategoryDao;
import com.mini.dao.ProductDao;
import com.mini.entity.Category;
import com.mini.entity.Product;
import com.mini.entity.Property;
import com.mini.service.ProductService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author hht
 * @create 2019-01-16 14:25
 */
@Service
public class ProductServiceImpl implements ProductService {



    //注入dao
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    /**
     *  分页查询
     * @param cid
     * @param currentPage
     * @param size
     * @param navigateNum
     * @return
     */

    @Override
    public Page<Product> getProductPage(Integer cid, Integer currentPage, Integer size, Integer navigateNum) {

        //先查询出 cid的 分类
        Category category = new Category();
        category.setId(cid);
        Example<Category> example = Example.of(category);
        Optional<Category> optional = categoryDao.findOne(example);
        Category result = null;
        //如果说查询到值的话
        if(optional.isPresent()){
            result = optional.get();
        }else{

            result = null;
        }
       //查询 产品
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(currentPage,size,sort);
        org.springframework.data.domain.Page<Product> pageFromJpa = productDao.findByCategory(result, pageable);


        return new Page<Product>(pageFromJpa,navigateNum);
    }

    /**
     * 添加产品
     * @param product
     * @return
     */
    @Override
    public Product addProduct(Product product) {
        Product result = productDao.save(product);
        return result;
    }

    /**
     *  根据id删除 产品
     * @param id
     * @return
     */
    @Override
    public String deleteProduct(Integer id) {

        String result = null;
        try {
             productDao.deleteById(id);
             result = "success";
         }catch (Exception e){
            result = "failure";

         }
        return result;
    }

    /**
     * 根据id 获取产品
     * @param id
     * @return
     */
    @Override
    public Product getProduct(Integer id) {

        //SpringBoot 2.0+ 查询方法进行了变化
        //先查询出 cid的 分类
        Product product = new Product();
        product.setId(id);
        Example<Product> example = Example.of(product);
        Optional<Product> optional = productDao.findOne(example);
        Product result = null;
        //如果说查询到值的话
        if(optional.isPresent()){
            result = optional.get();
        }else{

            result = null;
        }
         return result;


    }

    /**
     * 更新 产品
     * @param product
     * @return
     */
    @Override
    public Product updateProduct(Product product) {
        Product result = null;
        try {
           result = productDao.save(product);
        }catch (Exception e){

            result = null;
        }
        return result;
    }
}
