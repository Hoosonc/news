<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>新闻发布系统——后台登录页</title>
<!-- 引入EasyUI的相关css和js文件 -->
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css" />
<script src="EasyUI/jquery.min.js" type="text/javascript"></script>
<script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
<script src="EasyUI/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>

<body>
	<script type="text/javascript">
		function clearForm() {
			$('#adminLoginForm').form('clear');
		}

        /*
		function convertArray(o) { //主要是推荐这个函数。它将jquery系列化后的值转为name:value的形式。 
			var v = {};
			for ( var i in o) {
				if (typeof (v[o[i].name]) == 'undefined')
					v[o[i].name] = o[i].value;
				else
					v[o[i].name] += "," + o[i].value;
			}
			return v;
		}
		*/

		function checkAdminLogin() {
			$("#adminLoginForm").form("submit", {
				url : 'admin/login',
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						window.location.href = 'admin.jsp';
						$("#adminLoginDlg").dialog("close");
					} else {
						$.messager.show({
							title : "提示信息",
							msg : result.message
						});
					}
				}
			});
		}
	</script>
	<div id="adminLoginDlg" class="easyui-dialog"
		style="left: 450px; top: 200px;width: 250;height: 200"
		data-options="title:'后台登录',buttons:'#bb',modal:true">
		<form id="adminLoginForm" method="post">
			<table style="margin:20px;font-size: 13;">
				<tr>
					<th >用户名</th>
					<td><input class="easyui-textbox" type="text" id="loginName"
						name="loginName" data-options="required:true" value="admin"></input></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input class="easyui-textbox" type="text" id="loginPwd"
						name="loginPwd" data-options="required:true" value="123456"></input></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="bb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="checkAdminLogin()">登录</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm();">重置</a>
	</div>

</body>
</html>