package com.taotao.portal.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.utils.HttpClientUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	@Override
	public TbUser getUserByToken(String token) {
		
		String result = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, TbUser.class);
		
		return (TbUser) taotaoResult.getData();
	}

	@Override
	public void sendredirect(HttpServletResponse response, String redirect) {
		try {
			response.sendRedirect(SSO_BASE_URL + SSO_PAGE_LOGIN + "?redirect=" + redirect);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

	

	
	
}
