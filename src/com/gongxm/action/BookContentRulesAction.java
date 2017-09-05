package com.gongxm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gongxm.bean.BookChapterContentRules;
import com.gongxm.bean.BookListRules;
import com.gongxm.dao.Dao;
import com.gongxm.domain.request.IDParam;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookListRulesService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.StringConstants;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/action")
@ParentPackage("struts-default")
public class BookContentRulesAction extends BaseAction implements ModelDriven<BookChapterContentRules> {

	private static final long serialVersionUID = 1L;
	BookChapterContentRules rules = new BookChapterContentRules();

	@Autowired
	BookListRulesService rulesService;
	@Autowired
	private Dao<BookChapterContentRules> contentRulesDao;

	@Override
	public BookChapterContentRules getModel() {
		return rules;
	}

	// 更新内容页规则
	@Action("updateContentRules")
	public void updateContentRules() {
		ResponseResult result = new ResponseResult();
		if (rules != null) {
			BookListRules bookListRules = rulesService.findById(rules.getBook_list_rules_id());
			if (bookListRules != null) {
				bookListRules.setContentRules(rules);
				rulesService.update(bookListRules);
				result.setErrcode(MyConstants.SUCCESS);
				result.setErrmsg("修改规则成功!");
			}
		}
		String json = GsonUtils.toJson(result);
		write(json);
	}

	// 显示内容页规则
	@Action("showContentRules")
	public void showContentRules() {
		ResponseResult result = new ResponseResult();
		try {
			IDParam param = GsonUtils.fromJson(getData(), IDParam.class);
			int id = param.getId();
			if (id > 0) {
				BookChapterContentRules rules = contentRulesDao.findById(id);
				if (rules != null) {
					result.setResult(rules);
					result.setSuccess();
				} else {
					result.setErrmsg("找不到指定的规则!");
				}
			} else {
				result.setErrmsg(StringConstants.RULES_ID_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrmsg(StringConstants.JSON_PARSE_ERROR);
		}
		String json = GsonUtils.parseToJson(result);
		write(json);
	}

	
}
