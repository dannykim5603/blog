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
/*lib*/
h1 {
	margin: 10px;
	text-align: center;
	color: white;
}

.form1 {
	display: block;
	width: 100%;
}

.form1 .form-row {
	align-items: center;
	display: flex;
	margin : 10px;
}

.form1 .form-row:not (:first-child ) {
	margin-top: 10px;
}

.form1 .form-row>.label {
	width: 100px;
}

.form1 .form-row>.input {
	flex-grow: 1;
}

.form1 .form-row>.input>input, .form1 .form-row>.input>textarea {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.form1 .form-row>.input>select {
	padding: 10px;
}

.form1 .form-row>.input>textarea {
	height: 500px;
}

.form1 .form-row>.last-box {
	display: flex;
}

.form1 .form-row>.last-box>.submit-box {
	width: 50%;
	border-radius: 15px;
}

.form1 .form-row>.last-box>.cancel {
	background-color: white;
	color: black;
	font-weight: 400;
	radius: 15px;
	padding: 0px 40px;
	padding-top: 5px;
}

/*cus*/
@media ( max-width : 700px) {
	.form1 .form-row {
		display: block;
	}
}

.write-form-box {
	color: white;
	margin-top: 30px;
}
</style>

<!--  글 작성  -->
<h1>글 작성</h1>

<div class="write-form-box con">
	<form action="doWrite" method="POST" class="write-form form1"
		onsubmit="submitWriteForm(this); return false;">
		<div class="form-row">
			<div class="label">공개 여부</div>
			<div class="input">
				<select name="displayStatus">
					<option value="1">공개</option>
					<option value="0">비공개</option>
				</select>
			</div>
		</div>
		<div class="form-row">
			<div class="label">카테고리</div>
			<div class="input">
				<select name="cateItemId">
					<c:forEach items="${cateItems}" var="cateItem">
						<option value="${cateItem.id}">${cateItem.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-row">
			<div class="label">제목</div>
			<div class="input">
				<input name="title" type="text" placeholder="제목" />
			</div>
		</div>
		<div class="form-row">
			<div class="label">내용</div>
			<input type="hidden" name="body">
			<div id="editor1" style="width:93%"></div>
		</div>
		<div class="form-row">
			<div class="label"></div>
			<div class="input last-box">
				<input class="submit-box" style="width: 50%; border-radius: 15px;"
					type="submit" value="작성" /> <a
					style="border: 3px solid #444958; border-radius: 15px;"
					class="cancel" href="list">취소</a>
			</div>
		</div>
	</form>
</div>
<script>
	var editor1 = new toastui.Editor({
		el : document.querySelector("#editor1"),
		height : "600px",
		initialEditType : "markdown",
		previewStyle : "vertical",
		initialValue : "# 내용",
		plugins : [ toastui.Editor.plugin.codeSyntaxHighlight, youtubePlugin,
				replPlugin, codepenPlugin ]
	});

	function submitWriteForm(form) {
		var source = editor1.getMarkdown().trim();
		if (source.length == 0) {
			alert('내용을 입력해주세요.');
			editor1.focus();
			return;
		}
		form.body.value = source;
		form.submit();
	}
</script>

<%@ include file="/jsp/part/foot.jspf"%>