package com.gongxm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.utils.MyConstants;
import com.gongxm.utils.StringUtils;

/**
 * BaseServlet: Servlet»ùÀà
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BaseServlet() {
        super();
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream is = request.getInputStream();
		String requestJson = StringUtils.readStream(is, MyConstants.DEFAULT_ENCODING);
		response.setContentType("application/json");
		postRequest(response, requestJson);
	}

	public abstract void postRequest(HttpServletResponse response, String requestJson) throws IOException;

	public void writeResult(HttpServletResponse response, String json) throws IOException {
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
