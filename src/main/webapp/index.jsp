<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="index-elements/index_top.jsp" />
<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp" />
	<div class="main">
		<div class="class_type">
			<img src="${pageContext.request.contextPath}/Images/class_type.gif"
				alt="��������" />
		</div>
		<div class="content">
			<ul class="class_date">
				<li id='class_month'>
					<!-- ѭ����ʾ�����б� --> 
					<c:forEach var="topic" items="${requestScope.topics }">
						<a href="list?topicId=${topic.id }"><b>${topic.name }</b></a> 
					</c:forEach>
			</ul>
			<ul class="classlist">
				<!-- ѭ����ʾ��ǰҳ�����б� -->
				<c:forEach var="newsinfo" items="${requestScope.newsinfos }">
					<li><a href="newsread?id=${newsinfo.id}">
							${newsinfo.title} </a> <span><fmt:formatDate
								value="${newsinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span></li>
				</c:forEach>
				<br>
				<br>

				<!-- ��ҳ�����Ӳ��� -->
				<c:if test="${requestScope.pager.curPage>1}">
					<p align="center">
						<a
							href='list?pageIndex=1&topicId=${requestScope.newsinfo.topic.id}'>��ҳ</a>
						<a
							href='list?pageIndex=${requestScope.pager.curPage-1 }&topicId=${requestScope.newsinfo.topic.id}'>��һҳ</a>
					</p>
				</c:if>

				<c:if
					test="${requestScope.pager.curPage < requestScope.pager.pageCount}">
					<p align="center">
						<a
							href='list?pageIndex=${requestScope.pager.curPage+1}&topicId=${requestScope.newsinfo.topic.id}'>��һҳ</a>
						<a
							href='list?pageIndex=${requestScope.pager.pageCount }&topicId=${requestScope.newsinfo.topic.id}'>βҳ</a>
					</p>
				</c:if>
			</ul>
		</div>
	</div>
</div>

