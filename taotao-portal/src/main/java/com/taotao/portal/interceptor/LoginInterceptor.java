package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.utils.CookieUtils;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService service;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// ModelAndView返回之后执行
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		//执行handler之后调用
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		//执行handler之前调用，再次对用户是否登陆做判断
		//拿到cookie的token值
		String token = CookieUtils.getCookieValue(arg0, "TT_TOKEN");
		TbUser user = service.getUserByToken(token);
		if(user == null) {
			service.sendredirect(arg1, arg0.getRequestURL().toString());
			
			return false;
		}
		
		arg0.setAttribute("user", user);
		
		return true;
	}

}
