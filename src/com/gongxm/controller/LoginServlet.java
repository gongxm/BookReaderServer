package com.gongxm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.services.UserService;
import com.gongxm.utils.MD5Util;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;

@WebServlet(description = "�û���½", urlPatterns = { "/loginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Util.MD5(password);
		UserService uService = ServiceUtils.getUserService();
		User user = uService.findUser(username, password);
		if (user == null) {
			writer.write("<h1 align='center'><font color='red' size=5>�û������������1���ص���½ҳ��</font></h1>");
			response.setHeader("refresh", "1;url=" + request.getContextPath()
					+ "/login.jsp");
			return;
		}
		if (request.getParameter(MyConstants.REMEMBER) != null) {
			Cookie cookie = new Cookie("user", MD5Util.base64Encoding(username)
					+ "_" + password);
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
		request.getSession().setAttribute("user", user);
		if (MyConstants.ROLE_ROOT.equals(user.getPermissions())) {
			writer.write("<h1 align='center'><font color='green' size=5>��½�ɹ���1���ת����̨��</font></h1>");
			response.setHeader("refresh", "1;url=" + request.getContextPath() + "/admin");
		} else {
			writer.write("<h1 align='center'><font color='green' size=5>��½�ɹ���1���ת����ҳ��</font></h1>");
			response.setHeader("refresh", "1;url=" + request.getContextPath()
					+ "/index.jsp");
		}
	}


}
