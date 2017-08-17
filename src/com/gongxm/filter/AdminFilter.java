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

@WebFilter(filterName="AdminFilter",urlPatterns="/admin")
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
			response.getWriter().write("�Բ�������û�е�½�����ȵ�½�ٲ���������ת����½ҳ�棡");
			response.setHeader("refresh", "1;url="+request.getContextPath()+"/login.jsp");
			return;
		}
		if(!MyConstants.ROLE_ROOT.equals(user.getPermissions())){
			response.getWriter().write("�Բ�������Ȩ�޲��㣬�޷����иò���������ת����ҳ��");
			response.setHeader("refresh", "1;url="+request.getContextPath());
			return;
		}
		request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
	}

}
