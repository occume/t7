$(function(){
		$("#assing-form").submit(function(){
			//alert("sb");
				var account=$("#assign-desc").val();
				var len=account.replace(/(^\s*)|(\s*$)/g, '').length;
				if(len<=3){
					dialog.Alert("派单描述不能为少于3个字！");
					setTimeout(dialog.close,3000);
					return false;
				}
				if(len>300){
					dialog.Alert("派单描述不能多余300字！");
					setTimeout(dialog.close,3000);
					return false;
				}
		});
	});