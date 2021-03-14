<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="sidebar">
	<h1>
		<img src="${pageContext.request.contextPath}/Images/title_1.gif"
			alt="国内新闻" />
	</h1>
	<div class="side_list">
		<ul>
			<!-- 循环显示5条国内新闻  -->
			<c:forEach var="domesticNews"
				items="${requestScope.domesticNewsList }">
				<li><a href='newsread?id=${domesticNews.id }'><b>
							${domesticNews.title }</b></a></li>
			</c:forEach>
		</ul>
	</div>
	<h1>
		<img src="${pageContext.request.contextPath}/Images/title_2.gif"
			alt="国际新闻" />
	</h1>
	<div class="side_list">
		<ul>
			<c:forEach var="internationalNews"
				items="${requestScope.internationalNewsList }">
				<li><a href='newsread?id=${internationalNews.id }'><b>
							${internationalNews.title }</b></a></li>
			</c:forEach>
		</ul>
	</div>
</div>

