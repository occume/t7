function changeImage(){
	var time = new Date().getTime();
	var imgCode = $("#checkCodeImg");
	if (imgCode != undefined) {
		imgCode.html('');		
		var imgsrc = "../selfService/checkcode.jsp?nt="+time;
		imgCode.html('<img src= '+ imgsrc +' border=0 alt="图片验证码" name="code" width="80" height="30" border="0" align="middle" id="srccode">');
	}
	var identifyCode = $("#identifyingCode");
	if (identifyCode != undefined) {
		identifyCode.val('');
		identifyCode.focus();
	}
}