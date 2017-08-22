package com.gongxm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gongxm.bean.Rules;
import com.gongxm.domain.ResponseResult;
import com.gongxm.services.RulesService;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.ServiceUtils;

@WebServlet("/addRules")
public class AddRules extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static RulesService rulesService= ServiceUtils.getRulesService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		ResponseResult result = new ResponseResult();
		result.setErrmsg("请求失败!");
		try {
			Rules rules = new Rules();
			BeanUtils.populate(rules, request.getParameterMap());
			rulesService.add(rules);
			result.setErrcode(MyConstants.SUCCESS);
			result.setErrmsg("添加规则成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = "<script type='text/javascript'>window.onload=function(){alert('"+result.getErrmsg()+"'); location.href='"+request.getContextPath()+"/rulesManagement'}</script>";
		
		resp.getWriter().write(json);
	}

}
