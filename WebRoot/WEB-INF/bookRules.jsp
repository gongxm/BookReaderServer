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
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bookRules.js"></script>
</head>
<body>
	<h1>编辑书籍信息规则</h1>

	<form action="">
		<table class="center_table">
			<caption>编辑书籍信息规则</caption>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id"
					value="${bookRules.id}" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title" id="title"
					value="${bookRules.regexs[0]}" /></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" id="author"
					value="${bookRules.regexs[1]}" /></td>
			</tr>
			<tr>
				<td>类别</td>
				<td><input type="text" name="category" id="category"
					value="${bookRules.regexs[2]}" /></td>
			</tr>
			<tr>
				<td>状态</td>
				<td><input type="text" name="status" id="status"
					value="${bookRules.regexs[3]}" /></td>
			</tr>
			<tr>
				<td>封面</td>
				<td><input type="text" name="cover" id="cover"
					value="${bookRules.regexs[4]}" /></td>
			</tr>
			<tr>
				<td>简介</td>
				<td><input type="text" name="shortIntroduce"
					id="shortIntroduce" value="${bookRules.regexs[5]}" /></td>
			</tr>
			<tr>
				<td>目录链接正则</td>
				<td><input type="text" name="list_link" id="list_link"
					value="${bookRules.regexs[6]}" /></td>
			</tr>
			<tr>
				<td>目录标题正则</td>
				<td><input type="text" name="list_title" id="list_title"
					value="${bookRules.regexs[7]}" /></td>
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
				<td><input type="text" name="charset" id="charset"
					value="${bookRules.charset}" /></td>
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
				<td colspan="2" class="center"><input type="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>