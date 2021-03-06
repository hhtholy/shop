package com.mini.service;

import com.mini.entity.Category;
import com.mini.entity.Product;
import com.mini.entity.ProductImage;
import com.mini.entity.User;
import com.mini.util.Page;

import java.util.List;

public interface ProductService {

    //分页查询 分页信息
    public Page<Product> getProductPage(Integer cid,Integer currentPage,Integer size,Integer navigateNum);

    //添加产品

    public Product addProduct(Product product);

    //删除产品
    public String deleteProduct(Integer id);

    //根据id 获取产品
    public Product getProduct(Integer id);

    //更新产品
    public Product updateProduct(Product product);

    //点击产品信息上的立即购买
    public Integer buyitnow(Integer pid, Integer num, User user);



    //获取该产品的销量

    public Integer getSaleCount(Product product);


    //为产品设置销量 和 评价数量
    public void setReviewsAndSaleCount(Product product);
    public void setReviewsAndSaleCount(List<Product> products);

    //给产品设置图片
    public void setFirstImageForProduct(Product product);

    public void setFirstImagesForProduct(List<Product> list);


    //根据分类去查询  分类下的产品
    public List<Product> getProductByCategory(Category category);

    //根据关键词去查询出 产品结果
     public Page<Product> search(String keyword,int currentPage, int size);
}
