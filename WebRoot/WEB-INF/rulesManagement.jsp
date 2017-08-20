<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规则管理</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
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
				<table class="table_inner" style="table-layout:fixed;">
					<tr>
						<td style="width:100px">选项ID</td>
						<td style="width:200px">采集URL</td>
						<td style="width:100px">开始索引</td>
						<td style="width:100px">结束索引</td>
						<td style="width:100px">开始区域</td>
						<td style="width:100px">结束区域</td>
						<td style="width:200px">正则表达式</td>
						<td style="width:100px">是否重复匹配</td>
						<td style="width:100px">是否采集当前页面</td>
						<td style="width:100px">操作</td>
					</tr>
					<c:forEach items="${rulesList}" var="rules" varStatus="vs">
						<tr>
							<td>${rules.id}</td>
							<td>${rules.url}</td>
							<td>${rules.startIndex}</td>
							<td>${rules.endIndex}</td>
							<td>${rules.startStr}</td>
							<td>${rules.endStr}</td>
							<td>${rules.regex}</td>
							<td>${rules.repeat}</td>
							<td>${rules.current}</td>
							<td><input type="button" value="编辑"></td>
						</tr>
					</c:forEach>
					<tr>
						<td>ID</td>
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
						<td><select name="repeat">
								<option value="1">是</option>
								<option value="0">否</option>
						</select></td>
						<td><select name="repeat">
								<option value="0">否</option>
								<option value="1">是</option>
						</select></td>
						<td><input type="button" name="add" value="添加新规则" /></td>
					</tr>
					<tr>
						<td colspan="10" align="center"><a class="href_none"
							href="${pageContext.request.contextPath}/">回到主页</a> <a
							class="href_none" href="${pageContext.request.contextPath}/admin">后台管理</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>