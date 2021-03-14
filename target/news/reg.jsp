<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<title>新闻中国</title>

<link href="${pageContext.request.contextPath}/CSS/main.css"
	rel="stylesheet" type="text/css" />
<script language="javascript">
	function check() {
		var loginName = document.getElementById("loginName");
		var loginPwd = document.getElementById("loginPwd");
		if (loginName.value == "") {
			alert("用户名不能为空！请重新填入！");
			return false;
		} else if (loginPwd.value == "") {
			alert("密码不能为空！请重新填入！");
			return false;
		}
		return true;
	}

	/* function focusOnLogin(){
		var loginPwd = document.getElementById("loginPwd");
		if( loginPwd != null )
			loginPwd.focus();	
	} */

	function openReg() {
		window.location.href = '/news/reg.jsp';
	}
</script>
</head>
<body>
	<div id="header">
		<div id="nav" style="margin-top: 30px">
			<table>
				<tr>
					<td><div id="logo" >
							<img src="${pageContext.request.contextPath}/Images/logo.jpg"
								alt="新闻" />
						</div></td>
					<td><img
						src="${pageContext.request.contextPath}/Images/a_b01.gif" alt="" /></td>
				</tr>
			</table>
		</div>
		<div id="container">
			<div class="content">
				<form action="/news/user/reg" method="post"
					onsubmit="return check()">
					<table style="margin: 10px auto">
						<tr height="20px">
							<td>用户名:</td>
							<td><input type="text" id="loginName" name="loginName" /></td>
						</tr>
						<tr height="25px">
							<td>密码:</td>
							<td><input type="text" id="loginPwd" name="loginPwd" /></td>
						</tr>
						<tr height="25px">
							<td></td>
							<td><input type="submit" class="login_sub" value="注册" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

