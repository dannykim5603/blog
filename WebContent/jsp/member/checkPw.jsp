<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<%

Member member = (Member)request.getAttribute("member");

%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
.join-form-box {
	color: white;
	display: flex;
	justify-content: center;
	width:500px;
	margin-top:200px;
}

.form1 {
	border : outset #444958 3px;
	width: 80%;
	margin-top: 100px;
}

.form1>.form-row {
	width: 100%;
}

.form1>.form-row>.label {
	padding-top: 20px;
	padding-bottom: 20px;
	margin-left:10px;
}

.form1>.form-row>.input>input {
	width: 90%;
	margin-left: 20px;
	height: 25px;
}
.last-box{
	margin-bottom:10px;
	text-align:center;
}

.last-box>a {
	background-color: white;
	color: black;
	font-weight: thin;
	padding: 0px 40px;
}
</style>

<script>
	var submitJoinFormDone = false;
	function submitJoinForm(form) {
		if ( submitJoinFormDone ) {
			alert('처리중 입니다.');
			return;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('로그인 비번을 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = '';
		
		form.submit();
		submitJoinFormDone = true;
	}
</script>

<div class="join-form-box con">
	<form action="doCheckPw" method="POST" class="join-form form1" onsubmit="submitJoinForm(this); return false;">
		<input type="hidden" name="loginPwReal">

		<div class="form-row">
			<div class="label">loginPw</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder=" login PW "></input>
			</div>
		</div>
		
		<div class="form-row">
			<div class="label"></div>
			<div class="input last-box">
				<input class="submit-box" style="width:100px;" type="submit" value="확인" />
				<a style="border: 1px solid #444958;" class="cancel" href="../home/main">취소</a>
			</div>
		</div>
		
	</form>
</div>

<%@ include file="/jsp/part/foot.jspf"%>