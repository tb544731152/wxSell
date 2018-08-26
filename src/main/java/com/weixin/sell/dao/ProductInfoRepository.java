package com.weixin.sell.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixin.sell.pojo.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
