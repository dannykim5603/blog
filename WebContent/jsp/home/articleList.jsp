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
	border: 2px double rgba(98, 116, 216, .7);
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
</div>


<%@ include file="/jsp/part/foot.jspf"%>

