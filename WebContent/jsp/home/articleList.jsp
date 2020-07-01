<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sbs.java.blog.dto.Article"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ include file="/jsp/part/head.jspf"%>
<style>
h2 {
color: white;
}

.table-box {
	color: #444958;
}
.table-box>table {
	width: 100%;
	border-collapse: collapse;
	color: white;
	font-size : .9rem;
}

.table-box>table th, .table-box>table td {
	border: 2px double #444958;
	padding : 10px;
}
.article-list-box-1 td{
	text-align: center;
}

.page {
	text-align:center;
	padding : 20px;
}
.page > ui{

}
.page > ul > li{
	display:inline-block;	
}
.page > ul > li > a{

}
.page > ul > li:hover > a{

}

</style>
<%
 List<Article> articles = (List<Article>)request.getAttribute("articles");
	
%>
<h2 class="con" style ="text-align: center"> LIST </h2>

<div class="article-list-box-1 con table-box">
	<table>
	<colgroup>
		<col width="50">
		<col width="150">
		<col width="150">
	</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>갱신날짜</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
		<%for
		(Article article : articles){ 
		%>
			<tr>
				<td><%=article.getId()%></td>
				<td><%=article.getRegDate()%></td>
				<td><%=article.getUpdateDate()%></td>
				<td class =" text-align-left "><a href="./detail?id=<%=article.getId()%>"><%=article.getTitle()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<div class = "page">
	<%int totalCount = (int)request.getAttribute("totalCount");%>
	<%int itemsInAPage = 5; %>
	<%int limitFrom = ((int)page -1)*itemsInAPage; %>
	<%int totalPage = (int)Math.ceil(totalCount / itemsInAPage); %>
	<%for
		(int i = 1; i <= totalPage; i++){
	%>
		<ul>
			<li><a herf="">이전</a>
			<li><a href="${pageContext.request.contextPath}/s/article?cateItemId=${param.cateItemId}&page=<%=i%>"></a></li>
			<li><a herf="">다음</a>
		</ul>
		<%} %>
	</div>
</div>


<%@ include file="/jsp/part/foot.jspf"%>

