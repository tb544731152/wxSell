package com.weixin.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.weixin.sell.vo.OrderDTO;

public interface PayService {
	public PayResponse create(OrderDTO orederDTO);

    public PayResponse notify(String notifyData);

    public RefundResponse refund(OrderDTO orederDTO);


}
