<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>编辑书籍信息规则</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/bookRules.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bookRules.js"></script>
</head>
<body>
	<h1>编辑书籍信息规则</h1>

	<div class="edit">
		<input type="button" value="编辑内容页规则" id="edit" />
	</div>

	<form name="bookRulesForm" id="bookRulesForm">
		<table class="center_table">
			<caption>编辑书籍信息规则</caption>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id"
					value="${bookRules.id}" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>标题</td>
				<td><input type="text" name="titleRegex" id="titleRegex"
					value="${bookRules.titleRegex}" /></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="authorRegex" id="authorRegex"
					value="${bookRules.authorRegex}" /></td>
			</tr>
			<tr>
				<td>类别</td>
				<td><input type="text" name="categoryRegex" id="categoryRegex"
					value="${bookRules.categoryRegex}" /></td>
			</tr>
			<tr>
				<td>状态</td>
				<td><input type="text" name="statusRegex" id="statusRegex"
					value="${bookRules.statusRegex}" /></td>
			</tr>
			<tr>
				<td>封面</td>
				<td><input type="text" name="coverRegex" id="coverRegex"
					value="${bookRules.coverRegex}" /></td>
			</tr>
			<tr>
				<td>简介</td>
				<td><input type="text" name="shortIntroduceRegex"
					id="shortIntroduceRegex" value="${bookRules.shortIntroduceRegex}" /></td>
			</tr>
			<tr>
				<td>目录链接正则</td>
				<td><input type="text" name="listLinkRegex" id="listLinkRegex"
					value="${bookRules.listLinkRegex}" /></td>
			</tr>
			<tr>
				<td>目录标题正则</td>
				<td><input type="text" name="listTitleRegex"
					id="listTitleRegex" value="${bookRules.listTitleRegex}" /></td>
			</tr>
			<tr>
				<td>开始区域</td>
				<td><input type="text" name="startStr" id="startStr"
					value="${bookRules.startStr}" /></td>
			</tr>
			<tr>
				<td>结束区域</td>
				<td><input type="text" name="endStr" id="endStr"
					value="${bookRules.endStr}" /></td>
			</tr>
			<tr>
				<td>页面字符集</td>
				<td><select name="charset" style="width: 80px" id="charset">
						<c:if test="${bookRules.charset=='GBK'||bookRules.charset==''}">
							<option value="GBK" selected="selected">GBK</option>
							<option value="UTF-8">UTF-8</option>
						</c:if>
						<c:if test="${bookRules.charset=='UTF-8'}">
							<option value="GBK">GBK</option>
							<option value="UTF-8" selected="selected">UTF-8</option>
						</c:if>
				</select></td>
			</tr>
			<tr>
				<td>拼接链接</td>
				<td><input type="text" name="concatUrl" id="concatUrl"
					value="${bookRules.concatUrl}" /></td>
			</tr>
			<tr>
				<td>是否使用书籍链接进行拼接</td>
				<td><c:if test="${bookRules.useBookLink}">
						<input type="radio" name="useBookLink" value="true"
							checked="checked">是
					<input type="radio" name="useBookLink" value="false">否
			</c:if> <c:if test="${bookRules.useBookLink==false}">
						<input type="radio" name="useBookLink" value="true">是
					<input type="radio" name="useBookLink" value="false"
							checked="checked">否
			</c:if>
			</tr>
			<tr>
				<td colspan="2" class="center"><input type="submit" value="修改" />
					<input type="button" value="取消" id="cancel" /></td>
			</tr>
		</table>
	</form>





	<!-- 遮盖层 -->
	<div id="over" class="over"></div>

	<!-- 弹出窗口代码 -->
	<div id="floatdiv" style="height: 300px">
		<input class="close" id="close" type="button" value="关闭" />
		<h2>编辑内容页规则</h2>
		<form id="contentRules" name="contentRules"
			action="updateContentRules">
			<table class="table_inner table_margin table_width"
				style='margin: 0px auto'>
				<caption>编辑内容页规则</caption>
				<tr>
					<td>ID(无需修改)</td>
					<td><input type="text" id="c_id" name="id" /></td>
				</tr>
				<tr>
					<td>开始区域</td>
					<td><input type="text" id="c_start" name="startStr" /></td>
				</tr>
				<tr>
					<td>结束区域</td>
					<td><input type="text" id="c_end" name="endStr" /></td>
				</tr>

				<tr>
					<td align="center" colspan="2"><input type="submit" value="确定修改"
						id="editContentRules" /> <input type="button" value="取消修改"
						id="cancelEdit" /></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>