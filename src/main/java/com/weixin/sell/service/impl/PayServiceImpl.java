package com.weixin.sell.service.impl;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.weixin.sell.enums.ResultEnum;
import com.weixin.sell.exception.SellException;
import com.weixin.sell.service.OrderService;
import com.weixin.sell.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.weixin.sell.service.PayService;
import com.weixin.sell.vo.OrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Service
public class PayServiceImpl implements PayService{
	private static String ORDER_NAME = "微信点餐订单";
	
	@Autowired
	private BestPayServiceImpl bestPayServiceImpl;

    @Autowired
    private OrderService orderServiceImpl;

	@Override
	public PayResponse create(OrderDTO orderDTO) {
		PayRequest payRequest = new PayRequest();
		payRequest.setOpenid(orderDTO.getBuyerOpenid());
		payRequest.setOrderName(ORDER_NAME);
		payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
		payRequest.setOrderId(orderDTO.getOrderId());
		payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
		PayResponse payResponse = bestPayServiceImpl.pay(payRequest);
		return payResponse;
	}

    @Override
    public PayResponse notify(String notifyData) {
	    //验证签名;---SDK以及处理过了
        //查询支付结果---SDK以及处理过了
	    //处理微信异步通知返回结果，处理XML结果；
        PayResponse payResponse = bestPayServiceImpl.asyncNotify(notifyData);
        OrderDTO orderDTO = orderServiceImpl.findOne(payResponse.getOrderId());
        //判断订单是否存在;
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //验证支付金额---double不能直接比较，因为有精度问题;
        if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //如果前两步没问题，修改订单状态;
        OrderDTO paid = orderServiceImpl.paid(orderDTO);
        //异步通知结果;
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
	    RefundRequest refundRequest = new RefundRequest();
	    refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
	    refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        RefundResponse refundResponse = bestPayServiceImpl.refund(refundRequest);
        return refundResponse;
    }
}
