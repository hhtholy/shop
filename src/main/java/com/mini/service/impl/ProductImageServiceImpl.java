package com.mini.service.impl;

import com.mini.Constant;
import com.mini.dao.ProductImageDao;
import com.mini.entity.Product;
import com.mini.entity.ProductImage;
import com.mini.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author hht
 * @create 2019-01-17 19:39
            */
    @Service
    public class ProductImageServiceImpl implements ProductImageService {

    //注入dao
    @Autowired
    private ProductImageDao productImageDao;
    /**
     * 添加操作
     * @param productImage
     * @return
     */
    @Override
    public ProductImage addProductImage(ProductImage productImage) {
        return productImageDao.save(productImage);
    }

    /**
     * 根据id删除图片
     * @param id
     * @return
     */
    @Override
    public String deleteProductImage(Integer id) {
        String result = null;
         try {
            productImageDao.deleteById(id);
            result = "success";
         }catch(Exception e){
             result = "failure";
        }
         return result;
    }


    /**
     * 查询图片列表  根据 产品id 以及 type
     * @param product
     * @param type
     * @return
     */

    @Override
    public List<ProductImage> listImages(Product product, String type) {
        return productImageDao.findByProductAndTypeOrderByIdDesc(product,type);
    }

    /**
     *   根据 id 去获取 图片
     * @param id
     * @return
     */
    @Override
    public ProductImage getProductImage(Integer id) {

        ProductImage productImage = new ProductImage();

        productImage.setId(id);
        Example<ProductImage> example = Example.of(productImage);
        Optional<ProductImage> optional = productImageDao.findOne(example);
        ProductImage result = null;
        if(optional.isPresent()){
            result = optional.get();
        }

        return result;
    }

    /**
     * 给产品设置图片  单图
     * @param product
     */
  @Override
    public void setFirstImageForProduct(Product product) {

        //根据产品的去获取 单图 获取的是一个列表
        List<ProductImage> productImages = listImages(product, Constant.SINGLEIMAGE.toString());

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


}
