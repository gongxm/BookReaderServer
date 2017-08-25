package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.domain.OpenIdResult;
import com.gongxm.domain.request.LoginParam;
import com.gongxm.domain.response.LoginResult;
import com.gongxm.services.UserService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.HttpUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;
import com.gongxm.utils.StringConstants;
import com.gongxm.utils.TextUtils;
import com.gongxm.utils.TimeUtils;
import com.gongxm.utils.WxAuthUtil;

@WebServlet("/wxlogin")
public class WXLogin extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void postRequest(HttpServletRequest request,HttpServletResponse response, String requestJson) throws IOException {
		LoginParam loginParam = null;
		LoginResult loginResult = new LoginResult();
		try {
			loginParam = GsonUtils.fromJson(requestJson, LoginParam.class);

			String code = loginParam.getJs_code();

			if (TextUtils.notEmpty(code)) {
				String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + MyConstants.APPID + "&secret="
						+ MyConstants.APP_SECRET + "&js_code=" + code + "&grant_type=" + MyConstants.GRANT_TYPE;
				try {
					String data = HttpUtils.executGet(url);

					OpenIdResult result = GsonUtils.fromJson(data, OpenIdResult.class);

					int errcode = result.getErrcode();
					UserService userService = ServiceUtils.getUserService();

					// 如果错误码为0, 创建一个用户
					if (errcode == 0) {
						String openid = result.getOpenid();
						// 先查询用户是否存在
						User user = userService.findUserByOpenId(openid);

						String session_key = result.getSession_key();
						if (user == null) {
							String thirdSession = WxAuthUtil.create3rdSession(openid, session_key,
									(long) result.getExpires_in());
							user = new User();
							user.setOpenid(openid);
							user.setSession_key(session_key);
							user.setPermissions(MyConstants.ROLE_USER);
							user.setRegistTime(TimeUtils.getCurrentTime());
							user.setThirdSession(thirdSession);

							userService.addUser(user);

							result = new OpenIdResult();
							// 3rdSessionId返回给客户端
							loginResult.setThirdSession(thirdSession);
							loginResult.setErrcode(MyConstants.SUCCESS);
						} else {
							String thirdSession = user.getThirdSession();
							loginResult.setThirdSession(thirdSession);
							loginResult.setErrcode(MyConstants.SUCCESS);
							
							user.setOpenid(openid);
							user.setSession_key(session_key);
							userService.update(user);
						}
					} else {
						loginResult.setErrmsg(StringConstants.WX_HTTP_REQUEST_ERROR);
					}

				} catch (Exception e) {
					e.printStackTrace();
					loginResult.setErrmsg(e.getMessage());
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			loginResult.setErrmsg(StringConstants.JSON_PARSE_ERROR);
		}
		String json = GsonUtils.toJson(loginResult);
		writeResult(response, json);
	}
}
