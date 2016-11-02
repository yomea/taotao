package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.OrderForm;

public interface OrderService {
	
	TaotaoResult createOrder(OrderForm order, HttpServletRequest request, HttpServletResponse response);

}
