package com.weixin.sell.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixin.sell.pojo.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
