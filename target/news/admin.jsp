<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'admin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css" />
<script src="EasyUI/jquery.min.js" type="text/javascript"></script>
<script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
<script src="EasyUI/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:60px;background:#B3DFDA;padding:10px">
		<div align="left">
			<img width="30px" height="30px" alt="后台管理"
				src="Images/admin_logo.jpg" />
		</div>
		<div align="right">欢迎您，${sessionScope.admininfo.loginName}</div>
	</div>
	<div data-options="region:'west',split:true,title:'功能菜单'"
		style="width:180px">
		<ul id="tt"></ul>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;background:#A9FACD;padding:10px;text-align:center">powered
		by miaoyong</div>
	<div data-options="region:'center'">
		<div id="tabs" data-options="fit:true" class="easyui-tabs"
			style="width:500px;height:250px;"></div>
	</div>
	<script type="text/javascript">
		// 为tree指定数据
		$('#tt').tree({
			url : 'admin/getTree?adminid=${sessionScope.admininfo.id}'
		});
		$('#tt').tree({
			onClick : function(node) {
				if ("新闻管理" == node.text) {
					if ($('#tabs').tabs('exists', '新闻管理')) {
						$('#tabs').tabs('select', '新闻管理');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'newslist.jsp',
							closable : true
						});
					}
				} else if ("主题管理" == node.text) {
					if ($('#tabs').tabs('exists', '主题管理')) {
						$('#tabs').tabs('select', '主题管理');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'topiclist.jsp',
							closable : true
						});
					}
				} else if ("评论管理" == node.text) {
					if ($('#tabs').tabs('exists', '评论管理')) {
						$('#tabs').tabs('select', '评论管理');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'commentlist.jsp',
							closable : true
						});
					}
				} else if ("用户管理" == node.text) {
					if ($('#tabs').tabs('exists', '用户管理')) {
						$('#tabs').tabs('select', '用户管理');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'userlist.jsp',
							closable : true
						});
					}
				} else if ("退出系统" == node.text) {
					$.ajax({
						url : 'admin/logout',
						success : function(data) {
							window.location.href = "admin_login.jsp";
						}
					})
				}
			}
		});
	</script>
</body>
</html>
