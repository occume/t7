
$(function(){
	var ox,oy,positionX,positionY,isMove=false;
			var holder=$("#FB_dialog");
		
			holder.find(".pobg1").live("mousedown",function(ev){
            	   isMove=true;
            	   var self=$("#FB_dialog");
            	   self.find(".bgcorner1").css("height","30px");
            	   ox=ev.pageX;
            	   oy=ev.pageY;
            	   positionX=self.offset().left;
            	   positionY=self.offset().top;
            	   self.css("top",positionY-10+"px");
//            	   $(window.frames["FB_dialog_iframe"].document).live("mousemove",function(ev){
//            		   move();
//            	   });
			})
			holder.live("mousemove",function(ev){
					move(ev);
			})
			holder.live("mouseup",function(ev){
				stop(ev);
			})
			$(document).mousemove(function(ev){
				move(ev);
			});	
			$(document).mouseup(function(ev){
				stop(ev);
			})
			function move(ev){
				var self=$("#FB_dialog");
				if(isMove){
					var x=ev.pageX-ox+positionX;
					var y=ev.pageY-oy+positionY;
						self.css("top",y-10+"px").css("left",x+"px").css("cursor","pointer");
				}
			}
			function stop(ev){
				isMove=false;
				var obj=$("#FB_dialog");
				var cY=ev.pageY;
				obj.find(".bgcorner1").css("height","5px");
				//obj.css("top",cY+"px");
			}
})

/***处理按钮固定位置***/
$(function(){
	var buttonBox=$(".edittools");
	buttonBox.css("position","absolute");
	
	function replace(){
		var pY=$(window).height()+$(window).scrollTop()-60;
		
		var pX=100;
		$(".edittools").css("position","absolute").css("z-index","999");
		$(".edittools").css("top",pY+"px");
	}
	$(window).resize(function(){
		replace();
	});
	$(window).scroll(function(){
		replace();
	});
	replace();
})

$(function(){
	var btn_topic=$(".cllc").find(".topic");
	btn_topic.next().slideUp();
	
	
	var now=$(btn_topic[0]);
	$.each(btn_topic,function(i,n){
		if(btn_topic.length<0)return;
		$(n).children("span").first().css({"color":"#fff",
			"font-family":"Georgia",
			"font-size":"24px",
			"font-weight":"bold"});
		$(n).click(function(){
			var next=$(this).next(".content");
			
			if(next.css("display")=="none")
				next.slideDown();
			else
				next.slideUp();
			lead.init($(this));
			lead.moveTo($(this));
			now=$(this);
		});
	})
	lead.init($(btn_topic[0]));
	$(window).resize(function(){
		lead.init(now);
		lead.moveTo(now);
	});
	
	//$(".cllc").append("<span id='openAll'>展开</span>");
	var openAll=0;
	$("#openAll").css({"padding-left":650,"font-size":15,"cursor":"pointer"})
		.click(function(){
			if(openAll==0){
				btn_topic.next().slideDown();
				$(this).html("关闭");
				openAll=1;
			}else{
				btn_topic.next().slideUp();
				$(this).html("展开");
				openAll=0;
			}
		});
	
	imgShow.init();
})
var imgShow={
	Oper:null,
	imgSet:[],
	newImgSet:[],
	init:function(){
		
		if(!document.getElementById("imgShowBox")){
			var boby=document.getElementsByTagName("body")[0],
				newElem=this.newElement('div',{id:'imgShowBox'});
				newElem.style.position='absolute';
				//newElem.style.top='-1000px';
				//newElem.style.display='none';
			this.Oper=newElem;
			boby.appendChild(newElem);
		}
		//var picBox=this.getElementsByClassName("picture-box", "div")[0];
		//var pics=this.getElementsByClassName("picture-item", "div", picBox);
		var pics=document.images;
		this.imgSet=pics;
		this.run();
	},
	run:function(){
		//alert(this.Oper)
		var imgs=this.imgSet,tempEle,the=this,theBox=this.Oper;
		var pY=(document.body.scrollTop||document.documentElement.scrollTop)+100;
		//alert(document.body.scrollTop);
		//for()
		for(var k in imgs){
			
			(function(oldImg){
				oldImg.onclick=function(){
					var ofset=$(this).offset();
					//alert(oldImg.ownerDocument);
					if(the.newImgSet.length>0){
						//theBox.removeChild(theBox.childNodes[0]);
						var newImg=the.newImgSet.pop();
						newImg.style.zIndex=10;
							$(newImg).animate({top:newImg.min.t,left:newImg.min.l,height:newImg.min.h,width:newImg.min.w},300,function(){
								document.body.removeChild(this);
							});
					}
					//
					tempEle=the.newElement("img", {"src":oldImg.src});
					document.body.appendChild(tempEle);
					//alert(this.offsetWidth);
					tempEle['min']={w:this.offsetWidth,h:this.offsetHeight,t:ofset.top,l:ofset.left}
					//tempEle.style.zIndex=1;
					tempEle.onclick=function(){
						the.newImgSet.pop();
						$(this).animate({top:this.min.t,left:this.min.l,height:this.min.h,width:this.min.w},1000,function(){
							document.body.removeChild(this);
						});
					}
					the.newImgSet.push(tempEle);
					oldImg['max']={w:tempEle.offsetWidth,h:tempEle.offsetHeight};
					
					$(tempEle).css({width:this.offsetWidth,height:this.offsetHeight,position:"absolute"});
					document.body.appendChild(tempEle);
					//alert(tempEle.offsetWidth);
					//
					
					$(tempEle).css({top:ofset.top,left:ofset.left});
					pY=(document.body.scrollTop||document.documentElement.scrollTop)+100
					$(tempEle).animate({top:pY,width:oldImg.max.w,height:oldImg.max.h},500);
					return false;
				}
				
			})(imgs[k])
			
			
		}
	},
	newElement:function(tagName,attrObj){
		var newElem=document.createElement(tagName);
		for(var attr in attrObj){
			if(attrObj.hasOwnProperty(attr)){
				newElem[attr]=attrObj[attr];
			}
		}
		return newElem;
	},
	getElementsByClassName:function(className,tag,elm){
		tag=tag||'*';
		elm=elm||document;
		var classes = className.split(" "),
		classesToCheck = [],
		elements = (tag === "*" && elm.all)? elm.all : elm.getElementsByTagName(tag),
		current,
		returnElements = [],
		match;
		for(var k=0, kl=classes.length; k<kl; k+=1){
			classesToCheck.push(new RegExp("(^|\\s)" + classes[k] + "(\\s|$)"));
		}
		for(var l=0, ll=elements.length; l<ll; l+=1){
			current = elements[l];
			match = false;
			for(var m=0, ml=classesToCheck.length; m<ml; m+=1){
				match = classesToCheck[m].test(current.className);
				if (!match) {
					break;
				}
			}
			if (match) {
				returnElements.push(current);
			}
		}
		return returnElements;
	}
}
/**
 * 处理页面可爱圈圈
 */
var lead={
		cx:0,
		cy:0,
		ltop:0,
		lleft:0,
		paper:null,
		leading:null,
		text:null,
		init:function(obj){
			if(!obj.html()) return;
										if(!document.getElementById("holder"))
											$("body").append("<div id='holder' style='position:absolute;width:30px;height:"+obj.parent().height()+"px;'></div>");
										if(!+[1,]){
											this.ltop=obj.parent().offset().top+8;
											this.lleft=obj.parent().offset().left+13;
										}
										else{
											this.ltop=obj.parent().offset().top-1;
											this.lleft=obj.parent().offset().left+13;
										}
											$("#holder").css("top",this.ltop+"px").css("left",this.lleft+"px").css("height",obj.parent().height()+"px");	
										//alert(obj.offset().top);
										if(!this.paper){
											var h=obj.outerHeight()/2;
											this.paper = Raphael("holder", 42, obj.parent().height());
											this.leading=this.paper.circle(20,(!+[1,])? h-8:h, 18);
											this.leading.attr({fill: "none", stroke: "#ef5b9c","stroke-width":"3", opacity: .8});
											//this.text=this.paper.text(20,20,"1").attr({font: '28px "Georgia", Arial' ,fill: "#fff", stroke: "#fff"});
										}else{
											this.paper.setSize(42, obj.parent().height());
										}
								},
		moveTo:function(obj){
									//this.cy=obj.offset().top;
									 var x = 99;
							         var yy = 95;
							         var yy=0;
							         var rand = parseInt(Math.random() * (x - yy + 1) + yy);
							         var manner="";
									var offTop=obj.offset().top;
//							         var nodes=obj.parent().find(".content").filter(function(){
//							        	 return $(this).css("display")!="none";
//							         });
//							         if(nodes.length==1){
//							        	 offTop=$(nodes[0]).offset().top;
//							         }
							        // alert(nodes.length);
									var y=offTop-obj.parent().offset().top;
									
									if(this.cy-y>0){
										manner="backIn";
									}else{
										manner="bounce"
									}
									var strHsb="hsb(."+offTop/rand+", .75, .75)";//变色
									this.leading.animate({cy: y+20,stroke: strHsb}, 1000, manner);
									this.cy=y;
								},
		reset:function(){
									
								}
}
