$(function(){
	$(".NeedEnterSubmit").keypress(function(event){
	var k=window.event?event.keyCode:event.which;
	if (k == '13') {
		$(".NeedEnterSubmit a.submit").click();
	     event.preventDefault();
	   }
	});
});
function checkInput(f){
	//alert("checkInput");
	var accountnameValue = $("#accountname").val();
	if(!chAccoutname(accountnameValue)){		
		return false;
	}
	var passwordValue = $("#password").val();
	if(!chPassword(passwordValue)){
		return false;
	}
	var checkcodeValue = $("#identifyingCode").val();
	if(!checkcode(checkcodeValue)){
		return false;
	}
	f.submit();
	return true;
}

//验证火游帐号
function chAccoutname(v) { //返回true表示不能提交，false表示可以
	if(v.length == 0){
		$("#uiderr").text("*火油账号不能为空");
		return false;
	} 
	if(!chuLength(v)){
		$("#uiderr").text('*火油账号长度应为6-16位');
		return false;
	} 
	if(!isChar(v)){
		$("#uiderr").text('*火油账号首位为英文字母');
		return false;
	} 
	if (!chu(v)) {
		$("#uiderr").text('*火油账号是由英文字母和数字组成');
		return false;
	}
	$("#uiderr").text("");
	return true;
}
//检查密码
function chPassword(v) {
	$("#uiderr").text("");
	if(v.length == 0){
		$("#uiderr").text("*密码不能为空");
		return false;
	}
//	if(isEmpty(v)){
//		$("#uiderr").text("*密码不能为空或使用空格");
//		return false;
//	}
	if (!chp(v)) {
		$("#uiderr").text("*密码长度不足6位");
		return false;
	} 
	if(isSameChar(v)){
		$("#uiderr").text("*密码不能使用相同字符");
		return false;
	} 
	$("#uiderr").text("");
	return true;
}
//验证码验证
function checkcode(v){
	var regStr = /^[a-zA-Z0-9]{4,6}$/;
	if(v.length == 0){
		$("#uiderr").text('*请输入验证码');
		return false;
	}
	if(!regStr.test(v)){
		$("#uiderr").text('*验证码无效');
		return false;
	}
	$("#uiderr").text("");
	return true;		
}
/**
 * 检查密码是否完全相同
 * @param {Object} s
 * @return {TypeName} 
 */
function isSameChar(s){	
   var fChar = s.substring(0,1);   
   for(i=0; i<s.length; i++){	 
	 if(fChar != s.substring(i,i+1)){
	     return false;
	 }
   }
   return true;
}
function chp(v) {return !(v.length<6 || v.length>20);}
function chuLength(v){return !(v.length<6 || v.length>16);}
function isChar(s) {var c=/^[a-zA-Z]$/; return c.test(s.substring(0,1));}
function chu(v)	{var m5=/^[a-zA-Z][a-zA-Z0-9]{5,15}$/;	return m5.test(v);}
//游戏帐号失去焦点验证方法
function checkAccountState(){
	alert("游戏帐号失去焦点验证方法");
	if(!chAccoutname($("#accountname").val())){//验证游戏帐号基本格式
		return false;
	}
	$.get("/index/login_check" ,$("#loginForm").serializeArray(),
	//指定回调函数
	function(data, statusText){//查询此帐号是否存在   	
		$("#accountnameState").val("true");
		alert(data);	
		alert(statusText);
		alert(data == "游戏账号不存在");
		if(data == "游戏账号不存在"){
			$("#accountnameState").val("false");
		}
		alert($("#accountnameState").val());
		alert("false"==$("#accountnameState").val());
		if("false"==$("#accountnameState").val()){
			alert("游戏账号不存在");
		}
		$("#uiderr").text("*" + data);//.append(data);
		$("#uiderr").show(2000);
	},
	//指定服务器响应为HTML
	"html");
}
