<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MODIFY ARTICLE REPLY</title>
</head>
<body>

<style>
body {
	height:100%;
	background: #0f3854;
	background: radial-gradient(ellipse at center,  #0a2e38  0%, #000000 70%); 
	background-size: 100%;
	}
.modify-Reply-form {
	display:flex;
	margin-top:35px;
}
.modify-Reply-form > .input {
	margin-left: 10px;
	margin-right: 10px;
}
.modify-Reply-form > .body-box {
	width: 300px;
} 
.modify-Reply-form > .submit-box {
	margin-left:10px;
} 
</style>

<div class="doReplyModify" >
	<form action="doReplyModify" method="POST" class="modify-Reply-form">
	<input type="hidden" name="id" value="${articleReply.id}">
		<div class="label" style="color: white"> 수정 내용 </div>
		<div class="input">
			<input class="body-box" style="width:350px;"type="text" name="body" placeholder="${articleReply.body}" />
		</div>
		<div class="submit-box">
			<input class="submit" style="width: 40px;" type="submit" value="작성" onclick="self.close()"/>
			<button type="button" class="cancel" value="close" onclick="self.close()">취소</button>
		</div>
	</form>
</div>

</body>
</html>