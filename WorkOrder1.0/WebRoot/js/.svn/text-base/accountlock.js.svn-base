$(function () {
    $("#accountlockForm").validate({
       rules:{
    	"userInfo.accountname":{
    		checkAccountname:true
	     },
   		"userInfo.username":{
	    	 checkUsername:true
            },
        "userInfo.server.id":{
            	checkService:true
                },
        "userInfo.happendate":{
//                checkHappendate:true
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
        	"userInfo.accountname": {
	            required: "游戏账号不能为空",
	            minlength:"游戏账号长度为6至16个英文字母、数字"
	        },
        	"userInfo.username": {
                required: "角色名称不能为空",
                minlength:"角色名称长度为2至8个中文或4至15个英文",
                maxlength:"角色名称长度为2至8个中文或4至15个英文"
            },
            "userInfo.happendate":{
            	required: "请填写发生时间"
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
	var accountlockForm=$("#accountlockForm");
    if (accountlockForm.valid()) {
    	if (checkQQandPhone()){
    		//有上传文件的都用这个异步提交方法，不然无法提交文件
    		//accountlockForm.submit();
    		submitAjaxForUploadFile(accountlockForm,obj);
    		return false;

    	}
    }

    return false;
}