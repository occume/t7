// JavaScript Document
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%24 == 0 ? 24 : this.getHours()%24, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "\u65e5",         
    "1" : "\u4e00",         
    "2" : "\u4e8c",         
    "3" : "\u4e09",         
    "4" : "\u56db",         
    "5" : "\u4e94",         
    "6" : "\u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}       


//绑定时间
function BindSelectAjax(obj,url,pid){
	var p = pid==null?0:pid;
	var pek=$(obj).attr("name");
	if ($("option",obj).size()<=1){//！！！阻止下拉单内容重复加载
		$(obj).attr("disabled","disabled");
		$(obj).html("<option value=''>正在加载中...</option>");
		$.ajax({
			   type: "GET",
			   url: url,
			   dataType: "json",
			   data:{"pid":p},
			   cache:false,
			   success:function(data){
					$(obj).html("");
					$(obj).append($("<option value='0'>-请选择-</option>"));
					$(data.confList).each(function(index) {
						if(pek=="source"||pek=="states"){
							$("<option value='"+this.name+"'>"+this.name+"</option>").appendTo($(obj));
						}else{
							$("<option value='"+this.id+"'>"+this.name+"</option>").appendTo($(obj));
						}
						  });
					$(obj).attr("disabled","");
				   }
			   });
	}
}

/*工单全部查询事件处理类*/
var gdcx={
	color_1:0,
	color_2:0,
	colors:['#cc0000','#cc6666','cc9999','#ffcc99','#ffffcc','#ffff33','#ccff99','#99cc66','#669933','336600'],
	currentPage:1,
	pagesize :0,
	totalPage:0,
	totalCount:0,
	queryType:0,
	firstqueryUrl:"/json/query_getGlobalNoPull",
	queryUrl:"/json/query_searchWorkOrder",
	xlsUrl:"/json/XLSDownload",
	init:function(){
		gdcx.BindList();
		gdcx.MouseHandler();
		gdcx.addPageHandler();
		},
	BindList:function(){
		if (gdcx.queryType==0)
		{
			//gdcx.BindFirstListData();
		}else
		{
			gdcx.BindListData();
			}
		},	
	BindFirstListData:function(){
		var self=this;
		gdcx.clearList();
		$.ajax({
			 type: 'POST',  
			 url: gdcx.firstqueryUrl,
			 dataType: "json",
			 cache:false,
			 success:function(result){
			
				 gdcx.pagesize=result.pageSize;
				 gdcx.totalPage=result.totalPage;
				 gdcx.totalCount=result.totalCount;
				 $.each(result.wokeOrderList,function(i){gdcx.appendRow(this);});
				 gdcx.bulidPage();
				 }
			   });
		},
	BindListData:function(){
		
		//var self=this;
		//var re={};
//				alert(re);
//		if (re.wokeOrderList==null)
//			alert(re);
		$.ajax({
		  type: 'POST',
		  url: gdcx.queryUrl,
		  data: $("#gdcxform").serialize()+"&currentPage="+gdcx.currentPage,
		  dataType: "json",
		  cache:false,
		  success: function(result){
			  gdcx.clearList();
			  if(result.preURL!=null){
				  alert("您还未登录或者登录已过期，请重新登录~");
				  window.location.href= result.preURL
			  }else{
				  if (result.wokeOrderList.length==0)
				  {
					  	gdcx.pagesize=30;
						gdcx.totalPage=0;
						gdcx.bulidPage();
					  	dialog.Alert("没有查询到结果！");
					  	setTimeout(dialog.close,2000);
					  }else{
						gdcx.pagesize=result.pageSize;
						gdcx.totalPage=result.totalPage;
						gdcx.totalCount=result.totalCount;
						$.each(result.wokeOrderList,function(i){gdcx.appendRow(this);});
						gdcx.bulidPage();
				  }
			  }
		 		}
		});
		},
	appendRow:function(workOrderList){
		var sourcename = (workOrderList.source == null)?"":workOrderList.source;
		//alert(workOrderList.comment);
		var  evaluation = (workOrderList.evaluation == null)?"":workOrderList.evaluation;
		var as="";
		if(workOrderList.states=="处理中"){
			var assign=(workOrderList.assign==null)?"":((workOrderList.assign.statu==null)?"":workOrderList.assign.statu);
			if(assign=="ASSIGNING"){
				assign="(派单中)"
			}else if(assign=="ASSIGNED"){
				assign="(派单回复)"
			}else{
				assign="";
			}
			as=assign;
		}
		//var worker=(workOrderList.worker.name == null)?"":workOrderList.worker.name;
		var html='\
			<div class="alllistbox" style="cursor:pointer;" title="'+workOrderList.userInfo.memo+'"  onclick="javascript:gdcx.openUrl('+workOrderList.id+',this);">\
			<div class="boxcont1">'+workOrderList.id+'</div>\
			<div class="boxcont1">'+workOrderList.getWorker.accName+'</div>\
			<div class="boxcont2">'+workOrderList.source+'</div>\
			<div class="orangetext">'+workOrderList.urgency+'</div>\
			<div class="boxcont4">'+workOrderList.userInfo.accountname+'</div>\
			<div class="boxcont5">'+workOrderList.userInfo.game.name+'</div>\
			<div class="boxcont6">'+workOrderList.userInfo.lvlOne.name+'</div>\
			<div class="boxcont7">'+(workOrderList.worker==null?"":workOrderList.worker.nickName)+'</div>\
			<div class="boxcont8">'+new Date(workOrderList.createTime.time).pattern("yyyy-MM-dd hh:mm:ss")+'</div>\
			<div class="boxcont9">'+workOrderList.userInfo.server.name+'</div>\
			<div class="redtext">'+workOrderList.states+as+'</div>\
			<div class="redtext">'+workOrderList.comment+'</div>\
			<div class="clear" ></div>\
			</div>';
		$("#gdgclist").append(html);
		},
		openUrl:function(id,obj){
			if(gdcx.color_1==10) gdcx.color_1=0;
			//location.href="/json/query_toSingleWorkOrder?workOrderId="+id;
			$(obj).css("background-color",gdcx.colors[gdcx.color_1++]);
			//$(obj).siblings().css("background-color","");
			window.open("/json/query_lookOrOperateWorkOrder?workOrderId="+id, "newwindow");
			
			return false;
		},	
	bulidPage:function(){
		var pagelist =$("#gdgclistpage");
		var ddr = pagelist.find(".buttonselect .select40");
		ddr.html("");
		for(i=1; i<=this.totalPage;i++)
		{
			if (i==gdcx.currentPage){
				$("<option value='"+i+"' selected=\"selected\">"+i+"</option>").appendTo(ddr);
				}
			else
				$("<option value='"+i+"'>"+i+"</option>").appendTo(ddr);
		}
		
		pagelist.find("#tp").html(gdcx.totalPage);
		pagelist.find("#ts").html(gdcx.pagesize);	
		pagelist.find("#tc").html(gdcx.totalCount);
		},
	addPageHandler:function(){
		var self=this;
		var pagelist =$("#gdgclistpage")
		var first = pagelist.find(".buttonfirst>a");
		var front = pagelist.find(".buttonfront>a");
		var next = pagelist.find(".buttonnext>a");
		var last = pagelist.find(".buttonend>a");
		var btnquery=$("#btn_query");
		var btnclear=$("#btn_clear");
		var btnxls=$("#btn_xls");
		
		btnxls.click(function(){//导出xls
			
			 $("#gdcxform").submit();
		});
		
		var ddr = pagelist.find(".select40");
		/*jd*/var inputs=$(".checkleft").find("input");
					var selects=$(".checkleft").find("select");
		//alert(selects.length);
					
		inputs.keypress(function(e){//回车事件
			e = e || window.event;
			if(e.keyCode==13){
				if(STR.isEmpty(this.value)) return false;
				gdcx.currentPage=1;
			   gdcx.queryType=1;
			   gdcx.BindList();
			  
			}else{
				return true;
			}
			return false;
		});
		selects.keypress(function(e){//回车事件
			e = e || window.event;
			if(e.keyCode==13){
			
				gdcx.currentPage=1;
			   gdcx.queryType=1;
			   gdcx.BindList();
			  
			}
			return false;
		});
		
		first.live("click",gdcx.firstpage);
		front.live("click",gdcx.prevpage);
		next.live("click",gdcx.nextpage);
		last.live("click",gdcx.lastpage);
		ddr.livequery("change",function(){
			//alert("change");
			gdcx.changepage(this);
		});
		btnquery.live("click",function(){
									   gdcx.currentPage=1;
									   gdcx.queryType=1;
									   gdcx.BindList();
									   });
		btnclear.live("click",function(){
		//	inputs.value("");
			$.each(inputs,function(i,n){
				$(n).val("");
				btnxls.val("导出EXCEL");
			});
			$.each(selects,function(j,m){
//				alert($(m).val());
				//alert(m.getElementsByTagName("option")[0].value());
				m.getElementsByTagName("option")[0].selected="true";
			});
		});
		},
	changepage:function(obj){
		gdcx.currentPage=$(obj).val();
		gdcx.BindList();
		},
	firstpage:function(){
		gdcx.currentPage=1;
		gdcx.BindList();
		},
	nextpage:function(){
		gdcx.currentPage+=1;
		if (gdcx.currentPage>gdcx.totalPage)
			gdcx.currentPage=gdcx.totalPage;
			
		gdcx.BindList();
		},
	prevpage:function(){
		gdcx.currentPage-=1;
		if (gdcx.currentPage<1)
			gdcx.currentPage=1;
			
		gdcx.BindList();
		},
	lastpage:function(){
		gdcx.currentPage=gdcx.totalPage;
		gdcx.BindList();
		},
	MouseHandler:function(){
		$("#gdgclist .alllistbox").live('mouseover',function(){$(this).addClass("alllistboxselect")});
		$("#gdgclist .alllistbox").live('mouseout',function(){$(this).removeClass("alllistboxselect")});	
		},
	clearList:function(){
		$("#gdgclist .alllistbox").remove();
		}
}
//拉单处理类
var ladan={
	url:"/json/query_pullWorkOrder",
	firstqueryUrl:"/json/query_getGlobalNoPull",
	init:function(){
		$("#btn_ladan").live("click",function(){
			ladan.act(this);
			ladan.showUndeal();
		});
		ladan.showUndeal();
		},
	act:function(obj){
		$(obj).attr("disabled","disabled");
			$.ajax({
			 type: 'POST',  
			 url: ladan.url,
			 dataType:"json",
			 cache:false,
			 success:function(result){
				if(result.preURL!=null){
					dialog.Alert("您还未登录或者登录已过期，请重新登录~");
				  	setTimeout(dialog.close,3000);
					window.location.href= result.preURL
				}else{
					dialog.Alert(result.message);
				
				  	setTimeout(dialog.close,3000);
					$(obj).attr("disabled","");	
					window.location.href= window.location.href;
				}	
				 }
			   });
		},
		showUndeal:function(){
			$.ajax({
				 type: 'POST',  
				 url: ladan.firstqueryUrl,
				 dataType: "json",
				 cache:false,
				 success:function(result){
				//alert(result.message);
						$(".showUndeal").html(result.message);
					 }
				   });
		},
		fastEnterKey:function(){
			
		}
	}


//荣誉排行处理类
var ryph={
	querytodayUrl:"/json/query_getRangeJosonOfToday",
	querymonthUrl:"/json/query_getRangeJosonOfThisMonth",
	init:function(){
	$(document.body).append($('<div id="followMe"></div>'));
	
		ryph.loadtodayData();
		//window.setInterval(function(){ryph.loadtodayData();},1000*180);
		ryph.loadMonthData();
		},
	loadtodayData:function(){
		$.ajax({
			 type: 'GET',  
			 url: ryph.querytodayUrl,
			 dataType:"json",
			 cache:false,
			 success:function(result){
			//alert(result);
				 ryph.clear("#todayPh");
				 $("#todayPh").append("<div class=\"top1\">当天排名：</div>");
				 $.each(result,function(i){
					// alert(this.groupName);
					// alert(this.groupName !=  '无名');
					 if(this.groupName != '客服组长'){
						 if(this.groupName != '无名') {
							 ryph.bulidhtml("#todayPh",this,i);}
					 }
				 	}	
				 );
				 	 ryph.showDetail(result,"#todayPh");
				 }
			   });

		},
		loadMonthData:function(){
			$.ajax({
				 type: 'GET',
				 url: ryph.querymonthUrl,
				 dataType:"json",
				 cache:false,
				 success:function(result){
				//alert(result);
					 ryph.clear("#monthPh");
					 $("#monthPh").append("<div class=\"top1\">当月排名：</div>");
					 $.each(result,function(i){
						 if(this.groupName != '客服组长'){
							 if(this.groupName != '无名') {
							 ryph.bulidhtml("#monthPh",this,i);
							 }
					 		}	
					 	}	 
					 );
					 	ryph.showDetail(result,"#monthPh");
					 }
				   });

			},
	bulidhtml:function(obj,data,i){
		$(obj).append("<div class='top"+(i+2)+" charts' cg='"+data.countGet+"' cr='"+data.countReply+"'>"+data.groupName+"</div>");
		},
	clear:function(id){
			$(id).html("");
			},
	showDetail:function(data,e){
		var fm=$("#followMe");
		fm.css({opacity:0.9});
		$(".charts").each(function(i,n){ 
			$(n).mouseenter(function(e){
				var me=$(this),tempHtml=me.html(),os=me.offset(),
					t=os.top-10,l=os.left,w=me.width(),h=me.height();
				//
				fm.html("");
				
				fm.append("<p>"+me.html()+"</p>");
				fm.append("<p>拉单："+me.attr("cg")+"&nbsp;回复："+me.attr("cr")+"</p>");
				fm.animate({left:l-15,top:t,width:w},800,'backout');
				$.dequeue(fm[0],"fx");
				
			})
			$(n).mouseleave(function(){
				//dosth
			})
		});
	}
	}





//我组工单广场
var mygdcx={
	currentPage:1,
	pagesize :0,
	totalPage:0,
	totalCount:0,
	queryType:0,
	queryUrl:"/json/query_getWorkOrderByGroup",
	queryByPFUrl:"/json/query_getWorkOrderByPF",
	queryAllUrl:"/json/query_getCurrentInfo",
	BatchTypeUrl:"/index/cache_getBatchTypeList",
	BatchTypeData:null,
	init:function(){
		//mygdcx.BindList(); //MODIFY @ 2011.09.28
		mygdcx.showCurrentInfo();
		mygdcx.MouseHandler();
		mygdcx.addPageHandler();
		},
	BindList:function(){
			mygdcx.BindListData();
		},
	BindListData:function(){
		$.ajax({
		  type: 'GET',
		  url: mygdcx.queryUrl,
		  data: "currentPage="+mygdcx.currentPage,
		  dataType: "json",
		  cache:false,
		  success: function(result){
			  mygdcx.clearList();
			  if(result.preURL!=null){
				  dialog.Alert("您还未登录或者登录已过期，请重新登录~");
				  setTimeout(dialog.close,3000);
				  window.location.href= result.preURL;
			  }else{
				  if (result.wokeOrderList.length==0)
				  {
					  	mygdcx.pagesize=10;
						mygdcx.totalPage=0;
						mygdcx.bulidPage();
						dialog.Alert("没有您查询的结果~");
					  	setTimeout(dialog.close,2000);
					  	//mygdcx.bulidPage();
					  }else{
						mygdcx.pagesize=result.pageSize;
						mygdcx.totalPage=result.totalPage;
						mygdcx.totalCount=result.totalCount;
						$.each(result.wokeOrderList,function(i){mygdcx.appendRow(this);});
						mygdcx.bulidPage();
						mygdcx.doLock(result.wokeOrderList);
				  }
			  }
		 		}
		});
		},
	openUrl:function(id,obj){
			//location.href="/json/query_toSingleWorkOrder?workOrderId="+id;
			var od=$(obj).siblings(".boxcont1").html();
			
			if(od.indexOf('锁定中')!=-1){
				alert("锁定中");
			}else{
				var islock=mygdcx.isLock(od);
				if(islock){
					alert("锁定中");
				}else{
					//$(obj).parent().css("background-color","#FFFFC4");
					
					if(gdcx.color_2==10) gdcx.color_2=0;
					//location.href="/json/query_toSingleWorkOrder?workOrderId="+id;
					$(obj).parent().css("background-color",gdcx.colors[gdcx.color_2++]);
					
					$.each($(obj).parent().siblings(),function(i,n){
						 var s=$(n);
						 if(s.find(".boxcont1").html().indexOf('锁定中')==-1){
							// $(obj).parent().siblings().css("background-color","");
						 }
					})
					window.open("/json/query_toSingleWorkOrder?workOrderId="+id, "newwindow1");
					return false;
				}
			}
		},	
	showCurrentInfo:function(){//
			$.ajax({
				  type: 'GET',
				  url: mygdcx.queryAllUrl,
				  data: "currentPage="+mygdcx.currentPage,
				  dataType: "json",
				  cache:false,
				  success: function(result){
					 	$(".showDealing").html(result.countResult.countDealing);
					 	$(".showLackInfo").html(result.countResult.countLackInfo);
					 	$(".showAssigning").html(result.countResult.countAssigning);
					 	$(".showAssigned").html(result.countResult.countAssigned);
					 	$(".showDealed").html(result.countResult.countDealed);
					 	$(".showNoAssign").html(result.countResult.countNoAssign);
				 		}
			});
	},
	appendRow:function(workOrderList){
		var as="";
		var insertImg="";
		if(workOrderList.states=="处理中"){
			var assign=(workOrderList.assign==null)?"":((workOrderList.assign.statu==null)?"":workOrderList.assign.statu);
			if(assign=="ASSIGNING"){
				assign="(派单中)"
			}else if(assign=="ASSIGNED"){
				assign="(派单回复)"
				if(workOrderList.innerMark==1){
					insertImg="<img src='/images/innerMark.png'>";
				}
			}else{
				assign="";
			}
			as=assign;
		}
		var html='<div title="'+workOrderList.userInfo.memo+'" class="mylistlight">\
					<div class="boxcont1" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.id+'</div>\
					<div class="boxcont2" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.states+as+'</div>\
					<div class="boxcont3" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.source+insertImg+'</div>\
					<div class="redtext" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.urgency+'</div>\
					<div class="boxcont4" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.userInfo.accountname+'</div>\
					<div class="boxcont5" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.userInfo.lvlOne.name+'</div>\
					<div class="boxcont6" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+new Date(workOrderList.createTime.time).pattern("yyyy-MM-dd hh:mm:ss")+'</div>\
					<div class="boxcont7" style="cursor:pointer;" onclick="javascript:mygdcx.openUrl('+workOrderList.id+',this);">'+workOrderList.userInfo.server.name+'</div>\
					<div class="boxcont9">'+(workOrderList.getTime==null?"":new Date(workOrderList.getTime.time).pattern("yyyy-MM-dd hh:mm:ss"))+'</div>\
					<div class="boxcont10">\
					<input type="hidden" name="id" value="'+workOrderList.id+'" />\
					<select name="ddrBatchType" class="select128" ><option value="">请选择</option></select>\
					</div>\
					<div class="boxcont2"><input type="checkbox" name="batchDo"/></div>\
					<div class="clear" ></div>\
					</div>';
	
		$("#mygdcxlist").append(html);
		},
	bulidPage:function(){
		var pagelist =$("#mygdgclistpage");
		var ddr = pagelist.find(".buttonselect .select40");
		ddr.html("");
		for(i=1; i<=this.totalPage;i++)
		{
			if (i==mygdcx.currentPage){
				$("<option value='"+i+"' selected=\"selected\">"+i+"</option>").appendTo(ddr);
				}
			else
				$("<option value='"+i+"'>"+i+"</option>").appendTo(ddr);
		}
		
		pagelist.find("#tp").html(mygdcx.totalPage);
		pagelist.find("#ts").html(mygdcx.pagesize);	
		pagelist.find("#tc").html(mygdcx.totalCount);	
		},
	addPageHandler:function(){
		var self=this;
		var pagelist =$("#mygdgclistpage")
		var first = pagelist.find(".buttonfirst>a");
		var front = pagelist.find(".buttonfront>a");
		var next = pagelist.find(".buttonnext>a");
		var last = pagelist.find(".buttonend>a");
		
		var ddr = pagelist.find(".buttonselect .select40");
		first.live("click",mygdcx.firstpage);
		front.live("click",mygdcx.prevpage);
		next.live("click",mygdcx.nextpage);
		last.live("click",mygdcx.lastpage);
		ddr.livequery("change",function(){mygdcx.changepage(this);});
		$(".toolsbutton a[name='btnclick']").live("click",function(){
				mygdcx.queryUrl=$(this).attr("ajaxUrl");
				mygdcx.currentPage=1;
				mygdcx.BindList();
			
					$(this).addClass("jd-ahover");
					var sibs=$(this).parent().siblings(".sib");
					//alert(sibs.length)
					for(var i=0;i<sibs.length;i++){
						$(sibs[i]).find("a").removeClass("jd-ahover");
					}
					
					var crrentTab=$(".currentTab");
					crrentTab.html($(this).html());
				
		});

		},
		isLock:function(id){
			var locked=false;
			$.ajax({
				  type: 'GET',
				  async:false,
				  url: "/lock/isLocked",
				  data: {"id":id},
				  dataType: "json",
				  cache:false,
				  success: function(data){
					  	if(data.message){
					  		if(data.message=="LOCKING"){
					  			locked= true;
					  		}
					  	}
					}
		});
			return locked;
		},
	doLock:function(workOrders){
				var ids="";
				$.each(workOrders,function(i,n){
						ids+=n.id+",";
				});
				$.ajax({
						  type: 'GET',
						  url: "/lock/woIdInJCS",
						  data: {"ids":ids},
						  dataType: "json",
						  cache:false,
						  success: function(data){
							  var rows=$(".mylistlight");
								if(data){
										$.each(rows,function(i,n){
												var nn=$(n);
												var r=nn.find(".boxcont1");
												$.each(data.idList,function(i,n){
													if(r.html()==n){
														r.html("锁定中"+r.html());
														nn.css("background-color","#f3f3f3");
													}
												})
										});
								}
							}
				});
				
		},
	changepage:function(obj){
		mygdcx.currentPage=$(obj).val();
		mygdcx.BindList();
		},
	firstpage:function(){
		mygdcx.currentPage=1;
		mygdcx.BindList();
		},
	nextpage:function(){
		mygdcx.currentPage+=1;
		if (mygdcx.currentPage>mygdcx.totalPage)
			mygdcx.currentPage=mygdcx.totalPage;
			
		mygdcx.BindList();
		},
	prevpage:function(){
		mygdcx.currentPage-=1;
		if (mygdcx.currentPage<1)
			mygdcx.currentPage=1;
			
		mygdcx.BindList();
		},
	lastpage:function(){
		mygdcx.currentPage=mygdcx.totalPage;
		mygdcx.BindList();
		},
	MouseHandler:function(){
		$("#mygdcxlist .mylistlight").live('mouseover',function(){$(this).addClass("mylistselect")});
		$("#mygdcxlist .mylistlight").live('mouseout',function(){$(this).removeClass("mylistselect")});	
		$("#mygdcxlist .mylistlight select[name='ddrBatchType']").live('click',function(){mygdcx.ddrBatchTypeHandler(this);});
		//$("#btn_plhf").live("click",mygdcx.btn_plhfclick);
		},
	ddrBatchTypeHandler:function(obj){
		if ($("option",obj).size()<=1)//如果select未加载过数据
		{
			if (mygdcx.BatchTypeData==null)//如果select DATA未加载异步加载
			{
				$.getJSON(mygdcx.BatchTypeUrl,{ cacheName: "batchTypeCache"},function(data){
							mygdcx.BatchTypeData=data;
							mygdcx.appendddrBatchType(obj);
					});
			}else//直接绑定
			{
				mygdcx.appendddrBatchType(obj);
			}
		}
		},	
	appendddrBatchType:function(obj){
		//$("<option value=''>请选择</option>").appendTo($(obj));
			$(mygdcx.BatchTypeData).each(function(index) {
    				$("<option value='"+this.batchType+"'>"+this.batchTypeName+"</option>").appendTo($(obj));
					  });
		},
	getSelectBatchType:function(){
		var idurl="";
		var typeurl="";
		$("#mygdcxlist .mylistlight").each(function(i){
			var id=$("input[name='id']",this).val();
			var type=$("select[name='ddrBatchType']",this).val();
			if (type!=null && type!="")
			{
				if (idurl=="")
				{
					idurl = "id="+id;
					typeurl="btList="+encodeURIComponent(type);
				}else
				{
					idurl = idurl+"&id="+id;
					typeurl=typeurl+"&btList="+encodeURIComponent(type);
				}
			}
		});
		if (idurl=="")
		{
			alert("没有可提交的数据");
			return false;
			}
		window.location.href= "/index/cache_putDataCache?cacheName=batchDataCache&"+idurl+"&"+typeurl;
		},	
	btn_plhfclick:function(){mygdcx.getSelectBatchType();},	
	clearList:function(){
		$("#mygdcxlist .mylistlight").remove();
		}
}


//近期工单处理记录
var recentmygdcx={
	currentPage:1,
	pagesize :10,
	totalPage:0,
	queryUrl:"/json/query_getWorkOrderNearNow",
	init:function(){
		recentmygdcx.BindList();
		//recentmygdcx.MouseHandler();
		recentmygdcx.addPageHandler();
		},
		BindList:function(){
		//	alert($("#accName").val());
		$.ajax({
		  type: 'GET',
		  url: recentmygdcx.queryUrl,
		  data: "currentPage="+recentmygdcx.currentPage+"&accountname="+$("#accName").val(),
		  dataType: "json",
		  cache:false,
		  success: function(data){
			//alert(data);
			  recentmygdcx.clearList();
			
			  if (data && data.empty)
			  {
				 // alert("您查询的记录不存在");
				  	recentmygdcx.pagesize=10;
					recentmygdcx.totalPage=0;
					recentmygdcx.bulidPage();
					$("#recentmygdcxlist").append("暂无记录");
				  	
				  }else{
					  //alert("success");
					  //alert(result.wokeOrderList);
					recentmygdcx.pagesize=data.pageSize;
					recentmygdcx.totalPage=data.totalPage;
					$.each(data.wokeOrderList,function(){
						//	alert("each");
							recentmygdcx.appendRow(this);
						});
					recentmygdcx.bulidPage();
			  }
		 		}
		});
		},
	appendRow:function(workOrderList){
			//alert("ddd");
		var html='<div class="hiscont" style="cursor:pointer;" onclick="javascript:recentmygdcx.openUrl('+workOrderList.id+');">\
				<div class="hiscont1">'+workOrderList.accName+'</div>\
				<div class="hiscont2">'+workOrderList.issueType+'</div>\
				<div class="hiscont3">'+new Date(workOrderList.operTime.time).pattern("yyyy-MM-dd hh:mm:ss")+'</div>\
				<div class="hiscont4" title="'+workOrderList.reply+'">'+workOrderList.reply.substring(0,10)+'</div>\
				<div class="clear"></div>\
				</div>';
	
		$("#recentmygdcxlist").append(html);
		},
		openUrl:function(id){
			//location.href="/json/query_toSingleWorkOrder?workOrderId="+id;
			window.open("/json/query_lookOrOperateWorkOrder?workOrderId="+id, "newwindow2");
			return false;
		},	
	bulidPage:function(){
		var pagelist =$("#mygdgclistpage");
		var ddr = pagelist.find(".buttonselect .select40");
		ddr.html("");
		for(i=1; i<=this.totalPage;i++)
		{
			if (i==recentmygdcx.currentPage){
				$("<option value='"+i+"' selected=\"selected\">"+i+"</option>").appendTo(ddr);
				}
			else
				$("<option value='"+i+"'>"+i+"</option>").appendTo(ddr);
		}
		
		$("#tp").html(recentmygdcx.totalPage);
		$("#ts").html(recentmygdcx.pagesize);	
		},
	addPageHandler:function(){
		var self=this;
		var pagelist =$("#mygdgclistpage")
		var first = pagelist.find(".buttonfirst>a");
		var front = pagelist.find(".buttonfront>a");
		var next = pagelist.find(".buttonnext>a");
		var last = pagelist.find(".buttonend>a");
		
		var ddr = pagelist.find(".buttonselect .select40");
		first.live("click",recentmygdcx.firstpage);
		front.live("click",recentmygdcx.prevpage);
		next.live("click",recentmygdcx.nextpage);
		last.live("click",recentmygdcx.lastpage);
		ddr.livequery("change",function(){mygdcx.changepage(this);});
		},
	changepage:function(obj){
		recentmygdcx.currentPage=$(obj).val();
		recentmygdcx.BindList();
		},
	firstpage:function(){
		recentmygdcx.currentPage=1;
		recentmygdcx.BindList();
		},
	nextpage:function(){
		recentmygdcx.currentPage+=1;
		if (recentmygdcx.currentPage>recentmygdcx.totalPage)
			recentmygdcx.currentPage=recentmygdcx.totalPage;
			
		recentmygdcx.BindList();
		},
	prevpage:function(){
		recentmygdcx.currentPage-=1;
		if (recentmygdcx.currentPage<1)
			recentmygdcx.currentPage=1;
			
		recentmygdcx.BindList();
		},
	lastpage:function(){
		recentmygdcx.currentPage=recentmygdcx.totalPage;
		recentmygdcx.BindList();
		},
	MouseHandler:function(){
		
		},
	clearList:function(){
		$("#recentmygdcxlist .hiscont").remove();
		}
}

function getEvent(){
	if(document.all){
		return window.event;//如果是ie
	}
	func=getEvent.caller;
	while(func!=null){
		var arg0=func.arguments[0];
		if(arg0){
			if((arg0.constructor==Event || arg0.constructor ==MouseEvent)||
					(typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
				return arg0;
			}
		}
		func=func.caller;
	}
	return null;
}

///页面加载事件
$(function(){

});