package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.BookChapter;
import com.gongxm.bean.BookChapterContent;
import com.gongxm.domain.request.IDParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookChapterService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;
import com.gongxm.utils.TextUtils;

//获取章节内容
@WebServlet("/getBookChapter")
public class GetBookChapter extends BaseServlet {

	/**
	 * 
	 */
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
					BookChapterService service = ServiceUtils.getBookChapterService();
					BookChapter chapter = service.findOne(id);
					if (chapter != null) {
						if (chapter.getStatus()==MyConstants.BOOK_COLLECTED) {
							BookChapterContent content = chapter.getChapterContent();
							String text = content.getContent();
							if(TextUtils.isEmpty(text)) {
								text = "";
							}
							result.setResult(text);
							result.setErrcode(MyConstants.SUCCESS);
							result.setErrmsg(StringConstants.HTTP_REQUEST_SUCCESS);
						} else {
							result.setErrmsg(StringConstants.BOOK_CHAPTER_UNCOLLECT);
						}
					} else {
						result.setErrmsg(StringConstants.BOOK_CHAPTER_NOT_FOUND);
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

		String json = GsonUtils.parseToJson(result);
		writeResult(response, json);
	}

}
