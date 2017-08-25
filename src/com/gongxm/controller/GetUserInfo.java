package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.domain.request.ThirdSessionParam;
import com.gongxm.domain.response.UserInfo;
import com.gongxm.services.UserService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;
import com.gongxm.utils.TextUtils;
@WebServlet("/getUserInfo")
public class GetUserInfo extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		UserInfo info = new UserInfo();
		try {
			ThirdSessionParam param = GsonUtils.fromJson(requestJson, ThirdSessionParam.class);
			if(param!=null){
				String thirdSession = param.getThirdSession();
				if(TextUtils.notEmpty(thirdSession)){
					UserService userService = ServiceUtils.getUserService();
					User user = userService.findUserByThirdSession(thirdSession);
					if(user!=null){
						info.setUser(user);
						info.setErrcode(MyConstants.SUCCESS);
						info.setErrmsg("获取用户信息成功!");
					}else{
						info.setErrmsg("用户不存在!");
					}
				}else{
					info.setErrmsg("thirdSession为空");
				}
			}else{
				info.setErrmsg("参数为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setErrmsg(StringConstants.JSON_PARSE_ERROR);
		}
		String json = GsonUtils.toJson(info);
		writeResult(response, json);
	}

}
