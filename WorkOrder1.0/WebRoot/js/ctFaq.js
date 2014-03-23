$(function(){
	findFaq.init();
});


var findFaq={
		currentPage:0,
		pageSize:0,
		totalPage:0,
		defaultUrl:"",
		defaultParam:{},
		init:function(){
					//findFaq.oneDo();
					findFaq.bind();
				},
		oneDo:function(){
					this.defaultUrl="/faq/getFaqByKeyword";
					this.defaultParam="&keyWord="+$("#name").val();
					findFaq.getData();
				},
		getData:function(){
							$.ajax({
										url:findFaq.defaultUrl,
										cache:false,
										data:"cp="+findFaq.currentPage+findFaq.defaultParam,
										type:"get",
										dataType: "json",
										success:function(data){
										if(data){
											$("#ctfaqlist > .hiscont").remove();
											findFaq.currentPage=data.currentPage;
											findFaq.pageSize=data.pageSize;
											findFaq.totalPage=data.totalPage;
											
											findFaq.makePage();
											$.each(data.faqs,function(i,n){
												findFaq.makeHtml(n);
											});
											
										}else{
											dialog.Alert("");
										}
										setTimeout(function(){
											dialog.close();
										},2000);
									}
								});
				},
		makeHtml:function(faq){
					var f="";
							f+=(faq.type=='通知'?"<div class='hiscont' style='border:1px red dash;color:red;'>":"<div class='hiscont'>")+
								"<div class='hiscont1'>"+faq.gameName+"</div>"+
								(faq.type=='通知'?"<div class='hiscont1' style='color:red;'>":"<div class='hiscont1'>")+faq.type+"</div>"+
								"<div class='hiscont4' title='"+faq.title+"' t='"+faq.descrip+"'>"+faq.title.substring(0,12)+"</div>"+
								"<div class='hiscont3'>"+new Date(faq.createTime.time).pattern("yyyy-MM-dd hh:mm:ss")+"</div>"+
								"<div class=clear></div>"+
								"</div>";
							
					$("#ctfaqlist").append(f).show();
				},
		makePage:function(){
					var ddr = $(".faq").find(".buttonselect .select40");
					ddr.html("");
					for(i=1; i<=this.totalPage;i++)
					{
						if (i==findFaq.currentPage){
							$("<option value='"+i+"' selected=\"selected\">"+i+"</option>").appendTo(ddr);
							}
						else
							$("<option value='"+i+"'>"+i+"</option>").appendTo(ddr);
					}
					
					$(".faq").find("#tp").html(findFaq.totalPage);
					$(".faq").find("#ts").html(findFaq.pageSize);	
				},
		bind:function(){
			var self=this;
			var faq=$(".faq");
			var btnSend=faq.find("#send");
			var first=faq.find(" .buttonfirst");
			var end=faq.find(".buttonend");
			var prev=faq.find(".buttonfront");
			var next=faq.find(".buttonnext");
			var ddr = faq.find(".buttonselect .select40");
			var descrip=faq.find(".hiscont4");
			var quickAcc=faq.find("#q1");
			var quickGame=faq.find("#q2");
			var quickGoods=faq.find("#q3");
			var quickSuggest=faq.find("#q4");
			var quickOther=faq.find("#q5");
			var quickNotice=faq.find("#q6");
			var quickRecharge=faq.find("#q7");
			var quickRoleData=faq.find("#q8");
			var quickServer=faq.find("#q9");
			var quickKnowlege=faq.find("#q10");
			var quickBUG=faq.find("#q11");
			
			quickAcc.live("click",function(){
				//self=$(this);
				findFaq.quickDo($(this),"Acount")
			});
			quickGame.live("click",function(){
				findFaq.quickDo($(this),"GAMEABNOMAL")
			});
			quickGoods.live("click",function(){
				findFaq.quickDo($(this),"GoodsLost")
			});
			quickSuggest.live("click",function(){
				findFaq.quickDo($(this),"SUGESTTION")
			});
			quickOther.live("click",function(){
				findFaq.quickDo($(this),"MORE")
			});
			quickNotice.live("click",function(){
				findFaq.quickDo($(this),"NOTICE")
			});
			quickRecharge.live("click",function(){
				findFaq.quickDo($(this),"RECHARGEISSUE")
			});
			quickRoleData.live("click",function(){
				findFaq.quickDo($(this),"ROLEDATA")
			});
			quickServer.live("click",function(){
				findFaq.quickDo($(this),"SERVERISSUE")
			});
			quickKnowlege.live("click",function(){
				findFaq.quickDo($(this),"GAMEKNOWLEDGE")
			});
			quickBUG.live("click",function(){
				findFaq.quickDo($(this),"GAMEBUG")
			});
			
			
			
			ddr.livequery("change",function(){
				findFaq.currentPage=$(this).val();
				findFaq.getData();
			});
			first.live("click",function(){
				findFaq.currentPage=1;
				findFaq.getData();
			});
			end.live("click",function(){
				findFaq.currentPage=findFaq.totalPage;
				findFaq.getData();
			});
			prev.live("click",function(){
				findFaq.currentPage-=1;
				if(findFaq.currentPage<1){
					findFaq.currentPage=1;
				}
				findFaq.getData();
			});
			next.live("click",function(){
				self.currentPage+=1;
				self.defaultParam=self.defaultParam;
				if(self.currentPage>self.totalPage){
					self.currentPage=self.totalPage
				}
				self.getData();
			});
			descrip.live("click",function(){
				$("#showDescrip").remove();
				var c=$(this).attr("t");
				$("body").append("<div id='showDescrip'><div id='closeDescrip'><a href='javascript:void(0)'><<<返回</a></div>"+c+"</div>");
				var t=faq.offset().top;
				var l=faq.offset().left;
				var h=faq.height();
				
				var d=$("#showDescrip");
				d.css("top",t+120+"px").css("left",l+"px").css("background-color","#ffffff").css("width",(faq.width()-12)+"px")
					.css("height","auto").hide().slideDown("slow");
				$("#closeDescrip").live("click",function(){
					$("#showDescrip").slideUp("slow");
				});
			});
			
			btnSend.live("click",function(){
				var name=$("#name").val();
				name=encodeURI(name);
				name=encodeURI(name);
				self.currentPage=0;
				self.defaultUrl="/faq/getFaqByKeyword";
				self.defaultParam="&keyWord="+name;
				self.getData();
			});
			
			$("#name").live("keypress",function(e){//回车事件
				if(e.keyCode==13){
					var name=$("#name").val();
					name=encodeURI(name);
					name=encodeURI(name);
					self.currentPage=0;
					self.defaultUrl="/faq/getFaqByKeyword";
					self.defaultParam="&keyWord="+name;
					self.getData();
				}
			});
		},
		quickDo:function(s,itype){
			this.currentPage=0;
			this.defaultUrl="/faq/getFaqByType";
			this.defaultParam="&itype="+itype;
			this.getData();
			s.addClass("jd-ahover");
			s.siblings().removeClass("jd-ahover");
		}
};

$(function(){
	//$("body").append("<div id='faq_wall'></div>");
	//$("#faq_wall").append("<div id='faq_wall_tog'>FAQ</br>WALL</div>");
	faqWall.init();
	faqWall.nav();
	faqWall.bind();
	//faqWall.data();
});

////////////////////////////////////////////////
//                                            //
//                FAQ PUB WALL                //
//                                            //
////////////////////////////////////////////////

(function( win , doc ){
	var faqWall={
		BOSS:null,TOG:null,HIDDEN:true,CONTENT:null,BOBY:null,SET:[],ITEMS:null,FOOT:null,
		CP:1,ITYPE:'NOTICE',PAGE_SIZE:0,TOTAL_PAGE:0,
		POSSET:[],URL:"/faq/getFaqByType",PARAM:"&itype=",NODATA:null,
		init:function(){
			this.BOBY = $("body");
			if( !doc.getElementById('faq_wall')){
				this.BOSS = newElement("div","#faq_wall",{padding:"6px 0 0 6px"});
				var togg=newElement("div","#faq_wall_tog",{background:"none"}),
				    togName = newElement("div","#tog_name",
				    					{fontSize:"18px",
				    					 background:"#f99",
				    					 padding:"5px",
				    					 marginBottom:"6px",
				    					 cursor:"pointer"},"FAQ WALL");
				    togg.appendChild(togName);
				conn=newElement("div","#faq_wall_con"),
				items = newElement("div","#items"),
				
				//foot = newElement("div","#foot");
				//var footPre = newElement("span","#pre",null,"上一页"),
				///	footNex = newElement("span","#nex",null,"下一页");
				//foot.appendChild(footPre);
				//foot.appendChild(footNex);
				//togg.style.width=40+"px";
				this.TOG = $( togg );
				this.CONTENT = $( conn );
				this.ITEMS = $( items );
				//this.FOOT = $( foot );
				var head = newElement("div","#head",{background:"#9cc",height:"54px"},"123");
				$(head).html("<div id='seek_box'>"+
					"<input id='seek_inp' type='text' name='descrip'/>"+
					"<input  id='seek_send' type='button'  value='搜一下'/></div>");
				
				this.CONTENT.append(head).append(items);//.append(foot);
				var boss = $(this.BOSS),tog = this.TOG, con = this.CONTENT;
				boss.append( tog ).append(con);
				doc.getElementsByTagName("body")[0].appendChild( this.BOSS );
			}
				
				var boby = this.BOBY,
				    l = boby.width();
				
				$(this.BOSS).css({left:boby.width()-50,top:100});
			
		},
		/**
		 * 添加导航栏
		 */
		nav:function(){
			var quickKeySet = [['通知','NOTICE'],['BUG','GAMEBUG'],['上一页','pre','page'],['下一页','next','page']],i=0,elem;
				//searchBox = $("#faq-search-box");
				
				for( ; i < quickKeySet.length; i++){
					elem = newElement("div", ".faq_nav", 
							{ fontSize:"16px",
						      display:"none",
						      padding:"7px 0 0 5px",
						      background:"#9cc",
						      borderBottom:"1px dashed #000",
						      cursor:"pointer"}, quickKeySet[ i ][0]);
				    elem['itype'] = quickKeySet[ i ][1];
					if(quickKeySet[ i ].length > 2){
						elem.className = 'faq_page';
						elem['dire'] = quickKeySet[ i ][1];
					}
					this.TOG.append( elem );
				}
				elem = null;
		},
		/**
		 * 添加主题内容
		 */
		item:function(){
			var i = 0, elem, me = this, subElem,
				set = this.SET;
			
			//this.itemsClear();
			
			$.each( set, function(i, n){
				elem = newElement("div",".item",{
						background:"#fff",
					 	height:"60px",
					 	position:"absolute",
					 	top:60,
					 	overflow:"hidden"
					 });
				me.ITEMS.append( me.wrapItem(elem, n) );
			});
			
			this.restore();
		},
		wrapItem:function(elem,faq){
			//alert(faq.reps);
			var htm = "",i = 1,
				detail = "<span><a style='color:#BC8F8F;' href='/faq/single?id="+faq.id+"' target='blank'>详情<a></span>";
			htm += "<div class='wrap'>"+
			//"<div>"+faq.id+"</div>"+
			"<div class='d1'>" +
				"<span style='color:#0096FF;'>["+faq.type+"]</span>&nbsp;" +
					""+faq.title.substring(0,12)+"&nbsp;"+
			new Date(faq.createTime.time).pattern("yyyy-MM-dd hh:mm:ss")+"</div>"+
			"<div class='d2'>" +
				"<span><span class='k' style='color:#ccc;'>当前状态：<span style='color:red;'>"+ faq.status +"</span>&nbsp&nbsp;</span><span class='v'></span></span>" +
				"<span><a style='color:#ccc;' href='/faq/toEditPage?id="+faq.id+"' target='blank'>编辑&nbsp;&nbsp;<a></span>" +
				"<span><a style='color:#ccc;' href='/faq/toReplyPage?id="+faq.id+"' target='blank'>回复&nbsp;&nbsp;<a></span>" +
				"<span><a style='color:#666;' href='/faq/single?id="+faq.id+"' target='blank'>详情<a></span>" +
			"</div>"+
			"<div class='d3'>&nbsp;&nbsp;&nbsp;&nbsp;"+((faq.descrip.length > 300)?faq.descrip.substring(0,300)+"..."+detail:faq.descrip) +"</div>"+
			"<div class='d4'>";
			
			for( ;i <= faq.answers.length; i++){
				var ans = faq.answers[i-1];
				htm +="<div class='rep'>" +
						"<p><span id='ind'>"+ i +"</span><span>" + ans.desc + "</span></p>" +
						"<p class='pfoot'><span style='color:#BC8F8F;margin-right:10px;'>"+ ans.guy +"</span><span style='color:#BC8F8F;'>" + new Date(ans.cdate.time).pattern("yyyy-MM-dd hh:mm:ss") + "</span></p>" +
						"</div>";
			}
			htm += "</div></div>";
			elem.innerHTML = htm;
			return elem;
		},
		/**
		 * items 恢复原状
		 */
		restore:function(){
			var i = 0;
			for( ; i < 10; i++){
				this.POSSET.push([ i*61+60, 60 ]);
			}
			//alert(this.POSSET.length);
			this.itemShow();
		},
		itemShow:function(){
			//alert("itemShow");
			var i = 0,posSet = this.POSSET;
			    items = this.CONTENT.find(".item");
			//alert(items.length);
			for( ; i < items.length; i++){
				$(items[ i ]).animate({top:posSet[i][0],height:posSet[i][1]});
			}
		},
		/**
		 * 请求主题内容
		 */
		data:function(){
			var me = this;
			me.SET = [];
			$.ajax({
				url:this.URL,
				cache:false,
				data:"cp="+this.CP + this.PARAM,
				type:"get",
				dataType: "json",
				success:function(data){
				if(!doc.getElementById("nodata")){
					me.NODATA =$("<div id='nodata' style='height:60px;line-height:60px;margin-top:6px;background-color:#fff;font-size:25px;'></div>");
					//me.ITEMS.append(me.NODATA);
				}//else{
					//me.NODATA.html("查无此人");
				//}
				if(data && data.faqs.length>0){
					me.TOTAL_PAGE = data.totalPage;
					if(!document.getElementById("nodata")){
						me.NODATA.html("加载中，请稍后...");
						me.ITEMS.append(me.NODATA);
					}else{
						me.NODATA.html("加载中，请稍后...");
					}
					me.itemsClear();
					var timer = setTimeout(function(){
						var faqs = data.faqs;
						//alert(faqs.length);
						for(var i = 0; i < faqs.length; i++){
							me.SET.push( faqs[i] );
						}
						me.item();
						me.itemShow();
						clearTimeout(timer);
					},1000);
					//me.NODATA.remove();
				}else{
					me.itemsClear();
					//dialog.Alert("查无此人");
					if(!document.getElementById("nodata")){
						me.NODATA.html("查无此人");
						me.ITEMS.append(me.NODATA);
					}else{
						me.NODATA.html("查无此人");
					}
					me.CP = 1;
					me.PAGE_SIZE = 0;
				}
			}
		});
		},
		navShow:function(){
			var tog = this.TOG,i = 0, t;
				navs = tog.find(".faq_nav");
				navs.slideDown();
				
				navs = tog.find(".faq_page");
				navs.slideDown();
				
				faqWall.item();
//			navs.css({display:"block",top:0});
//			for( ; i < navs.length; i++){
//				t = navs[i]['pos']['t'];
//				alert(t);
//				$(navs[i]).animate({top:t},1000);
//			}
		},
		navHide:function(){
			var tog = this.TOG,
				navs = tog.find(".faq_nav");
			navs.slideUp();
			navs = tog.find(".faq_page");
			navs.slideUp();
			this.itemsClear();
		},
		itemsClear:function(){
			var items = this.ITEMS.find(".item"), i = 0;
			if(items.length > 0){
				
				for( ; i < items.length; i++){
					$(items[i]).animate({top:60},600,'',function(){
						this.parentNode.removeChild( this );
//						if(i == items.length-1){
//							me.ITEMS.html("<div style='height:60px;line-height:60px;margin-top:6px;background-color:#fff;font-size:25px;'>查无此人</div>");
//						}
					});
				}
//				var timer = setTimeout(function(){
//					for( ; i < items.length; i++){
//						items[i].parentNode.removeChild( items[i] );
//					}
//					clearTimeout(timer);
//				},2000)
			}
		},
		bind:function(){
			var boss = $( this.BOSS ),tog = this.TOG.find("#tog_name"), boby = this.BOBY,
			    me=this,l = boby.width();
			/**
			 * 导航栏状态
			 */
			tog.click(function(){
				if(me.HIDDEN){
					if(doc.all){
						boss.animate({left:boby.width()-boss.width()},function(){
							me.navShow();
						});
					}else{
						boss.animate({left:boby.width()-boss.width()},function(){
							me.navShow();
						});
					}
					me.HIDDEN = false;
				}else{
					if(doc.all){
						boss.animate({left:boby.width()-50},function(){
							me.navHide();
						});
					}else{
						boss.animate({left:boby.width()-50},function(){
							me.navHide();
						});
					}
					me.HIDDEN = true;
				}
			});
			/**
			 * 
			 */
			
			var items = this.CONTENT.find(".item .d1"), i = 0;
			items.live("click",function(){
				itms = me.CONTENT.find(".item .d1"), i = 0;
				me.POSSET=[];
				var mark = false,top;
				if(!this.open){
					this['open'] = true;
				for( ; i < 10; i++){
					//alert(this .innerHTML+"<>"+items[ i ][0]);
					//alert(items[ i ] .innerHTML);
					if(this !== itms[ i ]){
						if( mark ){
							top = i * 31 + 60 +320;
							//mark = false;
						}else{
							top = i * 31 + 60;
						}
						me.POSSET.push([ top, 30 ]);
					}else{
						me.POSSET.push([ i * 31 + 60, 350 ]);
						mark = true;
					}
				}
				me.itemShow();
				}else{
					this['open'] = false;
					me.restore();
				}
			});
			/**
			 * 导航栏快捷键
			 */
			var navs = $(".faq_nav");
			navs.live("click",function(){
				//alert(this.itype);
				me.URL = "/faq/getFaqByType";
				me.CP = 1;
				me.PARAM ="&itype=" + this.itype;
				//me.PARAM = "cp=1&itype="+this.itype;
				me.data();
				
			});
			/**
			 * 翻页
			 */
			var navs = $(".faq_page");
			navs.live("click",function(){
				//alert(me.CP +":pageSize"+me.PAGE_SIZE);
				if(this.dire === 'next'){
					me.CP ++;
					
					if( me.CP > me.TOTAL_PAGE ){
						dialog.Alert("少年，没有下一页了！");
						me.CP --;
						return;
					}
				}
				if(this.dire === 'pre'){
					me.CP --;
					if( me.CP < 1 ){
						dialog.Alert("少年，没有上一页了！");
						me.CP ++;
						return;
					}
				}
				
				me.data();
				
			});
			/**
			 * 搜索框
			 */
			var seekSend = $("#seek_send");
			seekSend.live("click",function(){
				var kw = $("#seek_inp").val();
				//alert(kw);
				if(isEmpty(kw)){
					dialog.Alert("少年，写点什么！");
					return;
				}
				kw=encodeURI(kw);
				kw=encodeURI(kw);
				me.CP = 1;
				me.URL = "/faq/getFaqByKeyword";
				me.PARAM = "&keyWord=" + kw;
				me.data();
			});
			
			$(window).scroll(function(){
				var pY=$(window).scrollTop()+80;
				boss.css("top",pY+"px")
			});
			$(window).resize(function(){
				me.init();
			});
		}
	}
	
	win.faqWall = faqWall;
	
})(window,document);
/**
 * 创建一个新元素
 * @param attrObj
 * @return
 */
function newElement( tag, identy, attrObj,text ){
	var elem=document.createElement( tag ),
		quickExpr =/^#([\w-]+)$|^\.([\w-]+)$/,
		match = quickExpr.exec( identy );
	if( match && match[ 1 ]){
		elem['id'] = match[ 1 ];
	}
	if( match && match[ 2 ]){
		elem['className'] = match[ 2 ];
	}
	if(attrObj){
		extend( elem.style , attrObj );
	}
	if(text){
		var txt = document.createTextNode( text );
		elem.appendChild( txt );
	}
	return elem;
}
/**
 * 向一个对象添加属性
 * @return
 */
function extend(){

	var target = arguments[0] || {},i = 1,len=arguments.length,deep = false,options;

	if(typeof target === 'boolean'){
		deep = target;
		target = arguments[1];
		i = 2;
	}
	if(len == i){
		target=this;
		--i;
	}
	for(;i < len;i++){
		
		if( (options = arguments[i]) != null){
			for(var name in options){
				var copy = options[ name ],src = target[ name ];
				if ( copy === src )
				continue;
			//----------------------------------------------
			//
			// the attribute may be exsit
			//
			//----------------------------------------------
				if(deep && typeof copy === 'object'){
					if(src){
						extend(deep,src,copy);
					}else{
						target[ name ] = DQuery.extend(deep,(copy.length?[]:{}),copy);
					}
				}else{
					target[ name ] = copy;
				}
			}
		}
		return target;
	}
}

//检查文本域输入长度[不得少于3个小，且高于300字]
function checkLimit(memo,limitMin,limitMax){
	//$("#memoErr").text("");
	if(!isEmpty(memo)){
		if(memo.length < limitMin||memo.length > limitMax){
			//$("#memoErr").text("*内容不得少于" +limitMin +"个字且最多可输入" + limitMax + "个字");
			return false;
		}
	}
	
	return true;
}


function isEmpty(s){
	return s.replace(/(^\s*)|(\s*$)/g, '').length <= 0;
}


function isAllNumber(obj,err){
	var numReg=/^[0-9]{0,10}$/;
	var _val=obj.val();
	if(!isEmpty(_val)){
		err.html("");
		if(!numReg.test(_val)){//验证其输入的格式
			err.html("请输入数字");
			return false;
		}
		return true;
	}
	return true;
}






/**
 * 文本框剩余字数统计
 * @return
 */
function leftCountOfTextArea(){
	var ta=$(".formtextareacont").find("textarea");
	ta.keydown(function(){
		countWord($(this))
	});
	ta.keyup(function(){
		countWord($(this))
	});
	ta.blur(function(){
			countWord($(this))
	});
}
function countWord(ta){
	var curLength=ta.val().length;
    if(curLength>=300){
        var num=ta.val().substr(0,300);
       ta.val(num);
       dialog.Alert("请不要超过字数限制~");
       setTimeout(dialog.close, 3000);
       $("#textAreaLeftCount").text(0)
    }
    else{
        $("#textAreaLeftCount").text(300-ta.val().length)
    }
}