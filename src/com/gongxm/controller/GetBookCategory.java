package com.gongxm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.services.BookService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.ServiceUtils;

@WebServlet("/getBookCategory")
public class GetBookCategory extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		BookService bookService = ServiceUtils.getBookService();
		
		List<String> categories = bookService.getBookCategory();
		
		String json = "[]";
		if(categories!=null) {
			json = GsonUtils.toJson(categories);
		}
		
		writeResult(response, json);
	}

}
