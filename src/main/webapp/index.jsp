<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="index-elements/index_top.jsp" />
<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp" />
	<div class="main">
		<div class="class_type">
			<img src="${pageContext.request.contextPath}/Images/class_type.gif"
				alt="新闻中心" />
		</div>
		<div class="content">
			<ul class="class_date">
				<li id='class_month'>
					<!-- 循环显示主题列表 --> 
					<c:forEach var="topic" items="${requestScope.topics }">
						<a href="list?topicId=${topic.id }"><b>${topic.name }</b></a> 
					</c:forEach>
			</ul>
			<ul class="classlist">
				<!-- 循环显示当前页新闻列表 -->
				<c:forEach var="newsinfo" items="${requestScope.newsinfos }">
					<li><a href="newsread?id=${newsinfo.id}">
							${newsinfo.title} </a> <span><fmt:formatDate
								value="${newsinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span></li>
				</c:forEach>
				<br>
				<br>

				<!-- 分页超链接部分 -->
				<c:if test="${requestScope.pager.curPage>1}">
					<p align="center">
						<a
							href='list?pageIndex=1&topicId=${requestScope.newsinfo.topic.id}'>首页</a>
						<a
							href='list?pageIndex=${requestScope.pager.curPage-1 }&topicId=${requestScope.newsinfo.topic.id}'>上一页</a>
					</p>
				</c:if>

				<c:if
					test="${requestScope.pager.curPage < requestScope.pager.pageCount}">
					<p align="center">
						<a
							href='list?pageIndex=${requestScope.pager.curPage+1}&topicId=${requestScope.newsinfo.topic.id}'>下一页</a>
						<a
							href='list?pageIndex=${requestScope.pager.pageCount }&topicId=${requestScope.newsinfo.topic.id}'>尾页</a>
					</p>
				</c:if>
			</ul>
		</div>
	</div>
</div>

