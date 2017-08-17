package com.gongxm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.services.UserService;
import com.gongxm.utils.BeanFillUtils;
import com.gongxm.utils.MD5Util;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;

/**
 * 用户注册
 */
@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService uService = ServiceUtils.getUserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		User user=BeanFillUtils.fillBean(request);
		if(!password.equals(repassword)){
			writer.write("<h1 align='center'><font color='red' size=5>两次输入的密码不一致！2秒后转到注册页面！</font><br/></h1>");
			request.getSession().setAttribute("user", user);
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/regist.jsp");
			return;
		}
		User oldUser=uService.findUserByName(username);
		if(oldUser!=null){
			writer.write("<h1 align='center'><font color='red' size=5>该用户名已存在，请使用其他用户名注册！2秒后转到注册页面！</font><br/></h1>");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/regist.jsp");
			return;
		}
		User newUser=new User();
		newUser.setUsername(username);
		newUser.setPermissions(MyConstants.ROLE_USER);
		newUser.setPassword(MD5Util.MD5(password));
		uService.addUser(newUser);
		writer.write("<h1 align='center'><font color='green' size=5>注册成功！2秒后转到登陆页面！</font><br/></h1>");
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
