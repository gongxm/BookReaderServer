package com.gongxm.action;

import javax.servlet.http.HttpServletRequest;

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
import com.gongxm.dao.BookDao;
import com.gongxm.dao.BookInfoAndChapterListRulesDao;
import com.gongxm.dao.Dao;
import com.gongxm.domain.response.ResponseResult;
import com.gongxm.services.BookListRulesService;
import com.gongxm.utils.GsonUtils;
import com.gongxm.utils.MyConstants;
import com.gongxm.utils.TextUtils;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@Namespace("/action")
@ParentPackage("struts-default")
public class BookRulesAction extends BaseAction implements ModelDriven<BookInfoAndChapterListRules> {

	private static final long serialVersionUID = 1L;

	BookInfoAndChapterListRules rules = new BookInfoAndChapterListRules();

	@Autowired
	BookListRulesService rulesService;

	@Autowired
	BookInfoAndChapterListRulesDao bookRulesDao;

	// 根据BookListRules的ID获取BookInfoAndChapterListRules的内容
	@Action(value = "showBookRules", results = { @Result(name = "success", location = "/WEB-INF/bookRules.jsp") })
	public String showBookRules() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("id");
		if (TextUtils.notEmpty(sid)) {
			int id = Integer.parseInt(sid);
			BookListRules rules = rulesService.findById(id);
			if (rules != null) {
				BookInfoAndChapterListRules bookRules = rules.getRules();
				ServletActionContext.getRequest().getSession().setAttribute("bookRules", bookRules);
			}
		}
		return SUCCESS;
	}

	// 更新或者添加新规则
	@Action("updateBookRules")
	public void updateBookRules() {
		ResponseResult result = new ResponseResult();
		if (rules != null) {
			BookInfoAndChapterListRules oldRules = bookRulesDao.findById(rules.getId());
			if(oldRules==null) {
				bookRulesDao.add(rules);
			}else {
				BookInfoAndChapterListRules data = fillData(rules, oldRules);
				bookRulesDao.update(data);
			}
			result.setErrcode(MyConstants.SUCCESS);
			result.setErrmsg("修改规则成功!");
		}
		String json = GsonUtils.toJson(result);
		write(json);

	}

	// 填充数据

	private BookInfoAndChapterListRules fillData(BookInfoAndChapterListRules src, BookInfoAndChapterListRules dest) {
		dest.setAuthorRegex(src.getAuthorRegex());
		dest.setCategoryRegex(src.getCategoryRegex());
		dest.setCharset(src.getCharset());
		dest.setConcatUrl(src.getConcatUrl());
		dest.setCoverRegex(src.getCoverRegex());
		dest.setEndStr(src.getEndStr());
		dest.setListLinkRegex(src.getListLinkRegex());
		dest.setListTitleRegex(src.getListTitleRegex());
		dest.setShortIntroduceRegex(src.getShortIntroduceRegex());
		dest.setStartStr(src.getStartStr());
		dest.setStatusRegex(src.getStatusRegex());
		dest.setTitleRegex(src.getTitleRegex());
		dest.setUseBookLink(src.isUseBookLink());
		return dest;
	}

	@Override
	public BookInfoAndChapterListRules getModel() {
		return rules;
	}

}
