package com.mini.service.impl;

import com.mini.dao.CategoryDao;
import com.mini.dao.ProductDao;
import com.mini.entity.Category;
import com.mini.entity.OrderItem;
import com.mini.entity.Product;
import com.mini.entity.Property;
import com.mini.service.OrderItemService;
import com.mini.service.ProductService;
import com.mini.service.ReviewService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author hht
 * @create 2019-01-16 14:25
 */
@Service
public class ProductServiceImpl implements ProductService {


     //注入OrderItemService
    @Autowired
    private OrderItemService orderItemService;

    //注入 ReviewService
    @Autowired
    private ReviewService reviewService;


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

    /**
     * 获取产品的销量
     *
     * 注意一点的是  需要获取销量的话  应该是所有的订单项中的数量累加  产品 * 数量 累加
     * @param product
     * @return
     */
    @Override
    public Integer getSaleCount(Product product) {

        //获取 订单项中 拥有该产品的所有订单项
        List<OrderItem> results = orderItemService.getOrderItemByProduct(product);

        int count = 0;
        for (OrderItem item:results){
             //首先确定是创建了订单的  同时是已经付款的
            if(item.getOrder() != null && item.getOrder().getPayDate() != null){
                    count += item.getNumber();
            }

        }
        return count;
    }

    /**
     * 为产品设置 评价数量和销量
     * @param product
     */
    @Override
    public void setReviewsAndSaleCount(Product product) {
        Integer saleCount = getSaleCount(product);

        //根据产品获取 评价数量
        int reviewCount = reviewService.getReviewCount(product);

        product.setReviewCount(reviewCount);
        product.setSaleCount(saleCount);
    }

    /**
     * 设置 产品的销量和评价数量
     * @param products
     */

    @Override
    public void setReviewsAndSaleCount(List<Product> products) {
          for (Product p:products){
              setReviewsAndSaleCount(p);
          }
    }
}
