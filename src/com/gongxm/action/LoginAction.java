package com.gongxm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gongxm.bean.User;
import com.gongxm.domain.OpenIdResult;
import com.gongxm.domain.request.LoginParam;
import com.gongxm.domain.response.LoginResult;
import com.gongxm.services.UserService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.HttpUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.StringConstants;
import com.gongxm.utils.TextUtils;
import com.gongxm.utils.TimeUtils;
import com.gongxm.utils.WxAuthUtil;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class LoginAction extends BaseAction implements ModelDriven<LoginParam>{
	private static final long serialVersionUID = 1L;

	@Autowired
	UserService userService;

	LoginParam loginParam = new LoginParam();

	@Action("wxlogin")
	public void wxlogin() {
		System.out.println("------------------");
		LoginResult loginResult = new LoginResult();
		String code = loginParam.getJs_code();
		System.out.println("code = " + code);
		if (TextUtils.notEmpty(code)) {
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + MyConstants.APPID + "&secret="
					+ MyConstants.APP_SECRET + "&js_code=" + code + "&grant_type=" + MyConstants.GRANT_TYPE;
			try {
				String data = HttpUtils.executGet(url);

				OpenIdResult result = GsonUtils.fromJson(data, OpenIdResult.class);

				int errcode = result.getErrcode();

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

			String json = GsonUtils.parseToJson(loginResult);
			write(json);
		}

	}

	@Override
	public LoginParam getModel() {
		return loginParam;
	}

}
