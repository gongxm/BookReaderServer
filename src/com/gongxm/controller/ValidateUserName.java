package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.domain.ResponseResult;
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
		String username = request.getParameter("username");
		UserService userService = ServiceUtils.getUserService();
		User user = userService.findUserByName(username);
		
		ResponseResult result = new ResponseResult(MyConstants.FAILURE,"����ʧ��");
		
		if(user!=null){
			result.setErrmsg("���û����Ѵ���!");
		}else{
			result.setErrcode(MyConstants.SUCCESS);
			result.setErrmsg("��ϲ��,���û�������ʹ��!");
		}
		String json = GsonUtils.toJson(result);
		writeResult(response, json);
	}
}
