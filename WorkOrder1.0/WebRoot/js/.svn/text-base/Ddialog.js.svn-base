(function($){
	$.extend({
		alert:function(elem,AutoClose){
			if(arguments.length>1)
				this.AutoClose=!!AutoClose;
			//this.occupScreen()
			this.makeBox(elem);
			
		},
		confirm:function(){
			
		},
		makeBox:function(elem){
				
				var cons=this.CONS;
				//when no params
				if(typeof elem === cons.UNDEFINED)
					elem=cons.DEFAULT("");
				else{
					//when DOCDlement
					if(elem.nodeType){
						elem=$(elem);
					}
					//when strings and numbers
					if(typeof elem === cons.STRING){
						var match = cons.quickExpr.exec( elem );
						if ( match && match[1] ) {
							elem=$(elem)
						}else{
							elem=cons.DEFAULT(elem);
						}
					//when others
					}else{
						elem=cons.DEFAULT(elem);
					}
				}
				if(!document.getElementById(cons.BOX_ID))
					$(cons.BOX_HTML).appendTo($(cons.BODY));
				
				var dialogBox=$(cons.$BOX_ID);
				var userBox=dialogBox.find(cons.USER_CONTENT_ID);
				dialogBox.css({opacity:0});
				userBox.html(cons.EMPTY);
				elem.appendTo(userBox);

				this.DBox=dialogBox;
				this.moveBox();
				
			},
		moveBox:function(){
				var cons=this.CONS;
				var box=this.DBox;
				var bWidth=$(window).width();
				var iWidth=box.width();
				
				//when a set of lick,do the last
				$.dequeue(box[0],"fx");
				
				box.animate({left:(bWidth-iWidth)/2-100,top:200},100);
				//box.animate({top:200},100);
				box.animate({left:(bWidth-iWidth)/2,opacity:1},300);
				
				if(this.AutoClose){
					setTimeout(this.closeBox,2000);
				}else{
					if(!document.getElementById(cons.BOX_HEAD_ID))
						$(cons.BOX_HEAD_HTML).prependTo(box);
					var boxHead=$(cons.$BOX_HEAD_ID);
					//this.envelopBox();
					this.bind();
				}
				
			},
		envelopBox:function(){
				
				var cons=$.CONS,
					box=$.DBox,
					Doffset=box.offset(),
					Dbody=$(cons.BODY),
					defaultLeft=cons.LEFT_WIDTH;
					 
				var Dl=Doffset.left,Dt=Doffset.top,Dw=box.width(),Dh=box.height();
				if(!document.getElementById(cons.RO_TOP_ID)){
					var ROTOP=$(cons.RO_TOP_HTML).appendTo(Dbody);
					ROTOP.css({top:-100,left:Dl-defaultLeft,width:Dw+2*defaultLeft+6,opacity:0});
					var ROLEFT=$(cons.RO_LEFT_HTML).appendTo(Dbody);
					ROLEFT.css({left:-100,top:Dt,height:Dh+9,opacity:0});
					var RORIGHT=$(cons.RO_RIGHT_HTML).appendTo(Dbody);
					RORIGHT.css({left:1600,top:Dt,height:Dh+9,opacity:0});
					var ROBOTTOM=$(cons.RO_BOTTOM_HTML).appendTo(Dbody);
					ROBOTTOM.css({top:-100,left:Dl-defaultLeft,width:Dw+2*defaultLeft+6,opacity:0});
				}
				var roTop=$(cons.$RO_TOP_ID),
					roLeft=$(cons.$RO_LEFT_ID),
					roRight=$(cons.$RO_RIGHT_ID),
					roBottom=$(cons.$RO_BOTTOM_ID);
				//
				roTop.animate({top:Dt-130,left:Dl-defaultLeft},100);
				roTop.animate({top:Dt-cons.LEFT_WIDTH,left:Dl-defaultLeft,opacity:0.6},200);
				roLeft.animate({left:Dl-110,top:Dt},100);
				roLeft.animate({left:Dl-cons.LEFT_WIDTH,top:Dt,opacity:0.6},200);
				roRight.animate({left:Dl+Dw+6+100,top:Dt},100);
				roRight.animate({left:Dl+Dw+6,top:Dt,opacity:0.6},200);
				roBottom.animate({top:Dt+Dh+6+100,left:Dl-defaultLeft},100);
				roBottom.animate({top:Dt+Dh+6,left:Dl-defaultLeft,opacity:0.6},200);
				$.dequeue(roTop[0],"fx");
				$.dequeue(roLeft[0],"fx");
				$.dequeue(roRight[0],"fx");
				$.dequeue(roBottom[0],"fx");
			},
		developBox:function(){
				var cons=this.CONS,
					box=this.DBox,
					Doffset=box.offset(),
					Dl=Doffset.left,Dt=Doffset.top,Dw=box.width(),Dh=box.height();
				if(document.getElementById(cons.RO_TOP_ID)){
					var roTop=$(cons.$RO_TOP_ID),
						roLeft=$(cons.$RO_LEFT_ID),
						roRight=$(cons.$RO_RIGHT_ID),
						roBottom=$(cons.$RO_BOTTOM_ID);
					roTop.animate({top:Dt-130,opacity:0},200);
					//roTop.animate({top:-300},100);
					roLeft.animate({left:Dl-110,opacity:0},200);
					//roLeft.animate({left:-110},100);
					roRight.animate({left:Dl+Dw+100,opacity:0},200);
					//roRight.animate({left:1600,},200);
					roBottom.animate({top:Dt+Dh+6+100,opacity:0},200);
					//roBottom.animate({top:-300},100);
				
				}
			},
		rePosition:function(){
				var cons=this.CONS;
				var box=this.DBox,
					win=$(window),
					
					winW=win.width(),docH=win.height(),
					boxL=box.offset().left,boxT=box.offset().top,
					boxW=box.width(),boxH=box.height();
					//alert(win.width());
				if(boxT<0)box.animate({top:cons.LEFT_WIDTH},200);
				if(boxL<0)box.animate({left:cons.LEFT_WIDTH},200);
				if(boxL>winW-boxW)box.animate({left:winW-boxW-cons.LEFT_WIDTH*2},200);
				setTimeout($.envelopBox,200);
			},
		bind:function(){
				$.dragBox();
				var cons=this.CONS;
				var box=this.DBox;
				var boxHead=$(cons.$BOX_HEAD_ID)
					
				box.hover(function(event){
						//if($.headAni){
							boxHead.slideDown(100);
							//$.dequeue(boxHead[0],"fx");
							setTimeout(function(){
								$.envelopBox();
							},200);
						//}
					
					},function(event){
						//event=event||window.event;
						//var src=event.relatedTarget||event.toElement;
						//if(src.getAttribute("class")!='surround')
						if($.headAni){
							boxHead.slideUp(100);
							setTimeout(function(){
								$.developBox();
							},200);
						}
						
						//$.dequeue(boxHead[0],"fx");
					});
					
				var closeDiv=$(cons.$CLOSE_ICON_ID);
				closeDiv.live("click",function(){
					$(this).css({display:"none"});
					$.developBox();
					$.closeBox();
					$.occupScreen();
					return false;
				});
			},
		closeBox:function(){
				var db=$($.CONS.$BOX_ID);
				db.animate({top:db.offset().top-100,opacity:0},300);
				db.animate({top:-1000,left:-1000},100);
			},
		occupScreen:function(){
				var cons=this.CONS;
				if(!document.getElementById(cons.THE_SCREEN)){
					$(cons.THE_SCREEN_HTML).appendTo(cons.BODY);
				}
				var theScreen=$(cons.$THE_SCREEN);
				theScreen.slideToggle(300); 
			},
		CONS:{
				LEFT_WIDTH:8,
				STRING:"string",NUMBER:"number",BODY:"body",EMPTY:"",UNDEFINED:"undefined",
				BOX_HTML:"<div id='dialogBox'><div id='userContent'></div></div>",
				BOX_HEAD_HTML:"<div id='boxHead'>&nbsp;<div id='tran'><div id='closeIcon'></div></div></div>",
				RO_TOP_HTML:"<div id='roTop' class='surround'></div>",RO_RIGHT_HTML:"<div id='roRight'></div>",
				RO_BOTTOM_HTML:"<div id='roBottom'></div>",RO_LEFT_HTML:"<div id='roLeft'></div>",
				THE_SCREEN_HTML:"<div id='theScreen'></div>",
				BOX_ID:"dialogBox",$BOX_ID:"#dialogBox",
				BOX_HEAD_ID:"boxHead",$BOX_HEAD_ID:"#boxHead",
				RO_TOP_ID:"roTop",$RO_TOP_ID:"#roTop",$RO_LEFT_ID:"#roLeft",$RO_RIGHT_ID:"#roRight",$RO_BOTTOM_ID:"#roBottom",
				$TRAN_ID:"#tran",$CLOSE_ICON_ID:"#closeIcon",
				$THE_SCREEN:"#theScreen",THE_SCREEN:"theScreen",
				USER_CONTENT_ID:"#userContent",
				quickExpr:/^[^<]*(<(.|\s)+>)[^>]*$|^#([\w-]+)$/,
				DEFAULT:function(elem){
							return $("<p>"+elem+"</p>");
						}
			},
		dragBox:function(){
				var cons=this.CONS;
				var ox,oy,positionX,positionY,isMove=false;
				var holder=$(cons.$BOX_HEAD_ID);
					
				holder.live("mousedown",function(ev){
					$.developBox();
						$.headAni=false;
			            isMove=true;
			            var self=$(cons.$BOX_ID);
			            	
			            ox=ev.pageX;
			            oy=ev.pageY;
			            positionX=self.offset().left;
			            positionY=self.offset().top;
			            	 
				});

						holder.live("mousemove",function(ev){
							move(ev);
						})
						holder.live("mouseup",function(ev){
							stop(ev);
							$.envelopBox();
						})
						$(document).mousemove(function(ev){
							move(ev);
						});	
						$(document).mouseup(function(ev){
							stop(ev);
						})
						function move(ev){
							var self=$(cons.$BOX_ID);
							if(isMove){
								var x=ev.pageX-ox+positionX;
								var y=ev.pageY-oy+positionY;
									self.css("top",y+"px").css("left",x+"px");
							}
							
						}
						function stop(ev){
							var cons=this.CONS;
							var box=$.DBox,
							win=$(window),
					
							winW=win.width(),docH=win.height(),
							boxL=box.offset().left,boxT=box.offset().top,
							boxW=box.width(),boxH=box.height();
					//alert(win.width());
							if(boxT<0||boxL<0||boxL>winW-boxW)
								//超出边界重定位
								//$.rePosition();
							
							$.headAni=true;
							isMove=false;
						}
					},
		
		DBox:null,
		AutoClose:true,
		headAni:true
	});
})(jQuery)



