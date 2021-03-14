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
	<table id="commentDg" class="easyui-datagrid"></table>

	<!-- 工具栏开始 -->
	<div id="commentTb" style="padding:2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="auditComment();">评论审核</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="deleteComment();">删除评论</a>
	</div>
	<!-- 工具栏结束 -->

	<!-- 搜索栏开始 -->
	<div id="searchCommentTb" style="padding:4px 3px; padding-top: 15px">
		<form id="searchCommentForm">
			<div style="padding:3px ">
				评论人&nbsp;&nbsp;<input style="width:115px;" id="comment_search_uid"
					class="easyui-combobox" value="0" name="comment_search_uid"
					data-options="valueField:'id',textField:'loginName',url:'user/getUsers'">&nbsp;&nbsp;&nbsp;&nbsp;
				评论状态&nbsp;&nbsp;<select style="width:115px;"
					id="comment_search_status" class="easyui-combobox"
					name="comment_search_status">
					<option value="0">请选择...</option>
					<option value="1">未审核</option>
					<option value="2">已审核</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp; 评论时间 &nbsp;&nbsp;<input
					class="easyui-datebox" name="commentTimeFrom" id="commentTimeFrom"
					style="width:115px;" /> ~ <input class="easyui-datebox"
					name="commentTimeTo" id="commentTimeTo" style="width:115px;" />&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" plain="true" onclick="searchComment();">查找</a>
			</div>
		</form>
	</div>
	<!-- 搜索栏结束 -->

	<script type="text/javascript">
		$(function() {
			$('#commentDg').datagrid({
				fit : true,
				fitColumn : true,
				rownumbers : true,
				singleSelect : false,
				url : 'comment/list',
				pagination : true,
				pageSize : 2,
				pageList : [ 2, 4, 6 ],
				toolbar : '#searchCommentTb',
				header : '#commentTb',
				columns : [ [ {
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'content',
					title : '评论内容',
					width : 280
				}, {
					field : 'createDate',
					title : '评论日期',
					width : 150
				}, {
					field : 'status',
					title : '评论状态',
					width : 100,
					formatter : function(value, row, index) {
						if (row.status == 2) {
							return "已审核";
						} else {
							return "未审核";
						}
					}
				} ] ]
			});
		});

		function searchComment() {
			var comment_search_uid = $('#comment_search_uid').combobox(
					"getValue");
			var comment_search_status = $('#comment_search_status').combobox(
					"getValue");
			var commentTimeFrom = $("#commentTimeFrom").datebox("getValue");
			var commentTimeTo = $("#commentTimeTo").datebox("getValue");
			$('#commentDg').datagrid('load', {
				userId : comment_search_uid,
				status : comment_search_status,
				commentTimeFrom : commentTimeFrom,
				commentTimeTo : commentTimeTo
			});
		}

	    // 评论审核
		function auditComment() {
			var rows = $("#commentDg").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认审核么?', function(r) {
					if (r) {
						var cids = "";
						for (var i = 0; i < rows.length; i++) {
							cids += rows[i].id + ",";
						}						
						$.post('comment/commentAudit', {
							ids : cids
						}, function(result) {
							if (result.success == 'true') {
								$("#commentDg").datagrid('reload');
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
				$.messager.alert('提示', '请选择要审核的评论', 'info');
			}
		}
		
		// 删除评论
		function deleteComment() {
			var rows = $("#commentDg").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认删除么?', function(r) {
					if (r) {
						var cids = "";
						for (var i = 0; i < rows.length; i++) {
							cids += rows[i].id + ",";
						}						
						$.post('comment/deleteComment', {
							ids : cids
						}, function(result) {
							if (result.success == 'true') {
								$("#commentDg").datagrid('reload');
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
				$.messager.alert('提示', '请选择要删除的评论', 'info');
			}
		}
		
	</script>
</body>
</html>
