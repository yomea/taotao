package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {

	TaotaoResult cartItem(String id, Integer num, HttpServletRequest request);
	
	List<CartItem> showCart(HttpServletRequest request);
	
	TaotaoResult deleteCartItem(String id, HttpServletRequest request, HttpServletResponse response);
	
}
