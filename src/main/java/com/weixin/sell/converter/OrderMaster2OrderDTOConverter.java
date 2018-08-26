package com.weixin.sell.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.weixin.sell.pojo.OrderMaster;
import com.weixin.sell.vo.OrderDTO;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
    	ArrayList<OrderDTO> result = new ArrayList<>();
    	for (OrderMaster orderMaster : orderMasterList) {
    		OrderDTO orderDTO = convert(orderMaster);
    		result.add(orderDTO);
		}
    	return result;
    }
}
