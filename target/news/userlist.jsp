<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("admininfo") == null)
		response.sendRedirect("/news/admin_login.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'newslist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<!-- 创建一个table -->
	<table id="userListDg" class="easyui-datagrid"></table>

	<!-- 创建工具栏 -->
	<div id="userListTb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="setIsEnableUser(2);">启用用户</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" onclick="setIsEnableUser(1);" plain="true">禁用用户</a>
	</div>

	<!-- 创建搜索栏 -->
	<div id="searchUserListTb" style="padding:4px 3px; padding-top: 15px">
		<form id="searchUserListForm">
			<div style="padding:3px ">
				客户名称&nbsp;&nbsp;<input class="easyui-textbox"
					name="users_search_loginName" id="users_search_loginName"
					style="width:110px" /> &nbsp;&nbsp;&nbsp;&nbsp;客户状态&nbsp;&nbsp;<select
					style="width:115px;" id="users_search_status"
					class="easyui-combobox" name="users_search_status">
					<option value="0">请选择...</option>
					<option value="1">禁用</option>
					<option value="2">启用</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="searchUsers();">查找</a>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#userListDg').datagrid({
				singleSelect : false,
				url : 'user/list',
				queryParams : {}, //查询条件
				pagination : true, //启用分页
				pageSize : 5, //设置初始每页记录数（页大小）
				pageList : [ 5, 10, 15 ], //设置可供选择的页大小
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#userListTb', //为datagrid添加工具栏
				header : '#searchUserListTb', //为datagrid标头添加搜索栏
				columns : [ [ { //编辑datagrid的列
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'loginName',
					title : '登录名',
					width : 100
				}, {
					field : 'loginPwd',
					title : '密码',
					width : 80
				}, {
					field : 'status',
					title : '用户状态',
					width : 100,
					formatter : function(value, row, index) {
						if (row.status == 2) {
							return "启用";
						} else {
							return "禁用";
						}
					}
				} ] ]
			});
		});

		var urls;
		var data;

		function searchUsers() {
			var users_search_loginName = $('#users_search_loginName').textbox("getValue");
			var users_search_status = $('#users_search_status').combobox(
					"getValue");
			$('#userListDg').datagrid('load', {
				loginName : users_search_loginName,
				status : users_search_status
			});
		}

		// 设置启用或禁用用户
		function setIsEnableUser(flag) {
			var rows = $("#userListDg").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认要设置么?', function(r) {
					if (r) {
						var uids = "";
						for (var i = 0; i < rows.length; i++) {
							uids += rows[i].id + ",";
						}
						$.post('user/setIsEnableUser', {
							uids : uids,
							flag : flag
						}, function(result) {
							if (result.success == 'true') {
								$("#userListDg").datagrid('reload');
								$.messager.show({
									title : '提示信息',
									msg : result.message
								});
							} else {
								$.messager.show({
									title : '提示信息',
									msg : result.message
								});
							}
						}, 'json');

					}
				});
			} else {
				$.messager.alert('提示', '请选择要启用或禁用的客户', 'info');
			}
		}
	</script>
</body>
</html>
