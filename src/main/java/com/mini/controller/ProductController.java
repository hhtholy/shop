package com.mini.controller;

import com.mini.entity.Product;
import com.mini.service.ProductImageService;
import com.mini.service.ProductService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hht
 * @create 2019-01-16 11:44
 *
 * 产品相关的控制层
 */
@RestController
public class ProductController {

    //注入service
     @Autowired
    private ProductService productService;


     //注入 ProductImageService  用来给产品设置图片  单图
    @Autowired
    private ProductImageService productImageService;

    /**
     * 分页查询
     * @param id
     * @param currentPage
     * @param size
     * @return
     */
    @GetMapping("categories/{cid}/products")
    public Page<Product> getProducts(@PathVariable("cid") Integer id, @RequestParam(value = "currentPage",defaultValue = "0") Integer currentPage,
                                     @RequestParam(defaultValue = "2") Integer size){


        Page<Product> page = productService.getProductPage(id, currentPage, size, 2);

        //设置图片
        productImageService.setFirstImagesForProduct(page.getContent());
        return page;
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return product;
    }

    /**
     * 根据id去删除 产品
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        return productService.deleteProduct(id);

    }

    /**
     * 根据id去查询产品
     * @param id
     * @return
     */
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Integer id){
        return productService.getProduct(id);

    }

    /**
     *  更新 产品
     * @param product
     * @return
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Integer id,@RequestBody Product product){

        return  productService.updateProduct(product);
    }

}
