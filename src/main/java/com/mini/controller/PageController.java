package com.mini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hht
 * @create 2019-01-12 17:58
 *
 * 页面跳转的操作
 */
@Controller
public class PageController {

    /**
     * 跳转到分类管理展示列表
     * @return
     */
    @GetMapping(value = "/admin")
    public String admin(){


        return "redirect:admin_category_list";
    }
    @GetMapping("/admin_category_list")
    public String categoryList(){


        return "admin/listCategory";
    }

    /**
     * 跳转到 分类编辑
     * @return
     */
    /*http://localhost:8080/minishopadmin/admin_category_edit?id=87 就算带了参数 也不影响*/
    @GetMapping("/admin_category_edit")
    public String editCategory(){

        return "admin/editCategory";
    }


    /**
     * 跳转到属性管理 页面
     * @return
     */
    @GetMapping("/admin_property_list")
    public String propertyList(){

        return "admin/listProperty";
    }

    /**
     *  跳转到属性编辑界面
     * @return
     */

    @GetMapping("/admin_property_edit")
    public String editProperty(){

        return "admin/editProperty";
    }


    /**
     * 跳转到 产品管理界面
     * @return
     */
    @GetMapping("/admin_product_list")
    public String productList(){

        return "admin/listProduct";
    }


    /**
     * 跳转到 产品编辑界面
     * @return
     */
    @GetMapping("/admin_product_edit")
    public String productEdit(){

        return "admin/editProduct";
    }

    /**
     * 图片展示列表 （包括添加）
     * @return
     */
    @GetMapping("/admin_productImage_list")
    public String productImageList(){

        return "admin/listProductImage";
    }

    /**
     * 跳转到 产品属性值   设置
     * @return
     */
    @GetMapping("/admin_propertyValue_edit")
    public String propertyValueSet(){

        return "admin/editPropertyValue";
    }


    /**
     * 跳转到 用户展示列表
     * @return
     */
    @GetMapping("/admin_user_list")
    public String listUser(){

        return "admin/listUser";
    }

    /**
     * admin_order_list
     */
    @GetMapping("/admin_order_list")
    public String listOrders(){

      return "admin/admin_order_list";
    }

}
