package com.mini.controller;

import com.mini.entity.Category;
import com.mini.fileutil.ImageUtil;
import com.mini.service.CategoryService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author hht
 * @create 2019-01-12 18:06
 */
@RestController
public class CategoryController {


    //注入service

    @Autowired
    private CategoryService categoryService;


   /*  @GetMapping("/categories")
    public List<Category> getCategories(){

        return categoryService.getCategories();
    }*/

    /**
     * 设置导航页码 为3 分页查询
     * @param currentPage
     * @param size
     * @return
     */
       @GetMapping("/categories")
    public Page<Category> getCategories(@RequestParam(value = "currentPage", defaultValue="0") Integer currentPage,
                                        @RequestParam(value = "size", defaultValue="4") Integer size){

         return categoryService.getCategoryPage(currentPage,size,5);
    }

    /**
     * 添加 分类
     * @param category
     * @param file
     * @param request
     */
    @PostMapping("/categories")
    public Category addCategory(Category category, @RequestParam("image") MultipartFile file, HttpServletRequest  request) throws IOException {

        //经过这一部分操作后  id就有值了
        categoryService.addCategory(category);
        //文件上传操作
        uploadFile(category,file,request);

        return category;

    }

    /**
     * 上传文件
     * @param category
     * @param file
     * @param request
     */
    public  void uploadFile(Category category, MultipartFile file, HttpServletRequest request) throws IOException {
       // getServletContext().getRealPath("/");获得绝对路径。

        String realPath = request.getServletContext().getRealPath("img/category");

        File imageFolder = new File(realPath);

        File fileImage = new File(imageFolder, category.getId() + ".jpg");

        if(!imageFolder.exists()){
            imageFolder.mkdirs();
        }

         // 文件复制到服务器上
        file.transferTo(fileImage);
        //转换
        BufferedImage bufferedImage = ImageUtil.change2jpg(fileImage);
         // 转换为jpg格式图片
       ImageIO.write(bufferedImage,"jpg",fileImage);
    }

    /**
     * 根据id删除
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable("id") Integer id,HttpServletRequest request){

        String delete = categoryService.delete(id);

        ///删除文件
        String realPath = request.getServletContext().getRealPath("img/category");

        File file = new File(realPath, id + ".jpg");

         if(file.exists()){
             file.delete();

         }
        return delete;
    }

    /**
     * 根据 id 去查询分类
     * @param id
     * @return
     */

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") Integer id){

            return categoryService.get(id);
    }

    /**
     * 根据id去更新
     * @param category
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable("id") Integer id, Category category,MultipartFile file, HttpServletRequest  request) throws IOException {
        Category updateResult = categoryService.update(category);
        //如果选择了文件的话
        if(file != null){
            //文件上传操作
            uploadFile(category,file,request);

        }

         return updateResult;
    }


}
