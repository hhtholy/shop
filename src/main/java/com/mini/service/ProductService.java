package com.mini.service;

import com.mini.entity.Product;
import com.mini.entity.ProductImage;
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


}
