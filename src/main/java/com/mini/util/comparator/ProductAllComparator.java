package com.mini.util.comparator;

import com.mini.entity.Product;

import java.util.Comparator;

/**
 * 综合排序 比较器  评价数量 * 销量 的形式 进行大小比较
 */
public class ProductAllComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount();
	}

}
