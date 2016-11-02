package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderForm;
import com.taotao.order.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService service;

	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody(required=true) OrderForm orderForm) {
		
		return service.createOrder(orderForm, orderForm.getOrderItems(), orderForm.getOrderShipping());
		
		
	}
	
	
}
