$(function(){
///	alert("df");
//	 var site=$("#site");
//	 site.keyup(function(){
//		 var self=$(this);
//		 var follow=self.parent().siblings("#follow");
//		 grabGoodsItems("abc",follow,self);
////		 initFollowMe(self);
////		 window.onresize=function(){
////				initFollowMe(self)
////			};
//	 });
	//alert("test");
	var match=new matchFun("#site");
		    match.init();
})
function matchFun(boss,follow){
	this.boss=$(boss);
	this.follow=$(follow);
	this.keyWord="";
	this.primiVal="";
	this.index=-1;
	this.obvious =false;
}
matchFun.prototype={
		init:function(){
	//alert("init");
			 if (document.getElementById("follow") == null) {
		         $("body").append("<div id='follow'><ul></ul></div>");
		     }
			 this.follow=$("#follow");
			var self=this;
//			var site=$(""+this.boss);
//			var _follow=site.parent().siblings(""+this.follow);
			this.boss.live("keyup",function(event){
				//alert(event.keyCode);
				//alert(self.boss.val());
				if(!(event.keyCode==13||event.keyCode==38||event.keyCode==40)){
					
					self.locate();
					var str=self.boss.val();
					//alert(str);
					//alert(str.replace(/(^\s*)|(\s*$)/g,''));
					var keyword=str.replace(/(^\s*)|(\s*$)/g,'');
					if(keyword.replace(/(^\s*)|(\s*$)/g,'')==""){ 
						self.follow.hide();
						self.obvious=false;
						return;
					}
					if(self.primiVal==keyword){//如果两次输入相同
						return;
					}
					self.grabGoodsItems(keyword,self.follow,self.boss);
					self.primiVal=keyword;
				}
				self.keyHandler(event);
				self.mouseHandler();
				window.onresize=function(){
					self.locate();
				}
			});
			
		},
		
		locate:function(){
			//alert("locate");
			var p=this.boss;
			//alert(p);
			 this.follow.css("left",p.offset().left+"px")
				.css("top",p.offset().top+p.height()+6+"px")
				.css("width",p.width()+3+"px");
		},
		grabGoodsItems:function(keyWord,follow,p){//获取信息
			var self=this;
			$.ajax({
				   type: "GET",
				   url: "/index/grab_getGoodsItems",
				   dataType: "json",
				   data:{"keyWord":keyWord},
				   cache:false,
				   success:function(data){
					   follow.find("ul").html("");
					   if(data.items!=null){
						 
							  $.each(data.items,function(i,item){
								  follow.find("ul").append("<li>"+item+"</li>");
								  if(!self.obvious){
									  	follow.show();
									  	self.obvious=true;
								  } 	
							  });
					   }else{
						   follow.find("ul").html("");
						   follow.hide();
						  	self.obvious=false;
					   }
				   }
			});
		},
		mouseHandler:function(){
			var self=this;
			var itemLi=this.follow.find("ul").find("li");
			  itemLi.live("click",function(){
				  self.boss.val($(this).html());
				  if(self.obvious){
					  self.follow.hide();
					  self.obvious=false;
				  }
			  });
			  itemLi.live("mouseover",function(){
				  	$(this).addClass("currentLi");
				  	$(this).siblings().removeClass("currentLi");
			  });
			  itemLi.live("mouseout",function(){
				  	$(this).removeClass("currentLi");
				  	//$(this).siblings().removeClass("currentLi");
			  });
			  this.boss.blur(function(){
				//  alert("dddd");
					setTimeout(function(){
						self.follow.hide();
						self.obvious=false;
					},1000);
				})
		},
		keyHandler:function(event){//键盘事件
			//alert("key");
			var lis=this.follow.find("ul").children("li");
			var len=lis.length;
			if(len>0){//如果有类似选项
				if(event.keyCode==40){// 向下
						this.index++;
						if(this.index>len){
							this.index=0;
						}else if(this.index==len){
							this.boss.val(this.primiVal);
							lis.eq(this.index-1).removeClass("currentLi");
							return;
						}
						this.alterCss(lis.eq(this.index));
						if(!this.obvious){
							this.follow.show();
							this.obvious=true;
						}
				}else if(event.keyCode==38){//向上
						this.index--;
						if(this.index<-1){
							this.index=len-1;
						}else if(this.index==-1){
							this.boss.val(this.primiVal);
							lis.eq(this.index+1).removeClass("currentLi");
							return;
						}
						this.alterCss(lis.eq(this.index));
						if(!this.obvious){
							this.follow.show();
							this.obvious=true;
						}
				}
				else if(event.keyCode==13){
					this.follow.hide();
					this.obvious=false;
					this.index=-1;
				}else{
					this.index=-1;
				}
			}
		},
		alterCss:function(li1){
			li1.addClass("currentLi");
			this.boss.val(li1.html());
			li1.siblings().removeClass("currentLi");
		},
		showFollw:function(obj){
			obj.show();
			this.obvious=true;
		},
		hideFollow:function(obj){}
}

function initFollowMe(p){
	//alert($(site).offset().left);
	//site.addClass("noban");
	var follow=p.parent().siblings("#follow");
	//alert(follow.html());
	follow.css("left",p.position().left+"px")
				.css("top",p.position().top+p.height()+6+"px")
				.css("width",p.width()+3+"px")
				.show();
}
function grabGoodsItems(keyWord,follow,p){
	$.ajax({
		   type: "GET",
		   url: "/index/grab_getGoodsItems",
		   dataType: "json",
		   data:{"keyWord":keyWord},
		   cache:false,
		   success:function(data){
			   follow.find("ul").html("");
			  $.each(data.items,function(i,item){
				  
				  follow.find("ul").append("<li>"+item+"</li>");
				  follow.css("left",p.position().left+"px")
					.css("top",p.position().top+p.height()+6+"px")
					.css("width",p.width()+3+"px");
				  follow.show();
			  });
		   }
	});
}






var foldSwitch={
		flag:0,
		init:function(obj){
	alert("init");
			if(foldSwitch.flag==0){
				
				$(obj).click(function(){
					alert("fold");
				});
			}else{
				obj.click(foldSwitch.unfold);
			}
		},
		fold:function(obj){
			obj.siblings(".alter").show();
			foldSwitch.flag=1;
		},
		unfold:function(obj){
			obj.siblings(".alter").hidden();
			foldSwitch.flag=0;
		}
}