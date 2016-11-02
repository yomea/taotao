package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable String itemId, @RequestParam(defaultValue="1") int num, HttpServletRequest request, HttpServletResponse response) {
		
		TaotaoResult taotaoResult = service.cartItem(itemId, num, request);
		
		String cookieValue = JsonUtils.objectToJson(taotaoResult.getData());
		
		CookieUtils.setCookie(request, response, "TT_CART", cookieValue, true);
		
		return "cartadd";
	}
	
	@RequestMapping("/cart/cart")
	public String showCart(HttpServletRequest request, Model model) {
		
		List<CartItem> cartItems = service.showCart(request);
		
		model.addAttribute("cartList", cartItems);
		
		return "cart";
		
		
	}
	
	@RequestMapping("/cart/delete/{itemId}")
	public String delItem(@PathVariable("itemId") String id, HttpServletRequest request, HttpServletResponse response) {
		
		service.deleteCartItem(id, request, response);
		
		
		return "redirect:/cart/cart.html";
		
	}
	
	

}
