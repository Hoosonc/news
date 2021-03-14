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
	<table id="newsinfoDg" class="easyui-datagrid"></table>

	<!-- 工具栏开始 -->
	<div id="newsinfoTb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="openAddNewsinfoDlg();">添加新闻</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="openEditNewsinfoDlg();">编辑新闻</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="removeNews();">删除新闻</a>
	</div>
	<!-- 工具栏结束 -->

	<!-- 搜索栏开始 -->
	<div id="searchNewsinfoTb" style="padding:4px 3px; padding-top: 15px">
		<form id="searchNewsinfoForm">
			<div style="padding:3px ">
				新闻标题&nbsp;&nbsp;<input class="easyui-textbox"
					name="newsinfo_search_title" id="newsinfo_search_title"
					style="width:110px" /> 新闻主题&nbsp;&nbsp; <input
					style="width:115px;" id="newsinfo_search_tid"
					class="easyui-combobox" value="0" name="newsinfo_search_tid"
					data-options="valueField:'id',textField:'name',url:'topic/getTopic/1'">&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="searchNewsinfo();">查找</a>
			</div>
		</form>
	</div>
	<!-- 搜索栏结束 -->

	<!-- 新增/修改新闻对话框开始 -->
	<div id="addNewsinfoDlg" class="easyui-dialog" title="New Topic"
		closed="true" style="width:400px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="addNewsinfoForm" method="POST" action="">
				<table cellpadding="5">
					<tr>
						<td>主题:</td>
						<td><input style="width:115px;" id="topic.id"
							class="easyui-combobox" name="topic.id" value="1" 
							data-options="valueField:'id',textField:'name',url:'topic/getTopic/0'">
						</td>
					</tr>
					<tr>
						<td>标题:</td>
						<td><input class="easyui-textbox" type="text" id="title"
							name="title" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>作者:</td>
						<td><input class="easyui-textbox" type="text" id="author"
							name="author" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>摘要:</td>
						<td><input class="easyui-textbox" name="summary" id="summary"
							data-options="multiline:true" style="height:60px"></input></td>
					</tr>
					<tr>
						<td>内容:</td>
						<td><input class="easyui-textbox" name="content" id="content"
							data-options="multiline:true" style="height:60px"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveNewsinfo()">保存</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearAddNewsinfoForm()">清空</a>
			</div>
		</div>
	</div>
	<!-- 新增/修改新闻对话框结束-->

	<script type="text/javascript">
		$(function() {
			$('#newsinfoDg').datagrid({
				fit : true,
				fitColumn : true,
				rownumbers : true,
				singleSelect : false,
				url : 'newsinfo/newslist',
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 15, 20 ],
				toolbar : '#searchNewsinfoTb',
				header : '#newsinfoTb',
				columns : [ [ {
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'title',
					title : '标题',
					width : 280
				}, {
					field : 'author',
					title : '作者',
					width : 60
				}, {
					field : 'summary',
					title : '摘要',
					width : 250
				}, {
					field : 'content',
					title : '内容',
					width : 300
				}, {
					field : 'createDate',
					title : '发布日期',
					width : 150
				}, {
					field : 'topic',
					title : '新闻主题',
					formatter : function(value, row, index) {
						if (row.topic) {
							return row.topic.name;
						} else {
							return value;
						}
					},
					width : 80
				} ] ]
			});
		});

		function searchNewsinfo() {
			var newsinfo_search_title = $('#newsinfo_search_title').textbox(
					"getValue");
			var newsinfo_search_tid = $('#newsinfo_search_tid').combobox(
					"getValue");
			$('#newsinfoDg').datagrid('load', {
				title : newsinfo_search_title,
				topicId : newsinfo_search_tid
			});
		}

		var urls;
		var data;
		function openAddNewsinfoDlg() {
			$('#addNewsinfoDlg').dialog('open').dialog('setTitle', '添加新闻');
			$('#addNewsinfoDlg').form('clear');
			urls = 'newsinfo/addNewsinfo';
		}

		function openEditNewsinfoDlg() {
			var row = $("#newsinfoDg").datagrid("getSelected");
			if (row) {
				$("#addNewsinfoDlg").dialog("open").dialog('setTitle', '编辑新闻');
				$("#addNewsinfoForm").form("load", {
					"id" : row.id,
					"topic.id" : row.topic.id,
					"title" : row.title,
					"author" : row.author,
					"summary" : row.summary,
					"content" : row.content
				});
				urls = "newsinfo/modifyNewsinfo?id=" + row.id;
			}
		}

		function saveNewsinfo() {
			$("#addNewsinfoForm").form("submit", {
				url : urls, //使用参数				
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						$("#newsinfoDg").datagrid("reload");
					}
					$("#addNewsinfoDlg").dialog("close");
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});
		}
		function clearAddNewsinfoForm() {
			$('#addNewsinfoForm').form('clear');
		}

		//删除新闻
		function removeNews() {
			var rows = $("#newsinfoDg").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认要删除么?', function(r) {
					if (r) {
						var ids = "";
						for (var i = 0; i < rows.length; i++) {
							ids += rows[i].id + ",";
						}
						$.post('newsinfo/deleteNewsinfo', {
							id : ids
						}, function(result) {
							if (result.success) {
								$("#newsinfoDg").datagrid('reload'); // reload the user data
								$.messager.show({ // show error message  
									title : '提示信息',
									msg : result.message
								});
							} else {
								$.messager.show({ // show error message  
									title : '提示信息',
									msg : result.message
								});
							}
						}, 'json');
					}
				});
			} else {
				$.messager.alert('提示', '请选择要删除的行', 'info');
			}
		}
	</script>
</body>
</html>
