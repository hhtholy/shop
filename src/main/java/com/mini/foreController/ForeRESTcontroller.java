package com.mini.foreController;

import com.mini.Constant;
import com.mini.controller.ProductController;
import com.mini.entity.*;
import com.mini.service.*;
import com.mini.util.Page;
import com.mini.util.Result;
import com.mini.util.comparator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author hht
 * @create 2019-01-21 10:53
 */
@RestController
public class ForeRESTcontroller {

    //注入UserService
    @Autowired
    private UserService userService;

    //注入ProductService
    @Autowired
    private ProductService productService;

    //productImageService
    @Autowired
    private ProductImageService productImageService;

    //PropertyValueService
    @Autowired
    private PropertyValueService propertyValueService;

    //ReViewSErvice
    @Autowired
    private ReviewService reviewService;

    //注入CategorySevice

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/foreregister")
      public Object register(@RequestBody User user){

        //获取 用户注册输入的用户名
        String username = user.getName();
        //获取用户输入的密码
        String password = user.getPassword();

        //用户的名字进行一下转义

        /**
         *
         *String s = HtmlUtils.htmlEscape("<div>hello world</div><p>&nbsp;</p>");
         *System.out.println(s);
         *String s2 = HtmlUtils.htmlUnescape(s);
         *System.out.println(s2);
         * //转义后
         * &lt;div&gt;hello world&lt;/div&gt;&lt;p&gt;&amp;nbsp;&lt;/p&gt;
         * //转换回去
         * <div>hello world</div><p>&nbsp;</p>
         */
        //主要是名字 需要在前端显示  否则的话  不需要这样做
        // 显示的话 可能会出现 恶意 写 比如 alert('123')的情况 那这样的话 界面可能就会出问题
        HtmlUtils.htmlEscape(username);

        //检测 用户是不是已经注册过
        if(userService.isExist(user)){
            return Result.fail("用户名已经被使用，请使用其他的用户名");
        }

        //正常注册的话
        userService.addUser(user);

        return Result.success();
      }

    /**
     * 用户登录
     * @param user
     * @return
     */
      @PostMapping("/forelogin")
      public Object login(@RequestBody User user, HttpSession session){

          //对用户输入的名字进行下转义
          String name = user.getName();
          user.setName(HtmlUtils.htmlEscape(name));
          User result = userService.login(user);

          if(result == null){
              return Result.fail("账号或者密码错误");
          }
          //把用户数据放在session中
          session.setAttribute("user",result);
          return Result.success();
      }

    /**
     * 展示产品的详情信息
     * @param pid
     * @return
     */

    @GetMapping("/foreproduct/{pid}")
      public Object product(@PathVariable("pid") Integer pid){
          //根据pid  获取产品
          Product product = productService.getProduct(pid);

          //设置产品的单图 和 详情图 集合
            product.setProductSingleImages(productImageService.getImagesByProduct(product, Constant.SINGLEIMAGE.getWord()));
            product.setProductDetailImages(productImageService.getImagesByProduct(product, Constant.DETAILIMAGE.getWord()));

            //查询出 产品的属性值
          List<PropertyValue> propertys = propertyValueService.getPropertyByProduct(product);
           //查询出 产品的评价 针对内容
          List<Review> reviews = reviewService.getReviews(product);

           //设置产品的 评价数量和销量
            productService.setReviewsAndSaleCount(product);

            //设置产品的单图 针对显示
            productService.setFirstImageForProduct(product);

          //把数据放在map中
          HashMap<String, Object> map = new HashMap<>();

          map.put("product",product);  //产品
          map.put("pvalues",propertys);  //产品的属性值
          map.put("reviews",reviews); //产品的评价
          return Result.success(map);
      }

    /**
     * 检测用户是不是已经登录了
     * @param session
     * @return
     */
    @GetMapping("/forecheckLogin")
      public Object checkUserIsLongin(HttpSession session){
        User user = (User)  session.getAttribute("user");
        return user == null?Result.fail("用户没有登录"):Result.success();
      }

    /**
     *
     * @param cid
     * @param sortType
     * @return
     */
      @GetMapping("/forecategory/{cid}")
      public Object category(@PathVariable("cid") Integer cid,String sortType){

          //首先根据id 查询出分类信息
          Category category = categoryService.get(cid);
          categoryService.setProductsForCategory(category);
          //产品的数据 因为图片等信息  是没有持久化到数据库的  因此这里需要进行set值
          //因为单图信息是需要展示的
          productService.setFirstImagesForProduct(category.getProducts());

          //为产品 设置上 评价数量和销量
          productService.setReviewsAndSaleCount(category.getProducts());

          //移除死循环问题   把产品中 category的引用去除
          categoryService.removeCategoryForProduct(category);
          if(sortType != null){
              switch (sortType){
                  case "review":
                      Collections.sort(category.getProducts(),new ProductReviewComparator());
                      break;
                  case "date" :
                      Collections.sort(category.getProducts(),new ProductDateComparator());
                      break;

                  case "saleCount" :
                      Collections.sort(category.getProducts(),new ProductSaleCountComparator());
                      break;

                  case "price":
                      Collections.sort(category.getProducts(),new ProductPriceComparator());
                      break;

                  case "all":
                      Collections.sort(category.getProducts(),new ProductAllComparator());
                      break;

              }


          }
             return category;

      }

    /**
     * 根据关键字 去查询产品 显示出对应结果
     * @param keyword
     * @return
     */

    @GetMapping("/search")
      public  Object search(@RequestParam("keyword") String keyword){

        //查询结果
        Page<Product> searchResult = productService.search(keyword, 0, 20);

        List<Product> content = searchResult.getContent();

        //设置产品的图片  单图显示
        productService.setFirstImagesForProduct(content);

        //设置 产品的 销量和评价数量
        productService.setReviewsAndSaleCount(content);


        return searchResult;
      }

    /**
     * 立即购买  点击立即购买
     * @param num
     * @return
     */
    @GetMapping("/buyitnow")
      public Object buyitnow(Integer pid,Integer num,HttpSession session){

        //获取 用户的数据
        User user = (User) session.getAttribute("user");

        //
        Integer buyitnow = productService.buyitnow(pid, num, user);


        return buyitnow;
      }

}
