package com.taotao.portal.service;

import javax.servlet.http.HttpServletResponse;

import com.taotao.pojo.TbUser;

public interface UserService {
	
	TbUser getUserByToken(String token);
	void sendredirect(HttpServletResponse response, String redirect);

}
