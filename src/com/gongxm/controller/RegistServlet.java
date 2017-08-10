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
import com.gongxm.utils.ServiceUtils;

/**
 * �û�ע��
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
			writer.write("<h1 align='center'><font color='red' size=5>������������벻һ�£�2���ת��ע��ҳ�棡</font><br/></h1>");
			request.getSession().setAttribute("user", user);
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/regist.jsp");
			return;
		}
		User oldUser=uService.findUserByName(username);
		if(oldUser!=null){
			writer.write("<h1 align='center'><font color='red' size=5>���û����Ѵ��ڣ���ʹ�������û���ע�ᣡ2���ת��ע��ҳ�棡</font><br/></h1>");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/regist.jsp");
			return;
		}
		user=new User();
		user.setUsername(username);
		user.setPassword(MD5Util.MD5(password));
		uService.addUser(user);
		writer.write("<h1 align='center'><font color='green' size=5>ע��ɹ���2���ת����½ҳ�棡</font><br/></h1>");
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
