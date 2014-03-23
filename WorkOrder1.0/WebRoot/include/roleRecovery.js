$(function () {
    $("#roleRecoveryForm").validate({
       rules:{
   		"userInfo.username":{
    			checkUsername:true
//               required: true,
//                minlength:2,
//                maxlength:16
            },
        "userInfo.server.id":{
            	checkService:true
                },
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


});


function register(obj)
{
	var roleRecoveryForm=$("#roleRecoveryForm")
    if (roleRecoveryForm.valid()) {
    	if (checkQQandPhone()){
    		submitAjax(roleRecoveryForm,obj);
    		return false;
    	//roleRecoveryForm.submit();
    	}
    }

    return false;
}