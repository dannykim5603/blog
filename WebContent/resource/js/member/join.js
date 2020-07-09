var joinFormSubmitted = false;

function submitJoinForm(form) {
	if (joinFormSubmitted){
		alert ('처리중입니다.');
	}
	
  form.loginId.value = form.loginId.value.trim();
  if ( form.loginId.value.length == 0 ) {
    alert('아이디를 입력해주세요.');
    form.loginId.focus();
    
    return;
  }
  
  form.loginPw.value = form.loginPw.value.trim();
  if (form.loginPw.value.length == 0 ) {
    alert('비밀번호를 입력해주세요.');
    form.loginId.focus();
    
    return;
  }
  if (form.loginPw.value != form.loginPwC.value){
	  alert('비밀번호가 일치하지 않습니다.');
  		form.loginPw.focus();
  	return;
  }

  if ( form.loginId.value.indexOf(' ') != -1 ) {
	    alert('아이디를 영문소문자와 숫자의 조합으로 입력해주세요.')
	    form.loginId.focus();
	    
	return;
  }
  
  form.loginPwReal.value = sha256(form.loginPw.value);
  form.loginPw.value = '';
  form.loginPwC.value = '';
  
  form.submit();
  joinFormSubmitted = true;
}