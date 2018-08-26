package com.weixin.sell.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weixin.sell.converter.OrderForm2OrderDTOConverter;
import com.weixin.sell.enums.ResultEnum;
import com.weixin.sell.exception.SellException;
import com.weixin.sell.service.BuyerService;
import com.weixin.sell.service.OrderService;
import com.weixin.sell.utils.ResultVOUtil;
import com.weixin.sell.vo.OrderDTO;
import com.weixin.sell.vo.OrderForm;
import com.weixin.sell.vo.ResultVO;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private BuyerService buyerService;

	// 创建订单
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
					bindingResult.getFieldError().getDefaultMessage());
		}

		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			throw new SellException(ResultEnum.CART_EMPTY);
		}

		OrderDTO createResult = orderService.create(orderDTO);

		Map<String, String> map = new HashMap<>();
		map.put("orderId", createResult.getOrderId());

		return ResultVOUtil.success(map);
	}

	// 订单列表
	@GetMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		if (StringUtils.isEmpty(openid)) {
			throw new SellException(ResultEnum.PARAM_ERROR);
		}

		PageRequest request = new PageRequest(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

		return ResultVOUtil.success(orderDTOPage.getContent());
	}

	// 订单详情
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
			@RequestParam("orderId") String orderId) {
		OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
		return ResultVOUtil.success(orderDTO);
	}

	// 取消订单
	@PostMapping("/cancel")
	public ResultVO cancel(@RequestParam("openid") String openid,
			@RequestParam("orderId") String orderId) {
		buyerService.cancelOrder(openid, orderId);
		return ResultVOUtil.success();
	}
}
