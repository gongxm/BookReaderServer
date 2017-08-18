<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="css/main.css">

<script type="text/javascript">
	function rulesManagement() {
		location.href = "${pageContext.request.contextPath}/rulesManagement";
	}
</script>


</head>
<body>
	<h1>网站后台</h1>
	<div class="middle">

		<table class="table_center">
			<tr>
				<td align="center">
					<form action="xxx" method="post">
						<table class="table_inner">
							<tr>
								<td>采集规则管理</td>
								<td><input type="button" value="进入"
									onclick="rulesManagement()" /></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>


		<div class="go_to_index">
			<a href="${pageContext.request.contextPath}/">回到主页</a>
		</div>

	</div>
</body>
</html>
