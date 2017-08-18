package com.gongxm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.utils.MyConstants;

@WebFilter(filterName="AdminFilter",urlPatterns={"/admin","/rulesManagement"})
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("-----------------AdminFilter-----------------------");
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest) req;
			response = (HttpServletResponse) resp;
		} catch (Exception e) {
			throw new RuntimeException("non http request or response");
		}
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			response.getWriter().write("对不起，您还没有登陆，请先登陆再操作！即将转到登陆页面！");
			response.setHeader("refresh", "1;url="+request.getContextPath()+"/login.jsp");
			return;
		}
		if(!MyConstants.ROLE_ROOT.equals(user.getPermissions())){
			response.getWriter().write("对不起，您的权限不足，无法进行该操作！即将转到主页！");
			response.setHeader("refresh", "1;url="+request.getContextPath());
			return;
		}
		if(request.getRequestURI().contains("/admin")){
			request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
