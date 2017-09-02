package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.UserService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;

@WebServlet("/validateUserName")
public class ValidateUserName extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws IOException {
		UserService userService = (UserService) context.getBean("userService");
		String username = request.getParameter("username");
		User user = userService.findUserByName(username);
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, "请求失败");

		if (user != null) {
			result.setErrmsg("该用户名已被占用!");
		} else {
			result.setErrcode(MyConstants.SUCCESS);
			result.setErrmsg("恭喜您,该用户名可以使用!");
		}
		String json = GsonUtils.toJson(result);
		writeResult(response, json);
	}
}
