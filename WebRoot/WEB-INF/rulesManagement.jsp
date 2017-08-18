<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规则管理</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<h1>规则列表</h1>
	<table class="table_center">
		<tr>
			<td align="center">
				<table class="table_inner">
					<c:forEach items="${rulesList}" var="rules" varStatus="vs">
						<tr>
							<td align="center">vs.count</td>
							<td><a href="${pageContext.request.contextPath}"></a></td>
						</tr>
					</c:forEach>
					<tr>
						<td><input type="text" name="rules" /></td>
						<td><input type="button" name="add" value="添加新规则" /></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/">回到主页</a></td>
						<td><a href="${pageContext.request.contextPath}/admin">后台管理</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>