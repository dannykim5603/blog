<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/jsp/part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
.join-form-box {
	color: white;
	display: flex;
	justify-content: center;
}

.form1 {
	width: 500px;
	border: outset #444958 3px;
	padding: 20px;
	margin-top: 100px;
}

.form1>.form-row {
	width: 100%;
	margin-bottom:10px;
}

.form1>.form-row>.label {
	padding-top: 20px;
	padding-bottom: 20px;
}

.form1>.form-row>.input>input {
	width: 90%;
	margin-left: 20px;
	height: 25px;
}

.form1 > .findPw {
	padding-top:10px;
	text-align:center;
	
}

.form1 > .form-row:last-child > .label {
	padding:5px;
}

.findPw{
	margin-top:20px;
}

.findPw:hover{
	color:red;
}

.last-box{
	margin-top:20px;
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
		
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		
		form.submit();
		submitJoinFormDone = true;
	}
</script>

<div class="join-form-box con">
	<form action="doFindId" method="POST" class="join-form form1" onsubmit="submitJoinForm(this); return false;">
		<div class="form-row">
			<div class="label">E-MAIL</div>
			<div class="input">
				<input name="email" type="email" placeholder="E-mail " />
			</div>
		</div>
		<div class="form-row">
			<div class="label">NAME</div>
			<div class="input">
				<input name="name" type="text" placeholder=" NAME "></input>
			</div>
		</div>
		<div class="findPw">
			<a href="../member/findPw">비밀번호 찾기</a>
		</div>
		<div class="form-row">
			<div class="label"></div>
			<div class="input last-box">
				<input class="submit-box" style="width: 20%;"
					type="submit" value="아이디 찾기" /> <a class="cancel" href="home">취소</a>
			</div>
		</div>
	</form>
</div>

<%@ include file="/jsp/part/foot.jspf"%>