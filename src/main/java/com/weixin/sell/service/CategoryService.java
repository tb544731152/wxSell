package com.weixin.sell.service;

import java.util.List;

import com.weixin.sell.pojo.ProductCategory;

public interface CategoryService {
	ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
