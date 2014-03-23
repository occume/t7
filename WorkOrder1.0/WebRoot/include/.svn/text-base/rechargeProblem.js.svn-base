$(function () {
    $("#roleRecoveryForm").validate({
       rules:{
   		"userInfo.username":{
               required: true,
                minlength:2,
                maxlength:16
            },
        "userInfo.server.id":{
            	checkService:true
                },
//        "recharge.rechargeType.id":{
//            	checkService:true
//                },        
        "userInfo.happendate":{
                	required: true
                    },
        "additional.offlineTime":{
                	required: true
                    },
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
            	required: "请填写发生时间"
            },
           	"additional.offlineTime":{
              	required: "请填写最后下线时间"
            },
            "userInfo.memo": {
              	required: "请填写说明"
            }//,
//            "recharge.rechargeType.id":{
//            	checkService:"请选择充值类型"
//            }
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
    
    $("#rechargeCard").blur(function(){
    	isAllNumber($(this), $(".rechargeCard"))
    });
    $("#rechargeSum").blur(function(){
    	isAllNumber($(this), $(".rechargeSum"))
    });
    $("#dname").blur(function(){
    	isNoOption($(this), $(".rechargeServer"))
    });
});

function rechargeTypeSelect(obj)
{
	switch(parseInt($(obj).val())){
	case 0:
		$("#cardname").html("充值卡号");
		break;
	case 1:
		$("#cardname").html("充值卡号");
		break;
	case 2:
		$("#cardname").html("银行订单号");
		break;
	case 3:
		$("#cardname").html("充值卡号");
		break;
	case 4:
		$("#cardname").html("充值卡号");
		break;
		$("#cardname").html("充值卡号");
	}
}

function register(obj)
{
	var roleRecoveryForm=$("#roleRecoveryForm");
    if (roleRecoveryForm.valid()) {
    	if(!isAllNumber($("#rechargeCard"), $(".rechargeCard"))){
    		return false;
    	}
    	if(!isAllNumber($("#rechargeSum"), $(".rechargeSum"))){
    		return false;
    	}
    	if(!isNoOption($("#dname"), $(".rechargeServer"))){
    		return false;
    	}
    	if (checkQQandPhone()){
    		//有上传文件的都用这个异步提交方法，不然无法提交文件
    		if(!isAllNumber($("#rechargeSum"), $(".rechargeSum"))){
    			return false;
        	}
    		submitAjaxForUploadFile(roleRecoveryForm,obj);
    		return false;
    	//roleRecoveryForm.submit();
    	}
    }

    return false;
}
function isAllNumber(obj,err){
	var numReg=/^[0-9]{0,15}$/;
	var _val=obj.val();
	if(!isEmpty(_val)){
		err.html("");
		if(!numReg.test(_val)){//验证其输入的格式
			err.html("请输入数字");
			return false;
		}else{
			err.html("");
		}
		return true;
	}
	return true;
}
function isNoOption(obj,err){
	var _val=obj.val();
	if(_val==0){
		err.html("请选择充值类型");
			return false;
		}else{
			err.html("");
		}
	return true;
}