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
<title>规则管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/RulesManagement.js"></script>
</head>
<body>
	<h1>规则列表</h1>
	<div>
		<div class="add_bt">
			<input type="button" value="添加新规则" id="add" />
		</div>
		<table class="table_center">
			<tr>
				<td align="center">

					<table class="table_inner">
						<tr>
							<td class="inner_td" style="width: 120px">规则ID</td>
							<td class="inner_td" style="width: 120px">规则名称</td>
							<td class="inner_td" style="width: 120px">数据源</td>
							<td class="inner_td" style="width: 180px">正则表达式</td>
							<td class="inner_td" style="width: 80px">重复匹配</td>
							<td class="inner_td" style="width: 120px">页面字符集</td>
							<td class="inner_td" style="width: 200px">操作</td>
						</tr>

						<c:forEach items="${rulesList}" var="rules" varStatus="vs">
							<tr>
								<td class="inner_td">${rules.id}</td>
								<td class="inner_td">${rules.rulesName}</td>
								<td class="inner_td">${rules.book_source}</td>
								<td class="inner_td">${rules.regex}</td>
								<td class="inner_td"><c:if test="${rules.repeat}">是</c:if>
									<c:if test="${rules.repeat==false}">否</c:if></td>
								<td class="inner_td">${rules.charset}</td>
								<td class="inner_td"><input type="button" value="编辑"
									onclick="editRules(this)"> <input type="button"
									value="删除" onclick="deleteRules(this)"> <input
									type="button" value="书籍规则" onclick="editBookRules(this)"></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="7" align="center"><a class="href_none"
								href="${pageContext.request.contextPath}/">回到主页</a> <a
								class="href_none"
								href="${pageContext.request.contextPath}/admin">后台管理</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!-- 遮盖层 -->
	<div id="over" class="over"></div>

	<!-- 弹出窗口代码 -->
	<div id="floatdiv">
		<input class="close" id="close" type="button" value="关闭" />
		<h2 id="operate">添加新规则</h2>
		<form id="rulesform" name="rulesform" action="addRules">
			<table class="table_inner table_margin table_width"
				style='margin: 0px auto'>
				<caption>列表页面规则</caption>
				<tr>
					<td class="inner_td insert_data_width">ID(无需输入)</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" readonly="readonly" name="id" id="id" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入规则名称:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="rulesName" id="rulesName" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入数据源:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="book_source" id="book_source" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入列表链接:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="baseUrl" id="baseUrl" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入通配符:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="flag" id="flag" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入开始索引:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="startIndex" id="startIndex" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入结束索引:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="endIndex" id="endIndex" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入开始区域:</td>
					<td class="inner_td insert_data_width"><input type="text"
						name="startStr" id="startStr" class="insert_data_width" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入结束区域:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="endStr" id="endStr" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入正则表达式:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="regex" id="regex" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">请输入拼接链接:</td>
					<td class="inner_td insert_data_width"><input type="text"
						class="insert_data_width" name="concatUrl" id="concatUrl" /></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">是否循环匹配</td>
					<td class="inner_td insert_data_width"><select name="repeat"
						style="width: 80px" id="repeat">
							<option value="true">是</option>
							<option value="false">否</option>
					</select></td>
				</tr>
				<tr>
					<td class="inner_td insert_data_width">页面字符集</td>
					<td class="inner_td insert_data_width"><select name="charset"
						style="width: 80px" id="charset">
							<option value="GBK">GBK</option>
							<option value="UTF-8">UTF-8</option>
					</select></td>
				</tr>

				<tr>
					<td class="inner_td insert_data_width" colspan="2"><input
						id="bt_submit" type="submit" value="添加新规则" /></td>
				</tr>

			</table>
		</form>
	</div>

</body>
</html>