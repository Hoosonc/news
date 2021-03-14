package com.news.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("u") == null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");  
			PrintWriter out = response.getWriter();
			out.print("<script language=\"javascript\">alert('登录后才能发布评论！');window.location.href='/news/newsinfo/list'</script>");
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

}
