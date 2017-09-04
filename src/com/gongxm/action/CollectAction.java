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

import com.gongxm.bean.BookListRules;
import com.gongxm.domain.request.IDParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookChapterService;
import com.gongxm.services.BookListRulesService;
import com.gongxm.services.BookListService;
import com.gongxm.utils.CollectUtils;
import com.gongxm.utils.GsonUtils;

@Controller
@Scope("prototype")
@Namespace("/action")
@ParentPackage("struts-default")
public class CollectAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	BookListRulesService rulesService;
	@Autowired
	BookListService service;
	@Autowired
	BookChapterService chapterService;

	// 显示所有的规则
	@Action(value = "collect", results = { @Result(name = "success", location = "/WEB-INF/collectManagement.jsp") })
	public String showAllRules() {
		List<BookListRules> rulesList = rulesService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("rulesList", rulesList);
		return SUCCESS;
	}

	@Action("startCollect")
	public void collect() {
		ResponseResult result = new ResponseResult();

		if (CollectUtils.threadCount > 0) {
			result.setErrmsg("正在采集中...");
		} else {
			IDParam param = GsonUtils.fromJson(getData(), IDParam.class);
			if (param != null) {
				int id = param.getId();
				if (id > 0) {
					new Thread() {
						public void run() {
							BookListRules bookListRules = rulesService.findById(id);
							CollectUtils.collectBookList(service, chapterService, bookListRules);
						};
					}.start();
					result.setSuccess();
				}
			}
		}
		String json = GsonUtils.toJson(result);
		write(json);
	}

}
