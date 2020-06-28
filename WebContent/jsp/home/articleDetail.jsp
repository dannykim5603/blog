<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sbs.java.blog.dto.Article"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ include file="/jsp/part/head.jspf"%>
<style>
.table-box>table {
	width: 100%;
	border-collapse: collapse;
}

.table-box>table th, .table-box>table td {
	border: 1px solid rgb(98, 116, 216);
	padding : 10px;
}
.article-list-box-1 td{
	text-align: center;
}
</style>
<%
 List<Article> articles = (List<Article>)request.getAttribute("articles");
%>
<h2 class="con">아티클 리스트</h2>

<div class="article-list-box-1 con table-box">
	<table>
	<colgroup>
		<col width="70">
		<col width="70">
		<col width="100">
	</colgroup>
		<thead>
		<%for
		(Article article : articles){ 
		%>
			<tr>
				<th>ID</th>
				<td><%=article.getId() %></td>
				<th>제목</th>
				<td><%=article.getTitle() %></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>날짜</th>
				<td colspan="3"><%=article.getRegDate()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>
</div>


<%@ include file="/jsp/part/foot.jspf"%>

