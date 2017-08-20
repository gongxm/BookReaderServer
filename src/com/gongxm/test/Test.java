package com.gongxm.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Rules;
import com.gongxm.controller.BaseServlet;
import com.gongxm.utils.ServiceUtils;
@WebServlet("/test")
public class Test extends BaseServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		Rules rules = new Rules();
		rules.setUrl("http://www.baidu.com");
		rules.setStartStr("abc");
		rules.setEndStr("cmd");
		rules.setStartIndex(2);
		rules.setRegex("1[3456789]\\d{9}");
		rules.setEndIndex(20);
		rules.setRepeat(false);
		rules.setCurrent(true);
		ServiceUtils.getRulesService().add(rules);
	}

}
