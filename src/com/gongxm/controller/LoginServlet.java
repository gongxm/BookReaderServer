package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "�û���½", urlPatterns = { "/loginServlet" })
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void postRequest(HttpServletResponse response, String requestJson) throws IOException {
	}

}
