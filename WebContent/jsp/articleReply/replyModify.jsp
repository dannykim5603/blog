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
</style>

<div class="doReplyModify" >
	<form action="doReplyModify" method="POST" class="modify-Reply-form">
		<div class="label"> 수정 내용 </div>
		<div class="input">
			<textarea name="body" placeholder=""></textarea>
			<button type=button name="body" value="modify" onclick="location.href='../article/replyModify?id=${param.id}';close()">수정
			</button>
		</div>
	</form>
</div>

</body>
</html>