<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采集管理</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/CollectManagement.js"></script>
</head>
<body>
	<h1>规则列表</h1>
	<table class="table_center">
		<tr>
			<td align="center">

				<table class="table_inner" style="width: 300px">
					<tr>
						<td class="inner_td" style="width: 120px">规则ID</td>
						<td class="inner_td" style="width: 120px">规则名称</td>
						<td class="inner_td" style="width: 150px">操作</td>
					</tr>

					<c:forEach items="${rulesList}" var="rules" varStatus="vs">
						<tr>
							<td class="inner_td">${rules.id}</td>
							<td class="inner_td">${rules.rulesName}</td>
							<td class="inner_td"><input type="button" value="采集"
								onclick="collect(this)"></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3" align="center"><a class="href_none"
							href="${pageContext.request.contextPath}/">回到主页</a> <a
							class="href_none" href="${pageContext.request.contextPath}/admin">后台管理</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>