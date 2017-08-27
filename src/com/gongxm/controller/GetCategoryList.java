package com.gongxm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Book;
import com.gongxm.domain.CategoryItem;
import com.gongxm.domain.request.GetCategoryListParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;

@WebServlet("/getCategoryList")
public class GetCategoryList extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		ResponseResult resp = new ResponseResult(MyConstants.FAILURE,StringConstants.HTTP_REQUEST_ERROR);
		try {
			GetCategoryListParam param = GsonUtils.fromJson(requestJson, GetCategoryListParam.class);
			if (param != null) {
				String category = param.getCategory();
				int currentPage = param.getCurrentPage();
				int pageSize = param.getPageSize();
				BookService bookService = ServiceUtils.getBookService();
				List<Book> books = bookService.getCategoryList(category,currentPage,pageSize);
				if(books!=null && books.size()>0){
					List<CategoryItem> items = new ArrayList<CategoryItem>();
					for (Book book : books) {
						CategoryItem item = new CategoryItem(book);
						items.add(item);
					}
					resp.setResult(items);
				}
				resp.setErrcode(MyConstants.SUCCESS);
				resp.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
			}else{
				resp.setErrmsg(StringConstants.HTTP_REQUEST_PARAM_ERROR);
			}

		} catch (Exception e) {
			resp.setErrmsg(StringConstants.JSON_PARSE_ERROR);
		}
		
		String json = GsonUtils.toJson(resp);
		
		writeResult(response, json);

	}

}
