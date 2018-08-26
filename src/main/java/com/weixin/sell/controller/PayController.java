package com.weixin.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.weixin.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.weixin.sell.enums.ResultEnum;
import com.weixin.sell.exception.SellException;
import com.weixin.sell.service.OrderService;
import com.weixin.sell.vo.OrderDTO;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(value = "orderId", required = false) String orderId,
                              @RequestParam(value = "returnUrl", required = false) String returnUrl,
                              Map<String, Object> map) {
        //1.查询订单;
        OrderDTO order = orderService.findOne(orderId);
        if (order == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付请求;
        PayResponse payResponse = payService.create(order);
        map.put("payResponse", payResponse);
        ModelAndView model = new ModelAndView("pay/create");
        map.put("returnUrl", returnUrl);
        model.addAllObjects(map);
        return model;
    }

    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //告知微信处理结果;
        return new ModelAndView("/pay/success");
    }
}
