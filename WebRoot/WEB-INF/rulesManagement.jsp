<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规则管理</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/RulesManagement.js"></script>
<style type="text/css">
td {
	border: 1px dotted #8000ff;
}
</style>
</head>
<body>
	<h1>规则列表</h1>
	<table class="table_center">
		<tr>
			<td align="center">

				<form id="rules-form" action="${pageContext.request.contextPath}/operateRules?operate=add" method="post" onsubmit="return addRules()">

					<table class="table_inner" style="table-layout: fixed;">
						<tr>
							<td style="width: 120px" align="center">规则ID</td>
							<td style="width: 200px" align="center">采集URL</td>
							<td style="width: 180px" align="center">开始索引</td>
							<td style="width: 180px" align="center">结束索引</td>
							<td style="width: 180px" align="center">开始区域</td>
							<td style="width: 180px" align="center">结束区域</td>
							<td style="width: 180px" align="center">正则表达式</td>
							<td style="width: 80px" align="center">重复匹配</td>
							<td style="width: 120px" align="center">采集当前页面</td>
							<td style="width: 150px" align="center">操作</td>
						</tr>

						<c:forEach items="${rulesList}" var="rules" varStatus="vs">
							<tr>
								<td align="center">${rules.id}</td>
								<td align="center">${rules.url}</td>
								<td align="center">${rules.startIndex}</td>
								<td align="center">${rules.endIndex}</td>
								<td align="center">${rules.startStr}</td>
								<td align="center">${rules.endStr}</td>
								<td align="center">${rules.regex}</td>
								<td align="center"><c:if test="${rules.repeat}">是</c:if> <c:if
										test="${rules.repeat==false}">否</c:if></td>
								<td align="center"><c:if test="${rules.current}">是</c:if> <c:if
										test="${rules.current==false}">否</c:if></td>
								<td align="center"><input type="button" value="编辑" onclick="editRules(this)">
									<input type="button" value="删除" onclick="deleteRules(this)"></td>
							</tr>
						</c:forEach>

						<tr>
							<td align="center">ID(自动增长)</td>
							<td><input type="text" name="url" id="url" value="请输入URL"
								onblur="reCheckElement('url')" onclick="checkElement('url')" /></td>
							<td><input type="text" name="startIndex" id="startIndex"
								value="请输入startIndex" onblur="reCheckElement('startIndex')"
								onclick="checkElement('startIndex')" /></td>
							<td><input type="text" name="endIndex" id="endIndex"
								value="请输入endIndex" onblur="reCheckElement('endIndex')"
								onclick="checkElement('endIndex')" /></td>
							<td><input type="text" name="startStr" id="startStr"
								value="请输入startStr" onblur="reCheckElement('startStr')"
								onclick="checkElement('startStr')" /></td>
							<td><input type="text" name="endStr" id="endStr"
								value="请输入endStr" onblur="reCheckElement('endStr')"
								onclick="checkElement('endStr')" /></td>
							<td><input type="text" name="regex" id="regex"
								value="请输入regex" onblur="reCheckElement('regex')"
								onclick="checkElement('regex')" /></td>
							<td align="center"><select name="repeat" id="repeat">
									<option value="true">是</option>
									<option value="false">否</option>
							</select></td>
							<td align="center"><select name="current" id="current">
									<option value="false">否</option>
									<option value="true">是</option>
							</select></td>
							<td align="center"><input id="bt-submit" type="submit"
								name="add" value="添加新规则"/></td>
						</tr>
						<tr>
							<td colspan="10" align="center"><a class="href_none"
								href="${pageContext.request.contextPath}/">回到主页</a> <a
								class="href_none"
								href="${pageContext.request.contextPath}/admin">后台管理</a></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>