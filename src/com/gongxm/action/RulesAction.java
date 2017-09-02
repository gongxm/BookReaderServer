package com.gongxm.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gongxm.bean.Rules;
import com.gongxm.services.RulesService;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
@Namespace("/action")
@ParentPackage("struts-default")
public class RulesAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	RulesService rulesService;
	
	@Action(value="showAllRules",results= {@Result(name="success",location="/WEB-INF/collectManagement.jsp")})
	public String showAllRules() {
		List<Rules> rulesList = rulesService.findAll();
		
		ServletActionContext.getRequest().getSession().setAttribute("rulesList", rulesList);

		return SUCCESS;
	}

}
