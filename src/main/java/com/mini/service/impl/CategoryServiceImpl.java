package com.mini.service.impl;

import com.mini.dao.CategoryDao;
import com.mini.entity.Category;
import com.mini.service.CategoryService;
import com.mini.util.Page;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
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
 * @create 2019-01-13 14:13
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    //注入  CategoryDao

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    /**
     * 分页 查询
     * @param currentPage  当前页
     * @param size   每页显示的条数
     * @param navigatePages   导航决定的页码数
     * @return
     */

    @Override
    public Page<Category> getCategoryPage(Integer currentPage, Integer size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Pageable pageable = new PageRequest(currentPage, size, sort);

        org.springframework.data.domain.Page<Category> pageResult = categoryDao.findAll(pageable);

        return new Page<>(pageResult,navigatePages);
    }

    /**
     * 添加分类
     * @param category
     */

    @Override
    public void addCategory(Category category) {
       categoryDao.save(category);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @Override
    public String delete(Integer id) {
        try {
            categoryDao.deleteById(id);
        }catch (Exception e){

            return "failure";
        }
        return "success";
    }

    /**
     *  进行分类的更新
     * @param category
     * @return
     */
    @Override
    public Category update(Category category) {

        Category result = null;
        try {

            result = categoryDao.save(category);

        }catch (Exception e){
              result = null;

        }
              return  result;
    }

    /**
     * 根据id 去查询实体
     * @param id
     * @return
     */

    @Override
    public Category get(Integer id) {
        //SpringBoot2.0+的版本 findOne方法有变化
        Category category = new Category();
        category.setId(id);
        Example<Category> example = Example.of(category);
        Optional<Category> categoryOptional = categoryDao.findOne(example);
        Category result = null;
        //如果在查询到值的情况下
        if(categoryOptional.isPresent()){
            result = categoryOptional.get();
        }else {
            System.out.println("没有查询到值");
        }
        return result;
    }
}
