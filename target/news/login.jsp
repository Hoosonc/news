<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>登录页</title>
</head>

<body>
	<form action="admin/login" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="loginName" /></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><input type="text" name="loginPwd" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="登录" /></td>
				<td></td>
			</tr>
		</table>
	</form>

</body>
</html>
