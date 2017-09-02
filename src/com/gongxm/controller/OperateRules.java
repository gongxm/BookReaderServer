package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gongxm.bean.Rules;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.RulesService;
import com.gongxm.utils.MyConstants;

@WebServlet("/operateRules")
public class OperateRules extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RulesService rulesService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		 WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext()); 
		 rulesService = (RulesService) context.getBean("rulesService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		ResponseResult result = new ResponseResult();
		result.setErrmsg("请求失败!");
		String operate = request.getParameter("operate");
		if ("add".equals(operate)) {
			try {
				Rules rules = new Rules();
				BeanUtils.populate(rules, request.getParameterMap());
				rulesService.add(rules);
				result.setErrcode(MyConstants.SUCCESS);
				result.setErrmsg("添加规则成功!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String json = "<script type='text/javascript'>window.onload=function(){alert('" + result.getErrmsg()
					+ "'); location.href='" + request.getContextPath() + "/rulesManagement'}</script>";

			resp.getWriter().write(json);
		} else if ("update".equals(operate)) {
			try {
				Rules rules = new Rules();
				BeanUtils.populate(rules, request.getParameterMap());
				rulesService.update(rules);
				result.setErrcode(MyConstants.SUCCESS);
				result.setErrmsg("修改规则成功!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String json = "<script type='text/javascript'>window.onload=function(){alert('" + result.getErrmsg()
					+ "'); location.href='" + request.getContextPath() + "/rulesManagement'}</script>";

			resp.getWriter().write(json);
		} else if ("delete".equals(operate)) {
			try {
				Rules rules = new Rules();
				BeanUtils.populate(rules, request.getParameterMap());
				rulesService.delete(rules);
				result.setErrcode(MyConstants.SUCCESS);
				result.setErrmsg("删除规则成功!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String json = "<script type='text/javascript'>window.onload=function(){alert('" + result.getErrmsg()
					+ "'); location.href='" + request.getContextPath() + "/rulesManagement'}</script>";

			resp.getWriter().write(json);
		} else {
			result.setErrmsg("错误的操作类型!");
			String json = "<script type='text/javascript'>window.onload=function(){alert('" + result.getErrmsg()
					+ "'); location.href='" + request.getContextPath() + "/rulesManagement'}</script>";

			resp.getWriter().write(json);
		}

	}

}
