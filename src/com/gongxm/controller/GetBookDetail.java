package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Book;
import com.gongxm.domain.request.IDParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;

@WebServlet("/getBookDetail")
public class GetBookDetail extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, StringConstants.HTTP_REQUEST_ERROR);
		try {
			IDParam param = GsonUtils.fromJson(requestJson, IDParam.class);
			if (param != null) {
				int id = param.getId();
				if (id > 0) {
					BookService bookService = ServiceUtils.getBookService();
					Book book = bookService.findOne(id);
					if (book != null) {
						result.setErrcode(MyConstants.SUCCESS);
						result.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
						result.setResult(book);
					} else {
						result.setErrmsg(StringConstants.BOOK_NOT_FOUND);
					}
				} else {
					result.setErrmsg(StringConstants.BOOK_ID_ERROR);
				}
			} else {
				result.setErrmsg(StringConstants.HTTP_REQUEST_PARAM_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrmsg(StringConstants.JSON_PARSE_ERROR);
		}

		String json = GsonUtils.toJson(result);
		writeResult(response, json);
	}

}
