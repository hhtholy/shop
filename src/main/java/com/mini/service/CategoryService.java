package com.mini.service;

import com.mini.entity.Category;
import com.mini.util.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hht
 * @create 2019-01-13 14:09
 */

public interface CategoryService {

    //查找分类
    public List<Category> getCategories();

    //分类分页
    public Page<Category> getCategoryPage(Integer currentPage,Integer size,int navigatePages);

    //添加分类

    public void addCategory(Category category);

    //删除分类

    public String delete(Integer id);

    //更新分类
    public Category update(Category category);

    //根据id去查询一个分类实体
    public Category get(Integer id);
}
