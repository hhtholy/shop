package com.mini.util.comparator;


import com.mini.entity.Product;

import java.util.Comparator;

/**
 *   根据价格进行比较
 */
public class ProductPriceComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return (int) (p1.getPromotePrice()-p2.getPromotePrice());
	}

}
