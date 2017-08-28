package com.gongxm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;

@WebServlet("/getBookCategory")
public class GetBookCategory extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		BookService bookService = ServiceUtils.getBookService();
		
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, StringConstants.HTTP_REQUEST_ERROR);
		
		List<String> categories = bookService.getBookCategory();
		
		String json = "[]";
		if(categories!=null) {
			result.setErrcode(MyConstants.SUCCESS);
			result.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
			result.setResult(categories);
			json = GsonUtils.toJson(result);
		}
		
		
		writeResult(response, json);
	}

}
