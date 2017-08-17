<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function rulesManagement(){
		location.href = "${pageContext.request.contextPath}/rulesManagement";
	}
</script>


<style type="text/css">
body {
	text-align: center;
}

.middle {
	align: center;
}

.table {
	border: 1px dotted #f3c3f3;
	width: 500px;
	font-size: 18px;
	padding: 5px;
}

.table2 {
	width: 100%;
	height: 80%;
	border: 0;
	cellspacing: 0;
	cellpadding: 0;
	align: center;
	text-align: center;
}

td {
	border: 1px solid #c3f3c3;
	padding: 10px;
}

.div1 {
	text-align: center;
	margin-top: 10px;
	margin-top: 10px;
}

h1 {
	text-align: center;
}
</style>

</head>
<body>
	<h1>网站后台</h1>
	<div class="middle">

		<table class="table2">
			<tr>
				<td align="center">
					<form action="xxx"
						method="post">
						<table class="table">
							<tr>
								<td>采集规则管理</td>
								<td><input type="button" value="进入" onclick="rulesManagement()" /></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>


		<div class="div1">
			<a href="${pageContext.request.contextPath}/">回到主页</a>
		</div>

	</div>
</body>
</html>
