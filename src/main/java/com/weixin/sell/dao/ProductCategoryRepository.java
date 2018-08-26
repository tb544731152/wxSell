package com.weixin.sell.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixin.sell.pojo.ProductCategory;
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}


