<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<title>新闻中国</title>
	
	<link href="${pageContext.request.contextPath}/CSS/main.css" rel="stylesheet" type="text/css" />
	<script language="javascript">
		function check(){
			var loginName = document.getElementById("loginName");
			var loginPwd = document.getElementById("loginPwd");
			if(loginName.value == ""){
				alert("用户名不能为空！请重新填入！");
				return false;
			}else if(loginPwd.value == ""){
				alert("密码不能为空！请重新填入！");
				return false;
			}
			return true;
		}
		
		 function focusOnLogin(){
			var loginPwd = document.getElementById("loginPwd");
			if( loginPwd != null )
				loginPwd.focus();	
		}
		
		function openReg(){
			window.location.href='/news/reg.jsp';
		}
	</script>
</head>
<body onload="focusOnLogin()">
<div id="header">
	<div id="top_login">	
	<c:if test="${sessionScope.u==null }">
		<form action="/news/user/login" method="post" onsubmit="return check()">
		    <label>用户名</label>
			<input type="text" id="loginName"  name="loginName" value="" class="login_input" />
			<label> 密&#160;&#160;码 </label>
			<input type="password" id="loginPwd" name="loginPwd" value="" class="login_input" />
			<input type="submit" class="login_sub" value="登录" />
			<input type="button" onclick="openReg()" class="login_sub" value="注册" />
			<label id="error"> </label>			
		</form>		
	</c:if>
	<c:if test="${sessionScope.u!=null }">
		欢迎您 ：${sessionScope.u.loginName} &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin">登录控制台</a> &nbsp; <a href="/news/user/loginout">退出</a>
	</c:if>
  </div>
  <div id="nav">
	  <table>
	    <tr>
	    	<td><div id="logo"> <img src="${pageContext.request.contextPath}/Images/logo.jpg" alt="新闻" /> </div></td>
	    	<td><img src="${pageContext.request.contextPath}/Images/a_b01.gif" alt="" /></td>
	    </tr>
	  </table>
    <!--mainnav end-->
  </div>
</div>
</body>
</html>

