$(function () {
    $("#plugAbuseForm").validate({
       rules:{
   		"additional.username":{
    			checkUsername:true
//               required: true,
//                minlength:2,
//                maxlength:16
            },
        "userInfo.server.id":{
            	checkService:true
                },
        "additional.report.id":{
                	checkService:true
                    },
        "userInfo.memo":{
                   required: true
                    },
        "userInfo.tel":{
                    	checkPhone:true
                       }                                        
        },
        messages: {
        	"additional.username": {
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
            },
            "additional.report.id": {
            	checkService: "请填写举报类型"
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
	var plugAbuseForm=$("#plugAbuseForm");
    if (plugAbuseForm.valid()) {
    	//有上传文件的都用这个异步提交方法，不然无法提交文件
		submitAjaxForUploadFile(plugAbuseForm,obj);
		return false;
    	//plugAbuseForm.submit();
    }

    return false;
}