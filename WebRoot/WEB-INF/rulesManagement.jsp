<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>规则管理</title>
</head>
<body>
	<h1>规则列表</h1>
	<table>
		<c:forEach items="${rulesList}" var="rules" varStatus="vs">
			<tr>
				<td align="center">vs.count</td>
				<td><a href="${pageContext.request.contextPath}"></a></td>
			</tr>
		</c:forEach>
		<tr>
			<td><a href="${pageContext.request.contextPath}/">回到主页</a></td>
			<td><a href="${pageContext.request.contextPath}/admin">后台管理</a></td>
		</tr>
	</table>
</body>
</html>