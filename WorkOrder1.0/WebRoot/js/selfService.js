$(document).ready(function(){
	$("#username").focus();
});

//角色恢复参数提交验证
function checkInput(f){
	if(!checkUserName($("#username").val())){
		return false;
	}
	if(!checkPhone($("#tel").val())){
		return false;
	}
	if(!checkQQ($("#qq").val())){
		return false;
	}
	if(!checkLimit($("#memo").val())){
		return false;
	}
	document.roleRecoveryForm.submit();
	return true;
}
//服务器故障参数提交验证
function checkServerFailureInput(){
	if(!checkHappendate($("#happendate").val())){
		return false;
	}
	if(!checkPhone($("#tel").val())){
		return false;
	}
	if(!checkQQ($("#qq").val())){
		return false;
	}
	
	if (!checkService($("#name").val()))
	{
		return false;
	}
	if(!checkLimit($("#memo").val())){
		$("#memoErr").html("请输入说明");
		return false;
	}
	//alert("参数全部验证完");
	document.serverFailureForm.submit();
	return true;
}

function checkService(value)
{
	if (value=="0")
	{
		$("#serviceErr").html("请选择服务器");
		return false;
	}
	return true;
	}


//物品丢失参数提交验证
function checkLoseItemsInput(){
	if(!checkUserName($("#username").val())){
		return false;
	}
	if(!checkHappendate($("#happendate").val())){
		return false;
	}
	if(!checkPhone($("#tel").val())){
		return false;
	}
	if(!checkQQ($("#qq").val())){
		return false;
	}
	if(!checkLimit($("#memo").val())){
		return false;
	}
	document.loseItemsForm.submit();
	return true;
}
/**
 * 游戏bug 游戏运行 角色异常 提交参数验证
 * @return
 */
function checkRoleAbnormal(){
	alert("abnormal")
	if(!checkUserName($("#username").val())){
		return false;
	}
	if(!checkHappendate($("#happendate").val())){
		return false;
	}
	alert("happendate end");
	if(!checkPhone($("#tel").val())){
		return false;
	}
	if(!checkQQ($("#qq").val())){
		return false;
	}
	if(!checkLimit($("#memo").val())){
		return false;
	}
	document.roleAbnormalForm.submit();
	return true;
}
/**
 * 违规举报 提交参数验证
 * @return
 */
function checkPlugAbuseInput(){
	if(!checkUserName($("#username").val())){
		return false;
	}
	if(!checkPhone($("#tel").val())){
		return false;
	}
	if(!checkQQ($("#qq").val())){
		return false;
	}
	if(!checkLimit($("#memo").val())){
		return false;
	}
	alert("参数全部验证完");
	document.plugAbuseForm.submit();
	return true;
}
/**
 * 意见建议和咨询问题验证
 * @return
 */
function checkAdvisory(f){
	alert("abnormal")
	if(!checkPhone($("#tel").val())){
		return false;
	}
	if(!checkQQ($("#qq").val())){
		return false;
	}
	if(!checkLimit($("#memo").val())){
		return false;
	}
	f.submit();
	return true;
}
//检查文本域输入长度[不得少于3个小，且高于300字]
function checkMemo(memo,limitMin,limitMax){
	$("#memoErr").text("");
	if(!isEmpty(memo)){
		if(!checkLimit(memo,limitMin,limitMax)){
			$("#memoErr").text("*内容不得少于" +limitMin +"个字且最多可输入" + limitMax + "个字");
			return false;
		}
	}
	
	return true;
}