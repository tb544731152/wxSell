package com.weixin.sell.service;


import com.weixin.sell.vo.OrderDTO;


public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
