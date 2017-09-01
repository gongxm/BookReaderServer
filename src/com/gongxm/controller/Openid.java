package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.domain.request.UserInfoParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.domain.response.UserResult;
import com.gongxm.services.UserService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;
import com.gongxm.utils.TextUtils;
import com.gongxm.utils.WxAuthUtil;

@WebServlet("/openid")
public class Openid extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService = ServiceUtils.getUserService();

	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws IOException {
		UserInfoParam userInfoParam = null;
		ResponseResult result = new ResponseResult(MyConstants.FAILURE, "用户信息存储失败!");
		try {
			userInfoParam = GsonUtils.fromJson(requestJson, UserInfoParam.class);
			String thirdSession = userInfoParam.getThirdSession();
			if (TextUtils.notEmpty(thirdSession)) {
				try {
					User user = userService.findUserByThirdSession(thirdSession);
					if (user != null) {
						String encryptedData = userInfoParam.getEncryptedData();
						String sessionKey = user.getSession_key();
						String iv = userInfoParam.getIv();
						String userInfo = WxAuthUtil.decodeUserInfo(encryptedData, iv, sessionKey);
						if (userInfo != null) {
							UserResult resultUser = GsonUtils.fromJson(userInfo, UserResult.class);
							user.setAvatarUrl(resultUser.getAvatarUrl());
							user.setCity(resultUser.getCity());
							user.setCountry(resultUser.getCountry());
							user.setGender(resultUser.getGender());
							user.setNickName(resultUser.getNickName());
							user.setUsername(resultUser.getNickName());
							user.setProvince(resultUser.getProvince());
							userService.update(user);
							result.setErrcode(MyConstants.SUCCESS);
							result.setErrmsg("用户信息存储成功!");
						}
					}
				} catch (Exception e) {
					result.setErrmsg(e.getMessage());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			result.setErrmsg(StringConstants.JSON_PARSE_ERROR);
		}
		String json = GsonUtils.toJson(result);
		writeResult(response, json);
	}
}
