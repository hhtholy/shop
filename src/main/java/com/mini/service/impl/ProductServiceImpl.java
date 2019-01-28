package com.mini.service.impl;

import com.mini.Constant;
import com.mini.dao.CategoryDao;
import com.mini.dao.ProductDao;
import com.mini.entity.*;
import com.mini.service.OrderItemService;
import com.mini.service.ProductImageService;
import com.mini.service.ProductService;
import com.mini.service.ReviewService;
import com.mini.util.Page;
import org.hibernate.engine.jdbc.Size;
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


    //注入ProductService
    @Autowired
    private ProductImageService productImageService;


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
     *   点击立即购买
     * @param pid
     * @param num
     * @param user
     * @return
     */
    @Override
    public Integer buyitnow(Integer pid, Integer num, User user) {
        //返回的结果
        Integer result = null;

        //首先 根据产品id 获取产品
        Product productResult = getProduct(pid);

        //根据 用户  获取用户所在的订单项
        List<OrderItem> orderItemsByUser = orderItemService.getOrderItemsByUser(user);

        //是否已经在订单项的判断
        boolean flag = false;


        //如果取得了数据的话
        if(orderItemsByUser != null && orderItemsByUser.size() > 0){

            //遍历订单项 数据  比对 现在立即购买的产品是不是 订单项中 已经存在了
            ///存在的话 那么只需要数量 加1 即可
            for (OrderItem item:orderItemsByUser){
                if(item.getProduct().getId().equals(pid)){
                      //订单项该商品的数量 加上现在添加上的数量即可   也就是num
                    Integer number = item.getNumber();
                     number = number + num;
                     item.setNumber(number);
                     //更新item的信息
                    orderItemService.updateOrderItem(item);
                    flag = true;
                    result = item.getId();
                }

            }
        }

        //如果该用户并没有添加过该商品的话  那么创建新的订单项
          if(!flag){
              OrderItem item = new OrderItem();
              item.setNumber(num);
              item.setUser(user);
              item.setProduct(productResult);
              result = item.getId();

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

    /**
     * 给产品设置图片  单图
     * @param product
     */
    @Override
    public void setFirstImageForProduct(Product product) {

        //根据产品的去获取 单图 获取的是一个列表
        List<ProductImage> productImages = productImageService.getImagesByProduct(product, Constant.SINGLEIMAGE.getWord());

        if(productImages != null && productImages.size() > 0){
            product.setProductImageId(productImages.get(0).getId());
        }
    }

    /**
     * 给产品设置图片
     * @param list
     */
    @Override
    public void setFirstImagesForProduct(List<Product> list) {
        for (Product product:list){
            setFirstImageForProduct(product);
        }
    }

    /**
     * 根据分类获取 该分类下的产品
     * @param category
     * @return
     */

    @Override
    public List<Product> getProductByCategory(Category category) {
        return productDao.findByCategory(category);
    }

    /**
     *  根据 关键字 去获取对应的产品 结果
     * @param keyword
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public Page<Product> search(String keyword, int currentPage, int size) {

         Pageable pageable =  new PageRequest(currentPage,size, Sort.Direction.DESC,"id");

        org.springframework.data.domain.Page<Product> pageResult = productDao.findByNameLike("%" + keyword + "%", pageable);

         return  new Page<>(pageResult,5);

    }


}
