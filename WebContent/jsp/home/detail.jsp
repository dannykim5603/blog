<%@page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>


<!-- 하이라이트 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<!-- 하이라이트 라이브러리, 언어 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

<!-- 코드 미러 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

<!-- 토스트 UI 에디터, 자바스크립트 코어 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<!-- 토스트 UI 에디터, 신택스 하이라이트 플러그인 추가 -->
<script
	src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터, CSS 코어 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />


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

#original {
	diplay: none;
}
</style>
<%
	Article article = (Article) request.getAttribute("article");
%>
<h2 class="con">아티클 리스트</h2>


<h1>뷰어 1</h1>
<div id="origin1">
	#
	<%=article.getTitle()%>
	##
	<%=article.getBody()%>
	```php $a = 10; $b = 20; ```

</div>
<div id="viewer1"></div>


<div class="article-list-box-1 con table-box" id="detailTable">
	<table>
		<colgroup>
			<col width="100">
			<col width="200">
			<col width="150">
		</colgroup>
		<thead>
			<tr>
				<th style="background-color:#4769ff">ID</th>
				<td><%=article.getId()%></td>
				<th style="background-color:#4769ff">게시날짜</th>
				<td><%=article.getRegDate()%></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th style="background-color:#4769ff">제목</th>
				<td colspan="3" align="center"><%=article.getTitle()%></td>
			</tr>
			<tr>
				<th style="background-color:#4769ff">내용</th>

				<td colspan="3" rowspan="3" align="center"><%=article.getBody()%></td>
			</tr>
		</tbody>
	</table>
	<script>
	
	</script>
</div>
<%@ include file="/jsp/part/foot.jspf"%>

