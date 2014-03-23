$(function(){
	var appear=false;
	$("#lookDetail").click(function(){
		var me=$(this);
		var woDetailBox=$("#wo-detail-box");
		if(!appear){
			lookDetailPosite(woDetailBox,$(this))
			if(!woDetailBox.html()){
				woDetailBox.html("无更多信息");
				setTimeout(function(){
					woDetailBox.html("");
					woDetailBox.fadeOut();
					me.html("[更多信息]");
					appear=false;
				}, 2000)
			}
			woDetailBox.fadeIn();
			$(this).html("[关闭信息]");
			appear=true;
			}else{
				woDetailBox.fadeOut();
				$(this).html("[更多信息]");
				appear=false;
			}
	});
	leftCountOfTextArea();
})
function lookDetailPosite(c,p){//邮编框定位
	c.css("left",p.offset().left+p.width()+"px")
	.css("top",p.offset().top+"px")
	.css("width",p.width()*3+"px");
}


//问题补充提交验证
function checkAddInput(f){
	var addValue = $("#addMemo").val();
	var str=addValue.replace(/(^\s*)|(\s*$)/g,'');
	if(str.length>100){
		dialog.Alert("少年，不要超过一百个字！");
		setTimeout(dialog.close, 3000)
		//$("#addMemoErr").text("少年，不要超过一百个字哦！");
		return false;
	}
	if(isEmpty(addValue)){
		$("#addMemoErr").text("少年，多多少少写几个字！");
		return false;
	}
	if(!checkAddMemo(addValue)){
		return false;
	}
	f.submit();
	document.getElementById(".buttonYes").getElementsByTagName("a")[0].innerHTML="处理中";
	return true;
	
}
//评价提交验证
function checkEvalInput(f){
	var radio = document.getElementsByName("evaluation");
	var selectnum = 0;
	for(var i=0;i<radio.length;i++){
		if(radio[i].checked){
			selectnum = 1;
		}
	}
	if(selectnum == 0 && $("#comment").val().length == 0){
		$("#commentErr").text("*您未做任何评价无须提交");
		return false;
	}
	f.submit();
	return true;
}
//对评语输入的验证
function checkComment(comment,limitMin,limitMax){
	$("#commentErr").text("");
	if(!isEmpty(comment)){
		if(!checkLimit(comment,limitMin,limitMax)){
			$("#commentErr").text("*内容不得少于" +limitMin +"个字且最多可输入" + limitMax + "个字");
			return false;
		}
	}	
	return true;
}
//对评语输入的验证
function checkAddMemo(addMemo,limitMin,limitMax){
	$("#addMemoErr").text("");
	if(!isEmpty(addMemo)){
		if(!checkLimit(addMemo,limitMin,limitMax)){
			$("#addMemoErr").text("*内容不得少于" +limitMin +"个字且最多可输入" + limitMax + "个字");
			return false;
		}
	}	
	return true;
}
/***
 * 文本框
 */
function leftCountOfTextArea(){
	var ta=$(".myrate").find("#comment");
	ta.keydown(function(){
		countWord($(this))
	});
	ta.keyup(function(){
		countWord($(this))
	});
	ta.blur(function(){
			countWord($(this))
	});
}
function countWord(ta){
	var curLength=ta.val().length;
    if(curLength>=100){
        var num=ta.val().substr(0,100);
       ta.val(num);
    }
    else{
        $("#leftCount").text(100-ta.val().length)
    }
}