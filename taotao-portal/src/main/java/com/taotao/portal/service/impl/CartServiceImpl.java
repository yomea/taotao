package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class CartServiceImpl implements CartService {
	
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	@Override
	public TaotaoResult cartItem(String id, Integer num, HttpServletRequest request) {

		CartItem cartItem = null;

		List<CartItem> cartItems = getCartItemList(request);

		for (CartItem cartItem2 : cartItems) {
			if (cartItem2.getId().equals(id)) {
				cartItem2.setNum(cartItem2.getNum() + 1);
				cartItem = cartItem2;
				break;
			}
		}

		if (cartItem == null) {
			String result = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + id);

			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, TbItem.class);

			if (taotaoResult.getStatus() == 200) {

				TbItem item = (TbItem) taotaoResult.getData();

				cartItem = new CartItem();
				cartItem.setId(item.getId().toString());
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage().split(",")[0]);

			}
			cartItems.add(cartItem);
			
		}
		

		return TaotaoResult.ok(cartItems);
	}

	public List<CartItem> getCartItemList(HttpServletRequest request) {
		List<CartItem> cartItems = null;
		String result = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (result == null) {
			return new ArrayList<CartItem>();
		}
		cartItems = JsonUtils.jsonToList(result, List.class, CartItem.class);
		return cartItems;

	}

	@Override
	public List<CartItem> showCart(HttpServletRequest request) {
		return this.getCartItemList(request);
	}

	@Override
	public TaotaoResult deleteCartItem(String id, HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cartItems = this.getCartItemList(request);
		
		for (CartItem cartItem : cartItems) {
			
			if(cartItem.getId().equals(id)) {
				cartItems.remove(cartItem);
				break;
			}
			
		}
		
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItems), true);
		
		return TaotaoResult.ok();
	}

}
