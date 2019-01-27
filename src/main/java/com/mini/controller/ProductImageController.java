package com.mini.controller;

import com.mini.Constant;
import com.mini.entity.Product;
import com.mini.entity.ProductImage;
import com.mini.fileutil.ImageUtil;
import com.mini.service.ProductImageService;
import com.mini.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hht
 * @create 2019-01-17 18:37
 *
 *
 * 图片相关的控制层
 */
@RestController
public class ProductImageController {
    //注入 Service
    @Autowired
    private ProductImageService productImageService;

    //注入 ProductService
    @Autowired
    private ProductService productService;

        @PostMapping("/productimages")
        public ProductImage addProductImage(@RequestParam("pid") Integer pid, ProductImage productImage, MultipartFile file, HttpServletRequest request) throws IOException {

            //根据 id 去查询出 产品
            Product product = productService.getProduct(pid);
            productImage.setProduct(product);

            productImageService.addProductImage(productImage);

            String folder = "img/";

            boolean is = Constant.SINGLEIMAGE.getWord().equals(productImage.getType());
            //单图和 细节图放在不同的文件夹
            if(is){
                 folder += "productSingle";
            }else {
                folder += "productDetail";
            }
            //获取真实路径
            String realPath = request.getServletContext().getRealPath(folder);

            File folderRel = new File(realPath);
            if(!folderRel.exists()){
                folderRel.mkdirs();
            }

            File getFile = new File(folderRel, productImage.getId() + ".jpg");
            //图片复制到这里
            file.transferTo(getFile);
            BufferedImage img = ImageUtil.change2jpg(getFile);
            ImageIO.write(img, "jpg", getFile);

            //获取文件名
            String fileName = getFile.getName();
            if(is){
                String imageFolder_small= request.getServletContext().getRealPath("img/productSingle_small");
                String imageFolder_middle= request.getServletContext().getRealPath("img/productSingle_middle");
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);
                f_small.getParentFile().mkdirs();
                f_middle.getParentFile().mkdirs();
                ImageUtil.resizeImage(getFile, 56, 56, f_small);
                ImageUtil.resizeImage(getFile, 217, 190, f_middle);
            }
            return productImage;

        }

    /**
     * 展示 产品对应图片的列表
     * @param productImage  接受参数
     * @param pid
     * @return
     */
        @GetMapping("/products/{pid}/productimages")
        public List<ProductImage> getProductImage(ProductImage productImage,@PathVariable("pid") Integer pid){
             //获取到产品   因为需要根据 产品去查询 该产品下面的图
            Product product = productService.getProduct(pid);
            if(product != null && productImage.getType() != null){
                return productImageService.getImagesByProduct(product,productImage.getType());

            }
            return new ArrayList<>();
        }

    /**
     * 删除 图片
     * @param id
     * @return
     */
    @DeleteMapping("/productimages/{id}")
        public String deleteProductImage(@PathVariable("id") Integer id,HttpServletRequest request){

        //先获取下 图片的 type 以便于判断删除
        ProductImage productImage = productImageService.getProductImage(id);
        String folder = "img/";

        boolean is = Constant.SINGLEIMAGE.getWord().equals(productImage.getType());
        //单图和 细节图放在不同的文件夹
        if(is){
            folder += "productSingle";
        }else {
            folder += "productDetail";
        }
        //获取真实路径
        String realPath = request.getServletContext().getRealPath(folder);
        File folderRel = new File(realPath);
        File getFile = new File(folderRel, productImage.getId() + ".jpg");
        //获取文件名
        String fileName = getFile.getName();
        getFile.delete();
        //是单图
        if(is){
            String imageFolder_small= request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle= request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.delete();
            f_middle.delete();

        }
       //删除
        String result = productImageService.deleteProductImage(id);
        return result;
        }
}
