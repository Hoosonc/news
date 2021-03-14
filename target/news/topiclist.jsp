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

<title>My JSP 'topiclist.jsp' starting page</title>

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
	<table id="topicDg" class="easyui-datagrid"></table>

	<!-- 工具栏开始 -->
	<div id="topicTb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="openAddTopicDlg();">添加主题</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="openEditTopicDlg();">编辑主题</a>
	</div>
	<!-- 工具栏结束 -->

	<!-- 搜索栏开始 -->
	<div id="searchTopicTb" style="padding:4px 3px; padding-top: 15px">
		<form id="searchTopicForm">
			<div style="padding:3px ">
				主题名称&nbsp;&nbsp;<input class="easyui-textbox"
					name="topic_search_name" id="topic_search_name"
					style="width:110px" /> <a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="searchTopic();">查找</a>
			</div>
		</form>
	</div>
	<!-- 搜索栏结束 -->

	<!-- 新增/编辑主题对话框开始 -->
	<div id="addTopicDlg" class="easyui-dialog" title="New Topic"
		closed="true" style="width:400px;">
		<div style="padding:10px 60px 20px 60px">
			<form id="addTopicForm" method="POST" action="">
				<table cellpadding="5">					
					<tr>
						<td>主题名称:</td>
						<td><input class="easyui-textbox" type="text" id="name"
							name="name" data-options="required:true"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveTopic()">保存</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearTopicForm()">清空</a>
			</div>
		</div>
	</div>
	<!-- 新增/编辑主题对话框结束-->

	<script type="text/javascript">
		$(function() {
			$('#topicDg').datagrid({
				fit : true,
				fitColumn : true,
				rownumbers : true,
				singleSelect : false,
				url : 'topic/list',
				pagination : true,
				pageSize : 2,
				pageList : [ 2, 4, 6 ],
				toolbar : '#searchTopicTb',
				header : '#topicTb',
				columns : [ [ {
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'name',
					title : '主题名称',
					width : 280
				} ] ]
			});
		});

		function searchTopic() {
			var topic_search_name = $('#topic_search_name').textbox(
					"getValue");
			$('#topicDg').datagrid('load', {
				name : topic_search_name,
			});
		}

		var urls;
		var data;
		function openAddTopicDlg() {
			$('#addTopicDlg').dialog('open').dialog('setTitle', '添加主题');
			$('#addTopicDlg').form('clear');
			urls = 'topic/addTopic';
		}

		function openEditTopicDlg() {
			var row = $("#topicDg").datagrid("getSelected");
			if (row) {
				$("#addTopicDlg").dialog("open").dialog('setTitle',
						'编辑主题');
				$("#addTopicForm").form("load", {
				    "id" : row.id,
					"name" : row.name
				});
				urls = "topic/modifyTopic?id=" + row.id;
			}
		}

		function saveTopic() {		
			$("#addTopicForm").form("submit", {			
				url : urls, //使用参数				
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						$("#topicDg").datagrid("reload");
					}					
					$("#addTopicDlg").dialog("close");
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});			
		}
		function clearTopicForm() {
			$('#addTopicForm').form('clear');
		}	
	</script>
</body>
</html>
