
$(function(){
	var ops=$("#lvlone > select");
	ops.livequery("change",function(){
		var pid=$(this).val();
		getSelectData(pid);
	});
	
	var staffSelect=$("#group");
	staffSelect.livequery("change",function(){
		getStaffData($(this),"#staff");
	});
	
	//createWorkOrder
	var imeAssign=document.getElementById("imeAssign");//$("#imeAssign");
	//alert(imeAssign);
	addEventHandler(imeAssign,"click",showSelect);
	addEventHandler(imeAssign,"click",checkMe);
	
	var cGroup=$("#cGroup");
	cGroup.live("click",function(){
		fillSelect($(this))
	});
	var cStaffSelect=$("#cGroup");
	cStaffSelect.livequery("change",function(){
		getStaffData($(this),"#cStaff");
	});
});
//创建页面 读取玩家信息
function readContact(obj){
	//var accName=$();
	obj.live("blur",function(){
		$.ajax({
			type:"get",
			url:"/index/login_ContactInfoOfUser",
			data:{"accountname":$(this).val()},
			dataType:"json",
			cache:false,
			success:function(data){
				if(!data.empty){
					//alert(data.tel+":::"+data.qq);
					if(data.tel){
						$("input[name='userInfo.tel']").val(data.tel);
					}else{
						$("input[name='userInfo.tel']").val("");
					}
					if(data.qq)
						$("input[name='userInfo.qq']").val(data.qq);
					if(data.email)
						$("input[name='userInfo.mail']").val(data.email);
						
				}
			}
		});
	});
}
function fillSelect(obj){
	if($("option",obj).size()<=1){
	$.ajax({
		type: "GET",
		   url: "/json/query_getAssignGroup",
		   dataType: "json",
		   cache:false,
		   success:function(data){
			   if(data.empty!=null){
				   dialog.Alert(data.empty);
				   setTimeout(dialog.close,2000);//注意这里
				   obj.html("");
				   obj.append($("<option value='0'>-请选择-</option>"));
			   }else{
				   obj.html("");
				   obj.append($("<option value='0'>-请选择-</option>"));
					$(data.staffList).each(function(index) {
						$("<option value='"+this.id+"'>"+this.name+"</option>").appendTo(obj);
						  });
					obj.attr("disabled","");
			   }
		   }
});
	}
}
//派单页面
function getStaffData(obj,str){//根据小组查找员工
	
	var groupId=obj.val();
	//alert(groupId);
	var staff=$(str);
	
		$.ajax({
				type: "GET",
				   url: "/json/query_getStaffOfGroup",
				   dataType: "json",
				   data:{"toGroupId":groupId},
				   cache:false,
				   success:function(data){
					   if(data.empty!=null){
						   dialog.Alert(data.empty);
						   setTimeout(dialog.close,2000);//注意这里
						   staff.html("");
							staff.append($("<option value='0'>-请选择-</option>"));
					   }else{
							staff.html("");
							staff.append($("<option value='0'>-请选择-</option>"));
							$(data.staffList).each(function(index) {
								$("<option value='"+this.id+"'>"+this.name+"</option>").appendTo(staff);
								  });
							staff.attr("disabled","");
					   }
				   }
		});
	
}
var globePid=999;
function getSelectData(pids){//根据问题大类型选择二级类型
	//alert("select");
	var two=$("#lvltwo > select")
	var pid=parseInt(pids);
	
	//alert(pid);
	if(false){
		two.html("");
		two.append($("<option value='0'>-无二级类型-</option>"));
		//$(two).attr("disabled","disabled");
	}
	else{
		//if($("option",two).size()<=1){
				if(pid!=globePid){
				//	alert("do it");
					$.ajax({
						type: "GET",
						   url: "/index/grab_issueJson",
						   dataType: "json",
						   data:{"pid":pid},
						   cache:false,
						   success:function(data){
				//			   var two=$("#lvltwo > select")
							   if(data.confList && data.confList.length>0){
								$(two).html("");
								$(two).append($("<option value='999'>-请选择-</option>"));
								$(data.confList).each(function(index) {
									//alert(index);
									$("<option value='"+this.id+"'>"+this.name+"</option>").appendTo($(two));
									  });
								$(two).attr("disabled","");
							   }else{
								   	two.html("");
									two.append($("<option value='0'>-无二级类型-</option>"));
							   }
							   }
					});
					globePid=pid;
				}		
	//}
	}
}

function checkMe(){
	var evt=getEvent();
	var element=evt.srcElement || evt.target;
	check(element);
}
function check(checkedRadio)    
{    
	if(tempradio== checkedRadio){  
		tempradio.checked=false;  
		tempradio=null;  
	  }   
	  else{  
		  tempradio= checkedRadio;    
	   }  
 }    
function showSelect(){
	var evt=getEvent();
	var element=evt.srcElement || evt.target;
	//alert($(element).next().html());
	$(element).next().next().show();
}
function hideSelect(){
	var evt=getEvent();
	var element=evt.srcElement || evt.target;
	//alert($(element).next().html());
	$(element).next().next().show();
}
function addEventHandler(obj,eventName,fun){
	//alert("addEvent");
		if(window.addEventListener){
			obj.addEventListener(eventName, fun, false);
		}else if(window.attachEvent) {
			
			obj.attachEvent("on"+eventName, fun);
		}
}
function getEvent(){
	if(document.all){
		return window.event;//如果是ie
	}
	func=getEvent.caller;
	while(func!=null){
		var arg0=func.arguments[0];
		if(arg0){
			if((arg0.constructor==Event || arg0.constructor ==MouseEvent)||
					(typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
				return arg0;
			}
		}
		func=func.caller;
	}
	return null;
}