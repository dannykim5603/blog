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

<!-- CSS -->
<style>
h1 {
	margin:10px;
	text-align:center;
	color :white;	
}
.article-box {
	color : white;
}
.article-box > .article-table {
	border:double #444958;
}
.button {
	display:flex;
	justify-content: center;
}

.saveButton {
	
}
.disCate {
	margin:10px;
	display:flex;
	justify-content: center;
}
.titleInput {

}
</style>

<!--  글 작성  -->
<h1>글 작성</h1>

<div class="con article-box">
	<table class="con article-table" style=" width: 600px; height:600px;">
		<tbody>
			<form method="POST" name=form1 action="write" encType="application/x-www-form-urlencoded">
				<div class="disCate">
					<div class="displayStatus">
						게시물 공개 여부<select name="displayStatus">
							<option value="1">공개</option>
							<option value="0">비공개</option>
						</select>
					</div>
					<div class="cateItemId">
						카테고리<select name="cateItemId">
							<%for (CateItem cateItem : cateItems){%>
								<option value=<%=cateItem.getId() %>><%=cateItem.getName()%></option>
							<%} %>
						</select>
					</div>
				</div>
				<tr class= "title" style="height:50px;">
					<th>제목</th>
					<td><input type="text" placeholder=" 제목 " name="title" 	height="45px" maxlength="20" value="${param.title}" class="form-control titleInput"style="width:98%; height:45px;"></td>
				</tr>
				<tr class = "body">
					<th>내용</th>
					<td><textarea rows="85" cols="15" placeholder=" 내용 " name="body" value="${param.body}" class="form-control"style="width:98%"></textarea></td>
				</tr>
			</form>
		</tbody>
	</table>
</div>
<div class ="button"><button class="saveButton" type="submit" value="작성">작성</button></div>

<%@ include file="/jsp/part/foot.jspf"%>