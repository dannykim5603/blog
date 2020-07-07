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

	<h1>글 작성</h1>

	<div class="con article-box" style="background-color: white">
		<table border="1" style="width: 600px">
			<tbody>
			<form method = "post" name= form1 action="doWrite" encType="application/x-www-form-urlencoded">
				<tr>
					<th>공개 여부</th>
					<td><input type="text" placeholder = "공개 여부" name="displayStatus" value="${param.displayStatus}" class="form-control"/></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td><input type="text" placeholder="카테고리 번호" name="cateItemId" value="${param.cateItemId}" class="form-control"/></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" placeholer=" 제목 " name="title" size="20" maxlength="20" value ="${param.title}" class="form-control"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="85" cols="15" placeholer=" 내용 " name="body" value="${param.body}" class= "form-control"></textarea></td>
				</tr>
				</form>
			</tbody>
				<button style = "" type="submit" value="작성">작성</button>
		</table>
	</div>

	<%@ include file="/jsp/part/foot.jspf"%>