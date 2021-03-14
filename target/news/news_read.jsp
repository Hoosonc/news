<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="index-elements/index_top.jsp" />
<link href="CSS/read.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function checkComment() {
		var cauthor = document.getElementById("cauthor");
		var content = document.getElementById("ccontent");
		if (cauthor.value == "") {
			alert("用户名不能为空！！");
			return false;
		} else if (content.value == "") {
			alert("评论内容不能为空！！");
			return false;
		}
		return true;
	}
</script>
<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp"></jsp:include>
	<div class="main">
		<div class="class_type">
			<img src="Images/class_type.gif" alt="新闻中心" />
		</div>
		<div class="content">
			<ul class="class_date">
				<li id='class_month'>
					<!-- 循环显示主题列表 --> <c:forEach var="topic"
						items="${requestScope.topics }">
						<a href="list?topicId=${topic.id }"><b>${topic.name }</b></a>
					</c:forEach>
			</ul>
			<ul class="classlist">
				<table width="98%">
					<tr width="100%">
						<td colspan="2" align="center">${newsinfo.title }</td>
					</tr>
					<tr>
						<td colspan="2"><hr /></td>
					</tr>
					<tr>
						<td align="center">作者：${newsinfo.author }&nbsp;&nbsp; 类型：<a
							href="list?topicId=${topic.id }">${newsinfo.topic.name }</a>
							发布时间：<fmt:formatDate value="${newsinfo.createDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<td align="left"><strong>摘要：${newsinfo.summary }</strong></td>
					</tr>
					<tr>
						<td colspan="2" align="center"></td>
					</tr>
					<tr>
						<td colspan="2">${newsinfo.content }</td>
					</tr>
					<tr>
						<td colspan="2"><hr /></td>
					</tr>
				</table>
			</ul>
		</div>

		<!-- 新闻评论开始 -->

		<div class="content" style="padding-top:50px;">
			<ul class="class_date">新闻评论
			</ul>
			<c:forEach var="comment" items="${requestScope.comments }">
				<ul class="classlist">
					<table width="98%">
						<tr>
							<td align="center">评论人：${comment.user.loginName }&nbsp;&nbsp;
								评论时间：<fmt:formatDate value="${comment.createDate}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<td colspan="2">${comment.content }</td>
						</tr>
						<tr>
							<td colspan="2"><hr /></td>
						</tr>
					</table>
				</ul>
			</c:forEach>
			<!-- 分页超链接部分 -->
			<c:if test="${requestScope.pager.curPage>1}">
				<p align="center">
					<a
						href='newsread?pageIndex=1&id=${requestScope.comment.newinfo.id}'>首页</a>
					<a
						href='newsread?pageIndex=${requestScope.pager.curPage-1 }&id=${requestScope.comment.newinfo.id}'>上一页</a>
				</p>
			</c:if>
			<c:if test="${requestScope.pager.curPage < requestScope.pager.pageCount}">
				<p align="center">
					<a
						href='newsread?pageIndex=${requestScope.pager.curPage+1}&id=${requestScope.comment.newinfo.id}'>下一页</a>
					<a
						href='newsread?pageIndex=${requestScope.pager.pageCount }&id=${requestScope.comment.newinfo.id}'>尾页</a>
				</p>
			</c:if>
		</div>
		<!-- 新闻评论结束 -->
		<br> <br> <br> <br>
		<!-- 提交评论 -->
		<div class="content" style="padding-top:50px;">
			<ul class="class_date">我要评论
			</ul>
			<ul class="classlist">
				<form action="/news/comment/addComment" method="post">
					<table width="100%">
						<tr>
							<td align="center"><input type="hidden" id="newinfo.id"
								name="newinfo.id" value="${newsinfo.id }" /></td>
						</tr>
						<tr>
							<td align="center"><textarea rows="10" cols="90"
									name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td align="center" style="padding-top:10px;padding-bottom:20px;">
								<input type="submit" class="login_sub" value="提交" />
							</td>
						</tr>
					</table>
				</form>
			</ul>
		</div>
	</div>
</div>
