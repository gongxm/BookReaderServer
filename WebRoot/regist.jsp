<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.table2 {
	border: 1px solid #f3f3f3;
	width: 100%;
	height: 80%;
	border: 0;
	cellspacing: 0;
	cellpadding: 0;
}

.table {
	border: 1px solid #f3c3c3;
	width: 500px;
}

td {
	border: 1px solid #c3f3c3;
	width: 30%;
}
</style>


<script type="text/javascript">
	function checkUserName() {

		var username = document.getElementById("username").value;

		if (username.length<3||username.length>16) {
			document.getElementById("user_name_tips").innerHTML = "<font color='red'>用户名必须是3-16个字符!<font>";
			return;
		}
		var regex = /^\w{3,16}$/

		if (regex.test(username)) {

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {

				//5.判断当前请求的状态
				if (xhr.readyState == 4) {
					//6.获取请求结果：
					eval("var data=" + xhr.responseText);

					//成功
					if (data.errcode == 1) {
						document.getElementById("user_name_tips").innerHTML = "<font color='green'>"
								+ data.errmsg + "<font>";
					} else {
						document.getElementById("user_name_tips").innerHTML = "<font color='red'>"
								+ data.errmsg + "<font>";
					}
				}

			};
			xhr.open('get',
					"${pageContext.request.contextPath}/validateUserName?"
							+ username)
			xhr.send(null);
			return true;
		} else {
			document.getElementById("user_name_tips").innerHTML = "<font color='red'>用户名只能是数字大小写字母以及下划线!<font>";
		}
		return false;
	}

	function checkPassword() {

		var password = document.getElementById("password").value;
		var regex = /^\w{6,16}$/

		if (password.length<6||password.length>16) {
			document.getElementById("password_tips").innerHTML = "<font color='red'>密码长度必须是6-16位<font>";
		} else {
			if (regex.test(password)) {
				document.getElementById("password_tips").innerHTML = "<font color='green'>恭喜您,密码可以使用<font>";
				return true;
			} else {
				document.getElementById("password_tips").innerHTML = "<font color='red'>密码只能是数字大小写字母以及下划线!<font>";
			}
		}
		return false;
	}
	function checkRePassword() {
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		if (repassword == password) {
			document.getElementById("repassword_tips").innerHTML = "<font color='green'>两次输入的密码一致!<font>";
			return true;
		} else {
			document.getElementById("repassword_tips").innerHTML = "<font color='red'>两次输入的密码不一致!<font>";
			return false;
		}
	}

	function check() {
		if (checkUserName() && checkPassword() && checkRePassword()) {
			return true;
		}
		return false;
	}
</script>

</head>
<body>

	<h1 align="center">用户注册</h1>

	<table class="table2">
		<tr>
			<td align="center">
				<form name="regist"
					action="${pageContext.request.contextPath}/registServlet"
					onsubmit="return check()" method="post">
					<table class="table">
						<tr>
							<td>用户名:</td>
							<td><input id="username" type="text" name="username"
								value="${sessionScope.user.username}" onBlur="checkUserName()" />
							</td>
							<td><div id="user_name_tips"></div></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input id="password" type="password" name="password"
								value="${sessionScope.user.password}" onBlur="checkPassword()" />
							</td>
							<td><div id="password_tips"></div></td>
						</tr>
						<tr>
							<td>确认密码:</td>
							<td><input id="repassword" type="password" name="repassword"
								onBlur="checkRePassword()" /></td>
							<td><div id="repassword_tips"></div></td>
						</tr>
						<tr>
							<td align="center"><input type="reset" value="清空" /></td>
							<td align="center"><input type="submit" value="注册" /></td>
						</tr>
						<tr>
							<td align="center"><a
								href="${pageContext.request.contextPath}">回到主页</a></td>
							<td align="center"><a
								href="${pageContext.request.contextPath}/login.jsp">已有账号，去登陆</a></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
