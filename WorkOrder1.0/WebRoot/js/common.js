$(function(){
	
	foldSwitch.init($(".topicMenu"));
	foldSwitch.bind();
	$("#getPostCode").click(function(){
		//alert("getPostCode");
		getPostCode();
		$("#postBoxClose").live("click",function(){
			
				$("#postBox").remove();
				$("#cityBox").remove();
		
		});
		
	});
	leftCountOfTextArea();
});

var foldSwitch={
		flag:0,
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
			var alterPsw=$("#acc-manage-left").find("#alterPsw");
			var alterNick=$("#acc-manage-left").find("#alterNick");
			var addStaff=$("#acc-manage-left").find("#addStaff");
			var deleteStaff=$("#acc-manage-right").find("#delete");
			var deleteGroup=$("#acc-manage-right").find(".deleteGroup");
			var createGroup=$("#acc-manage-left").find("#createGroup");
			
			alterPsw.live("click",function(){
				dialog.openUrl('/ctService/accManage/modifyPassword.jsp','400','300')
			});
			alterNick.live("click",function(){
				dialog.openUrl('/ctService/accManage/modifyNickName.jsp','400','300')
			});
			addStaff.live("click",function(){
				dialog.openUrl('/acc/acc_toAddStaff','400','300')
			});
			deleteStaff.live("click",function(e){
				var id=$("#hid"+e.target.title).val();
				dialog.Confirm("您确定要删除？", function(){
					delSth(id,"/acc/acc_delete");
				});
			});
			
			deleteGroup.live("click",function(){
				var id=$(this).siblings(".id").html();
				
				dialog.Confirm("您确定要删除？", function(){
					delSth(id,"/acc/acc_deleteGroup");
				});
			});
			createGroup.live("click",function(){
				dialog.openUrl('/acc/acc_toCreateGroup','auto','auto')
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
//$(document).ready(function(){
//	//alert($("#modiName"));
//	$("#modiName").click(function(){
//		var self=$(this);
//		var oDiv=$("#modifyName");
//		modifyNickName(oDiv);
//		self.unbind("click");
//	});
//	$("#mp").click(function(){
//		var self=$(this);
//		var oDiv=$("#modifyP");
//		modifyPassword(oDiv);
//		self.unbind("click");
//	});
//	
//});

function modifyPassword(object){
	var append="<form action='/wo/acc_modifyPassword' method='post'>"+
		"<br/>请输入旧密码：<input type='password' name='oldPassword'/><br/>"+
		"请输入新密码：<input type='password' name='password'/><br/>"+
		"请确认新密码：<input type='password' name='rePassword'/><br/>"+
		"<input type='submit' value='commit'/>"+
		"</form>";
	object.html(append).show("slow");
}
function modifyNickName(object){
	var append="<form action='/wo/acc_modifyNickName' method='post'>"+
		"<br/>请输入您的新昵称：<input type='text' name='nickName'/><br/>"+
		"<input type='submit' value='commit'/>"+
		"</form>";
	object.html(append).show("slow");
}

//角色名称相关规则验证
function checkUserName(v){
	$("#usernameErr").text("");
	if(isEmpty(v)){
		$("#usernameErr").text("*角色名称不能为空");
		return false;
	}
	if(v.length <2 || v.length > 16){
		$("#usernameErr").text("*角色名称长度为2至8个中文或4至15个英文");
		return false;
	}
	return true;
}
//qq格式验证
function checkQQ(v){
	$("#qqErr").text("");
	var qqReg = /^[1-9][0-9]{4,20}$/;
	if(!isEmpty(v)){
		if(!qqReg.test(v)){//验证其输入的格式
			$("#qqErr").text("*您输入的QQ无效");
			return false;
		}
	}	
	return true;
}
//电话号码验证
function checkPhone(phone){
	$("#telErr").html('');
	var phoneReg = /^[0-9]([0-9]|[-]){7,12}$/;
	if(isEmpty(phone)){
		$("#telErr").html("*联系电话不能为空");
		return false;
	}
	if(!phoneReg.test(phone)){
		$("#telErr").html("*联系电话应该为8-11位的数字");
		return false;
	}
	return true;
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
//时间格式验证方法
function checkHappendate(happendate){
	$("#happendateErr").text("");
	if(happendate.length == 0){
		$("#happendateErr").text("*时间不能为空");
		return false;
	}
	//var d=new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
	//return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&& d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r [6]&&d.getSeconds()==r[7]);
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

var whitespace = "\t\n\r";//定义空白字符
//function isEmpty(s){
//	var i;
//	if((s == null)||(s.length == 0)){
//		return true;
//	}
////	for(var j=0; j<s.length; j++){
////		var t = s.substring(j,j+1);
////		if(t==" " || t=="　"){ 
////			return true;	
////		}
////	}
//	for(i=0; i<s.length;i++){
//		var c = s.charAt(i);
//		if(whitespace.indexOf(c) == -1){
//			return false;
//		}
//	}
//	//在这点上，所有字符都是空白字符
//	return true;
//}



function checkQQandPhone()
{
	var QQ=$("input[name='userInfo.qq']");
	var qqValue=$("input[name='userInfo.qq']").val();
	var telValue=$("input[name='userInfo.tel']").val();
	if (isEmpty(qqValue)&&isEmpty(telValue))
	{
		QQ.parent().nextAll(".baocuo1").html("");
		dialog.Alert("请至少填写一种联系方式，以便在需要时能及时联系到您")
		setTimeout(dialog.close, 3000)
        $("<div class='errorMessage'>请至少填写一种联系方式</div>").appendTo(QQ.parent().nextAll(".baocuo1"));
		return false;
	}
	return true;
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
//异步提交表单
function submitAjax(form,obj)
{
	//alert("ajax");
	$(obj).attr("disabled","disabled");
	$("#inputErr").html("");
	$.ajax({
		url:$(form).attr("action"),
		cache:false,
		data:$(form).serialize(),
		type:"POST",
		dataType: "json",
		success:function(re){
		if (re.tag)
		{
			location.href=re.data;
		}
		else
		{
			$(obj).attr("disabled","");
			$("#inputErr").html(re.data);
			dialog.Alert("<div class=\"errorMsgPanel\">"+re.data+"</div>");
			setTimeout(function(){dialog.close();},2000);
		}
	}
	});
}


//如果有上传文件的话使用这个异步提交
function submitAjaxForUploadFile(form,obj)
{
	//alert("submit");
	$(obj).attr("disabled","disabled");
	clearInputErr();
	$(form).attr("target","_selfsubmitformtemp");
	if (document.getElementById("_selfsubmitformtemp")==null)
	{
		
		$("body").append("<iframe id='_selfsubmitformtemp' name='_selfsubmitformtemp' style='display:none'></iframe>");
	}
	//alert("befor submit");
	form.submit();
	return false;
}

function clearInputErr()
{
	$("#inputErr").html("");
}

function enabledsubmit_btn()
{
	$("#submit_btn").attr("disabled","");
	
}
/**
 * 根据城市选择邮编
 * @param obj
 * @return
 */
var currentPostCode=null;
var province=null;
var cities=null;
function getPostCode(obj){// 查询邮编
	//alert("postcode");
	var parent=$("#getPostCode");
	//alert(parent.width());
	if(!document.getElementById("postBox")){
		//alert("ddfdfad");
			genaratePostBox(parent);
			$.get("/globeConf/postCode.xml",function(data){
				province=$(data).find("province");
				//省级循环
			//	alert(province.length);
				$(province).each(function(){
					//alert("build province box");
					var name=$(this).attr("name");
					$("#postBox").find("ul").append(genarateCityOfProvince(name));
				});
				var postBox=$("#postBox");
				genarateCityBox(postBox)
				//var cities;
				posite(postBox,parent);
				postBox.find("li").live("click",function(){
					cities=$(data).find("province[name='"+$(this).html()+"']").find("city");
					//alert(cities.length);
					//市级循环
					var cityBox=$("#cityBox");
					
					var cityContainer=cityBox.find("ul");
					cityContainer.html("");
					if(cities.length>0){
							cityBox.show();
							$(cities).each(function(){
								
								var name=$(this).find("name").text();
								var postCode=$(this).find("post").text();
								cityContainer.append(genaratePostOfCity(name,postCode));
							});
					}else{
						cityBox.hide();
					}
					//alert("province bind");
					cityPosite(cityBox,postBox);
					$("#cityBox").find("li").live("click",function(){
						//alert($(this).attr("post"));
						currentPostCode=$(this).attr("post");
						setValue();
						return false;//防止冒泡
					});
					
					return false;
				});
			});
	}
	else{
		$("#postBox").remove();
		$("#cityBox").remove();
	}
}
function setValue(){
	var tel=$("#tel").val();
	var begin=tel.indexOf('-');
	if(begin!=-1){
		tel=tel.substring(begin+1)
	}
	$("#tel").val(currentPostCode+"-"+tel)
	$("#postBox").remove();
	$("#cityBox").remove();
}
function provinceBind(data){
	
}
function posite(c,p){//邮编框定位
	//alert(document.getElementById("getPostCode").offsetLeft);
	//alert(document.getElementById("getPostCode").offsetTop);
	var top=p.offset().top-c.height();
	if(!-[1,]){//if ie
		top=top-c.height()*10;
	}else{//if not ie
		top=top-c.height()/6;
	}
	//p.find("li").addClass("postLi");	
	c.css("left",p.offset().left+"px")
	.css("top",top+"px")
	.css("width",p.width()*3+"px");
}
function cityPosite(c,p){//邮编框定位
//	alert(c.html()+p.html());
	//alert(c.height());
//	var top=p.offset().top-c.height();
//	if(!-[1,]){//if ie
//		top=top-c.height()*10;
//	}else{//if not ie
//		top=top-c.height()/6;
//	}
	//p.find("li").addClass("postLi");	
	p=$("#postBox")
	c.css("left",p.offset().left+p.width()+17+"px")
	.css("top",p.offset().top+"px")
	//.css("width",p.width()*3+"px");
}
function genarateCityBox(p){//市级框
	//alert("burn a city box");
	if (document.getElementById("cityBox") == null) {
        $("body").append("<div id='cityBox'><ul></ul></div>");
    }
	var cityBox=$("#cityBox");
	cityBox.css("left",p.offset().left+"px")
	.css("top",p.offset().top-30+"px");
}
function genaratePostBox(p){//生成一个邮编选择框
	if (document.getElementById("postBox")==null) {
		//alert("burn a box");
        $("body").append("<div id='postBox'><ul></ul><div id='postBoxClose'>[关闭]</div></div>");
//        var postBox=document.createElement("div");
//        postBox.setAttribute("id", "postBox");
//       var postUl=document.createElement("ul");
//       postBox.appendChild(postUl);
       // $(document.getElementsByTagName("body")).append("<div id='postBox'><ul></ul><div id='postBoxClose'>[关闭]</div></div>");
    }
//	var postBox=$("#postBox");
//	postBox.css("left",p.offset().left+"px")
//	.css("top",p.offset().top-30+"px")
	//.css("width",p.width()*3+"px");
}
function genarateCityOfProvince(name){//
	var str="<li>"+name+"</li>"
	return str;
}
function genaratePostOfCity(name,postCode){//
	var str="<li post="+postCode+">"+name+"</li>"
	return str;
}



var returnHref="";

function checkLoginbySession(obj)
{
	$.ajax({
		url:"",
		async: false,
		cache:false,
		success:function(re){
			if (re)
			{
				location.href=$(obj).attr("href");
			}else
			{
				returnHref=$(obj).attr("href");
				showLogin();
			}
		}
	});
}


function showLogin()
{
	
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
       dialog.Alert("请不要超过字数限制哦~");
       setTimeout(dialog.close, 3000);
       $("#textAreaLeftCount").text(0)
    }
    else{
        $("#textAreaLeftCount").text(300-ta.val().length)
    }
}
/**
 * 输入框提示
 */
$(function(){
	var emp=$(".defaultHint").find("form");
	//alert(emp.find("#name").val());
	emp.find("#name").val("支持账号和昵称模糊查询");
	emp.find("#name").live("focus",function(){
		//alert("in");
		$(this).val("");
	});
	emp.live("blur",function(){
		//alert("sdfd");
		if($(this).find("#name").val()=="")
		$(this).find("#name").val("支持账号和昵称模糊查询");
		
	})
	emp.submit(function(){
		
		if(emp.find("#name").val()=="支持账号和昵称模糊查询"){
			emp.find("#name").val("");
		}
		return true;
	});
})