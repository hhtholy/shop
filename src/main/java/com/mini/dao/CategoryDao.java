package com.mini.dao;

import com.mini.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hht
 * @create 2019-01-12 17:12
 */
public interface CategoryDao extends JpaRepository<Category,Integer> {
}
