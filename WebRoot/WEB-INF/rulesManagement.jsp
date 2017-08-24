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
</head>
<body>
	<h1>规则列表</h1>
	<table class="table_center">
		<tr>
			<td align="center">

				<form id="rules-form" action="${pageContext.request.contextPath}/operateRules?operate=add" method="post" onsubmit="return addRules()">

					<table class="table_inner">
						<tr>
							<td class="inner_td" style="width: 120px" >规则ID</td>
							<td class="inner_td" style="width: 120px" >规则名称</td>
							<td class="inner_td" style="width: 200px" >采集URL</td>
							<td class="inner_td" style="width: 120px" >开始索引</td>
							<td class="inner_td" style="width: 120px" >结束索引</td>
							<td class="inner_td" style="width: 180px" >开始区域</td>
							<td class="inner_td" style="width: 180px" >结束区域</td>
							<td class="inner_td" style="width: 180px">正则表达式</td>
							<td class="inner_td" style="width: 80px">重复匹配</td>
							<td class="inner_td" style="width: 120px">采集当前页面</td>
							<td class="inner_td" style="width: 150px">操作</td>
						</tr>

						<c:forEach items="${rulesList}" var="rules" varStatus="vs">
							<tr>
								<td class="inner_td">${rules.id}</td>
								<td class="inner_td">${rules.rulesName}</td>
								<td class="inner_td">${rules.url}</td>
								<td class="inner_td">${rules.startIndex}</td>
								<td class="inner_td">${rules.endIndex}</td>
								<td class="inner_td">${rules.startStr}</td>
								<td class="inner_td">${rules.endStr}</td>
								<td class="inner_td">${rules.regex}</td>
								<td class="inner_td"><c:if test="${rules.repeat}">是</c:if> <c:if
										test="${rules.repeat==false}">否</c:if></td>
								<td class="inner_td"><c:if test="${rules.current}">是</c:if> <c:if
										test="${rules.current==false}">否</c:if></td>
								<td class="inner_td"><input type="button" value="编辑" onclick="editRules(this)">
									<input type="button" value="删除" onclick="deleteRules(this)"></td>
							</tr>
						</c:forEach>

						<tr>
							<td class="inner_td">ID(自动增长)</td>
							<td class="inner_td"><input type="text" style="width: 200px" name="rulesName" id="rulesName" value="请输入规则名称"
								onblur="reCheckElement('rulesName')" onclick="checkElement('rulesName')" /></td>
							<td class="inner_td"><input type="text" style="width: 200px" name="url" id="url" value="请输入URL"
								onblur="reCheckElement('url')" onclick="checkElement('url')" /></td>
							<td class="inner_td"><input type="text" style="width: 120px" name="startIndex" id="startIndex"
								value="请输入startIndex" onblur="reCheckElement('startIndex')"
								onclick="checkElement('startIndex')" /></td>
							<td class="inner_td"><input type="text" style="width: 120px" name="endIndex" id="endIndex"
								value="请输入endIndex" onblur="reCheckElement('endIndex')"
								onclick="checkElement('endIndex')" /></td>
							<td class="inner_td"><input type="text" name="startStr" id="startStr"
								value="请输入startStr" style="width: 180px" onblur="reCheckElement('startStr')"
								onclick="checkElement('startStr')" /></td>
							<td class="inner_td"><input type="text" style="width: 180px" name="endStr" id="endStr"
								value="请输入endStr" onblur="reCheckElement('endStr')"
								onclick="checkElement('endStr')" /></td>
							<td class="inner_td"><input type="text" style="width: 180px" name="regex" id="regex"
								value="请输入regex" onblur="reCheckElement('regex')"
								onclick="checkElement('regex')" /></td>
							<td class="inner_td"><select name="repeat" id="repeat">
									<option value="true">是</option>
									<option value="false">否</option>
							</select></td>
							<td class="inner_td"><select name="current" id="current">
									<option value="false">否</option>
									<option value="true">是</option>
							</select></td>
							<td class="inner_td"><input id="bt-submit" type="submit"
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