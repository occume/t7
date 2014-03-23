/**
 * 输入框自动补全
 * @param boss
 * @return
 */
function matchArrFun(boss){
	this.boss=$(boss);
	this.follow=$("");
	this.keyWord="";
	this.primiVal="";
	this.index=-1;
	this.obvious =false;
	this.Items=[];
}
matchArrFun.prototype={
		init:function(){
	//alert("init");
		this.Items=["@qq.com","@gmail.com","@sina.com"];
			 if (document.getElementById("follow") == null) {
		         $("body").append("<div id='follow'><ul></ul></div>");
		     }
			 this.follow=$("#follow");
			var self=this;
			this.boss.keyup(function(event){
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
					self.buildFollow(self.follow);
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
		buildFollow:function(follow){//获取信息
		//	alert("build");
			 follow.find("ul").html("");
			var pre=this.boss.val();
							  $.each(this.Items,function(i,item){
								  follow.find("ul").append("<li>"+pre+item+"</li>");
								  if(!self.obvious){
									  	follow.show();
									  	self.obvious=true;
								  }
							  })
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
/**
 * 
 * 
*/
var globe=null;
function matchFun(boss){
	this.boss=$(boss);
	this.follow=$("");
	this.keyWord="";
	this.primiVal="";
	this.index=-1;
	this.obvious =false;
}
matchFun.prototype={
		init:function(){
//	alert("init");
//	alert(this.boss.parent().html())
			 if (!document.getElementById("follow")) {
		         $("body").append("<div id='follow'><ul></ul></div>");
		     }
			 this.follow=$("#follow");
			var self=this;
			//var site=$(""+this.boss);
//			var _follow=site.parent().siblings(""+this.follow);
			//alert("before keyup");
			this.boss.keyup(function(event){
				globe=self.boss;
//				alert("keyup");
//				alert(event.keyCode);
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
			var p=this.boss;
			//alert(this.boss.length);
			//alert(p.val());
			var itemLi=this.follow.find("ul").find("li");
			
			itemLi.live("click",function(){
					//alert(p.length);
					//  p.val($(this).html());
					//alert(self.boss.val());
					  globe.val($(this).html());
					  if(self.obvious){
						  self.follow.find("ul").html("");
						  self.follow.hide();
						  self.obvious=false;
					  }
					//return false;
				
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
						 self.follow.find("ul").html("");
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
					 this.follow.find("ul").html("");
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