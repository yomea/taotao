package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.OrderForm;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.service.impl.CartServiceImpl;
import com.taotao.utils.ExceptionUtil;

@Controller
public class OrderController {
	
	@Autowired
	private CartServiceImpl  cartService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/order-cart")
	public String showOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<CartItem> cartItems = cartService.getCartItemList(request);
		
		model.addAttribute("cartList", cartItems);
		
		return "order-cart";
		
	}
	
	@RequestMapping("/order/create")
	public String createOrder(OrderForm order, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			TbUser user = (TbUser) request.getAttribute("user");
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
			TaotaoResult taotaoResult = orderService.createOrder(order, request, response);
			model.addAttribute("orderId", taotaoResult.getData().toString());
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			//这里可以使用发邮件的功能，或者发短信的功能
			ExceptionUtil.getStackTrace(e);
			return "error/exception";
		}
		
		
		
	}
	
	
}
