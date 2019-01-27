package com.mini.service;

import com.mini.entity.Product;
import com.mini.entity.ProductImage;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-17 19:24
 */
public interface ProductImageService {
      //添加图片
    public ProductImage addProductImage(ProductImage productImage);

     //删除图片
    public String deleteProductImage(Integer id);

    //获取图片 根据产品id, 以及type
    List<ProductImage> getImagesByProduct(Product product, String type);

    //获取图片  根据id
    public ProductImage getProductImage(Integer id);


}
