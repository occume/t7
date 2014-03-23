$(function () {
    $("#suggestionForm").validate({
       rules:{
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
            "userInfo.memo": {
              	required: "请填写咨询内容"
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
	var suggestionForm=$("#suggestionForm");
    if (suggestionForm.valid()) {
    	if (checkQQandPhone()){
    		submitAjax(suggestionForm,obj);
    		return false;
    	//suggestionForm.submit();
    	}
    }
    return false;
}