<%@page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<style>
.table-box>table {
	width: 100%;
	border-collapse: collapse;
}

.table-box>table th, .table-box>table td {
	border: 2px double rgba(98, 116, 216, .8);
	padding: 10px;
}

.article-list-box-1 td {
	text-align: center;
}
</style>
<%
	Article article = (Article) request.getAttribute("article");
%>
<h2 class="con">아티클 리스트</h2>

<div class="article-list-box-1 con table-box">
	<table>
		<colgroup>
			<col width="50">
			<col width="200">
			<col width="150">
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<td><%=article.getId()%></td>
				<th>게시날짜</th>
				<td><%=article.getRegDate()%></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>제목</th>
				<td colspan="3" align = "center"><%=article.getId() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3" rowspan="3" align = "center"><%=article.getBody()%></td>
			</tr>
		</tbody>
	</table>
</div>
<%@ include file="/jsp/part/foot.jspf"%>

