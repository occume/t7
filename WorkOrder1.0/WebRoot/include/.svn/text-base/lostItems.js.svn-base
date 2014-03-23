$(function () {
	//物品名称输入框自动补全
//	var necc=$(".necessaryName");
//	//alert(necc.length);
//	$.each($(".necessaryName"),function(i,n){
////		alert($(n).parent().html());
//		var readGoodsName=new matchFun(n);
//		readGoodsName.init();
//	})
	
	//添加多个物品输入
	
	$(".iconadd").click(function(){
		createInput(this);
		var necc=$(".necessaryName");
		$.each(necc,function(i,n){
			if(isEmpty($(n).val())){
				var read=new matchFun(n);
				read.init();
			}
		})
		$("#deleteInput").live("click",function(){
			deleteInput($(this));
		});
		$(".formrigcont41").live("mouseover",function(){
			$(this).find("#deleteInput").show();
		});
		$(".formrigcont41").live("mouseout",function(){
			$(this).find("#deleteInput").hide();
		});
	});
	//表单提交前验证
    $("#loseItemsForm").validate({
        rules:{
    		"userInfo.username":{
     			checkUsername:true
//                	required: true,
//                 minlength:2,
//                 maxlength:16
             },
         "userInfo.server.id":{
             	checkService:true
                 },
         "userInfo.happendate":{
                 	required: true
                     },
//         "additional.offlineTime":{
//                 	required: true
//                     },
         "userInfo.qq":{
                   	checkQQ:true    
                     },
         "userInfo.memo":{
                    required: true
                     },
         "userInfo.tel":{
                     	checkPhone:true
                        }                                        
         },
         messages: {
         	"userInfo.username": {
                 required: "角色名称不能为空",
                 minlength:"角色名称长度为2至8个中文或4至15个英文",
                 maxlength:"角色名称长度为2至8个中文或4至15个英文"
             },
             "userInfo.happendate":{
             	required: "请至少填写起始时间"
             },
            	"additional.offlineTime":{
               	required: "请填写最后下线时间"
             },
             "userInfo.memo": {
               	required: "请填写说明"
             }
         },
         errorPlacement: function (error, element) {
         	element.parent().nextAll(".baocuo1").html("");
             error.appendTo(element.parent().nextAll(".baocuo1"));
         },
         errorClass: "errorMessage",
         errorElement: "div",
         focusInvalid: true,
         keyup: true,
         success: "successMessage"

     });
    $(".requeidGoods").blur(function(){
    	isAllNumber($(this), $(".goodsErr"))
    });
    $(".purpleGold").blur(function(){
    	isAllNumber($(this), $(".purpleErr"))
    });
    $(".gameCoin").blur(function(){
    	isAllNumber($(this), $(".gameCoinErr"))
    });
    //var goodsLostForm=document.getElementById("loseItemsForm");
   //alert($(goodsLostForm).html())
 //   $(goodsLostForm).submit(function(){
    	//alert("submit");有用的
   // });
//    addEventHandler(goodsLostForm,"submit",function(){
//    	alert("dfdfdf");
//    	var qqReg = /^[0-9]{4,}$/;
//    	var v=$(".requeidGoods");
//    	if(!isEmpty(v)){
//    		if(!qqReg.test(v)){//验证其输入的格式
//    			$(".goodsErr").text("请输入数字");
//    			return false;
//    		}
//    	}
//    });
 
});


function register(obj)
{
	//alert("regester");
	var losItemsForm=$("#loseItemsForm");
    if (losItemsForm.valid()) {
    	//alert("pass");
    	if (!checkQQandPhone()){
    	//	submitAjax(loseItemsForm,obj);
    		return false;
    		//loseItemsForm.submit();
    	}
    	//alert("qq phone over");
    	if(!isAllNumber($(".requeidGoods"), $(".goodsErr"))){
    			return false;
    	}
    	if(!isAllNumber($(".purpleGold"), $(".purpleErr"))){
    		//alert("purple")
			return false;
    	}
    	if(!isAllNumber($(".gameCoin"), $(".gameCoinErr"))){
			return false;
    	}
    	var necessaryNames=$(".necessaryName");
    	for(var i=0;i<necessaryNames.length;i++){
    	//alert(isEmpty($(necessaryNames[i]).val()))
    		if(!isEmpty($(necessaryNames[i]).val())){
    			break;
    		}
    		if(i==necessaryNames.length-1){
    			dialog.Alert("请至少填写一种丢失物品");
    			setTimeout(dialog.close, 2000)
    			return false;
    		}
    	}
    	//alert("can send");
    	submitAjaxForUploadFile($("#loseItemsForm"),obj);
    }

    return false;
}

var index=0;
function createInput(obj){
	var container=$(obj).parent().parent();
	if($(".jd-gap").find(".formrigcont4").length<=9){
		container.append("<div class=\"formrigcont4  formrigcont41\">"+
															"<div class=\"formrigcont5\">"+
																"名称：<input class=\"input100 necessaryName\" maxlength=\"15\"  type=\"text\" name=\"goodses"+index+"\"/></div>"+
															"<div class=\"formrigcont5\">"+
																"属性：<input class=\"input100\" type=\"text\" maxlength=\"15\" name=\"goodses"+index+"\"/></div>"+
															"<div class=\"formrigcont5\">"+
																"数量：<input class=\"input100\" type=\"text\" maxlength=\"7\" name=\"goodses"+index+"\" value='1' /></div>"+
																"<span id='deleteInput' style='font-size:25px;'>×</span>"+
														"</div>"
																);
	}else{
		dialog.Alert("您提交的物品丢失数量过多，请分开提交~");
		setTimeout(dialog.close, 2000);
	}
	index++;
}
function deleteInput(obj){
	obj.parent().remove();
}
function addEventHandler(obj,eventName,fun){
//	alert("addEvent");
		if(window.addEventListener){
			//alert("ff");
			obj.addEventListener(eventName, fun, false);
		}else if(window.attachEvent) {
			//alert("ie");
			
			obj.attachEvent("on"+eventName, fun);
		}
}