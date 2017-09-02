package com.gongxm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Rules;
import com.gongxm.services.RulesService;

@WebServlet("/collectManagement")
public class CollectManagement extends BaseServlet {
	private static final long serialVersionUID = 1L;


	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response, String requestJson)
			throws ServletException, IOException {
		RulesService rulesService= (RulesService) context.getBean("RulesService");

		List<Rules> rulesList = rulesService.findAll();

		request.getSession().setAttribute("rulesList", rulesList);

		request.getRequestDispatcher("/WEB-INF/collectManagement.jsp").forward(request, response);
	}

}
