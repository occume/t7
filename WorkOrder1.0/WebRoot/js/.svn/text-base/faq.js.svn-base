$(function(){
	
	foldSwitch.init($(".topicMenu"));
	foldSwitch.bind();
	leftCountOfTextArea();
});

var foldSwitch={
		flag:0,
		deleteUrl:"/faq/deleteFaq",
		init:function(obj){
			obj.click(function(){
				if(foldSwitch.flag==0){
					foldSwitch.fold(obj);
					foldSwitch.flag=1;
				}else{
					foldSwitch.unfold(obj);
					foldSwitch.flag=0;
				}
			});
		},
		fold:function(obj){
			obj.siblings(".alter").slideDown();
		},
		unfold:function(obj){
			obj.siblings(".alter").slideUp();
		},
		bind:function(){
			var addFaq=$("#acc-manage-left").find("#addFaq");
			var editFaq=$("#acc-manage-left").find("#editFaq");
			var btnSure=$(".popBottom").find("#btn_success");
			var deleteFaq=$("#deleteFaq");
			var editFaq=$("#editFaq");
			var publishFaq=$("#publishFaq");
			var inPublishFaq=$("#inPublishFaq");
			
			addFaq.live("click",function(){
				dialog.openUrl('/ctService/faqManage/addFaq.jsp',400,380)
				//window.open ('/ctService/faqManage/addFaq.jsp', 'addWindow', 'height=300, width=400, top=350, left=500, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no,status=no')
			});
			
			btnSure.live("click",function(){
					parent.dialog.close();
					window.parent.location.href=window.parent.location.href;
			});
			$("#saveFaq").submit(function(){
					dialog.Alert("操作进行中，请稍后...");
					return true;
			});
			deleteFaq.live("click",function(){
					var self=this;
					dialog.Confirm("确定删除？", function(){
							var id=$(self).attr("title");
							alert(id);
							foldSwitch.deleteOrPublishFaq(id,"/faq/deleteFaq");
							dialog.Alert("操作进行中，请稍后...");
					})
			});
			editFaq.live("click",function(){
				
			});
			publishFaq.live("click",function(){
				var self=this;
				dialog.Confirm("确定发布？", function(){
						var id=$(self).attr("title");
						
						foldSwitch.deleteOrPublishFaq(id,"/faq/publishFaq");
						dialog.Alert("操作进行中，请稍后...");
				})
			});
			inPublishFaq.live("click",function(){
				var self=this;
				dialog.Confirm("确定取消发布？", function(){
						var id=$(self).attr("title");
						foldSwitch.deleteOrPublishFaq(id,"/faq/inPublishFaq");
						dialog.Alert("操作进行中，请稍后...");
				})
			});
		},
		deleteOrPublishFaq:function(id,url){
									$.ajax({
										url:url,
										cache:false,
										data:{"id":id},
										type:"POST",
										dataType: "json",
										success:function(data){
										if(data.message){
											dialog.Alert(data.message+"，搞定了！");
										}else{
											dialog.Alert("操作出现异常！");
										}
										setTimeout(function(){
											dialog.close();
											window.location.href=window.location.href;
										},2000);
										
									}
									});
							}
}
function delSth(id,url){
	//alert("sth");
	$.ajax({
		url:url,
		cache:false,
		data:{"id":id},
		type:"POST",
		dataType: "json",
		success:function(data){
		if (data.message){
			dialog.Alert(data.message);
			//location.href=re.data;
		}else{
			dialog.Alert("删除出现异常！");
		}
		setTimeout(dialog.close,2000);
		window.location.href=window.location.href;
	}
	});
}


//检查文本域输入长度[不得少于3个小，且高于300字]
function checkLimit(memo,limitMin,limitMax){
	//$("#memoErr").text("");
	if(!isEmpty(memo)){
		if(memo.length < limitMin||memo.length > limitMax){
			//$("#memoErr").text("*内容不得少于" +limitMin +"个字且最多可输入" + limitMax + "个字");
			return false;
		}
	}
	
	return true;
}


function isEmpty(s){
	if(s.replace(/(^\s*)|(\s*$)/g, '').length<=0)
	{//为空
	return true;
	}
	else{//不为空
	return false;
	}
	}






function isAllNumber(obj,err){
	var numReg=/^[0-9]{0,10}$/;
	var _val=obj.val();
	if(!isEmpty(_val)){
		err.html("");
		if(!numReg.test(_val)){//验证其输入的格式
			err.html("请输入数字");
			return false;
		}
		return true;
	}
	return true;
}






/**
 * 文本框剩余字数统计
 * @return
 */
function leftCountOfTextArea(){
	var ta=$(".formtextareacont").find("textarea");
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
    if(curLength>=300){
        var num=ta.val().substr(0,300);
       ta.val(num);
       dialog.Alert("请不要超过字数限制>_<");
       setTimeout(dialog.close, 3000);
       $("#textAreaLeftCount").text(0)
    }
    else{
        $("#textAreaLeftCount").text(300-ta.val().length)
    }
}