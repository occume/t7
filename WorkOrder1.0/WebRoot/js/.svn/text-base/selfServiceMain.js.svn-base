$(function(){
	//alert("selfServiceMain");
	//init();
});

function init(){
	//alert("init");
	if(!document.getElementById("tipBox")){
		$("body").append("<div id='tipBox'><div id='innerTipbox'></div></div>");
	}
	var tipBox=$("#tipBox");
	 $(".quckservice").find("a").live("mouseover",function(e){
		 	doHint($(this),tipBox,e);
	 });
	 $(".quckservice").find("a").live("mouseout",function(){
		 	doHintHide(tipBox);
	 });
}
function doHint(obj,tipBox,e){
	var hintWord=obj.next("span").html();
	//$(obj).mousemove(function(e){
	var bTop=obj.offset().top;
	var bLeft=obj.offset().left;
		tipBox.find("#innerTipbox").html(hintWord);
		tipBox.css({top:bTop,left:bLeft+95});
		//tipBox.find("#innerTipbox").show();
		tipBox.show();
	//});
}
function doHintHide(tipBox){
	tipBox.hide();
}
