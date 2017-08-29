package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.test.MyTest;
@WebServlet("/collect")
public class Collect extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if("1".equals(id)) {
			MyTest.demo1();
		}else if("2".equals(id)){
			MyTest.demo2();
		}else if("3".equals(id)) {
			MyTest.demo3();
		}
	}
}
