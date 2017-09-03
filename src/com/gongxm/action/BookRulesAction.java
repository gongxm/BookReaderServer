package com.gongxm.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gongxm.bean.BookInfoAndChapterListRules;
import com.gongxm.bean.BookListRules;
import com.gongxm.domain.request.IDParam;
import com.gongxm.services.BookListRulesService;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/action")
@ParentPackage("struts-default")
public class BookRulesAction extends BaseAction implements ModelDriven<IDParam> {

	private static final long serialVersionUID = 1L;

	@Autowired
	BookListRulesService rulesService;

	IDParam param = new IDParam();

	// 根据BookListRules的ID获取BookInfoAndChapterListRules的内容
	@Action(value = "showBookRules", results = { @Result(name = "success", location = "/WEB-INF/bookRules.jsp") })
	public String showBookRules() {

		int id = param.getId();

		BookListRules rules = rulesService.findById(id);
		if (rules != null) {
			BookInfoAndChapterListRules bookRules = rules.getRules();
			ServletActionContext.getRequest().getSession().setAttribute("bookRules", bookRules);
			return SUCCESS;
		}
		return SUCCESS;
	}

	@Override
	public IDParam getModel() {
		return param;
	}

}
