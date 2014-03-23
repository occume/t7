$(function(){
	var cmServer=null;
	$("#server").find(".zhankai").click(function(){
		$.ajax({
			type: 'POST',  
			 url: "/index/index_addServer",
			 dataType: "text",
			 cache:false,
			 success:function(result){
					if(result!=null){
						alert(result);
					}else{
						alert("请重试~");
					}		
				 }
		});
	});
	var cmGame=null;
	$("#games").find(".zhankai").click(function(){
		var p=$("#games");
		var brothers=p.siblings();
		$.each(brothers,function(){
			var me=$(this);
			me.find("#content").html("");
			me.find(".add").hide(); 
			me.find(".flush").hide();
		});
		p.find(".add").show();
		p.find(".flush").show();
		if(cmGame==null)//单例
			cmGame=new cm($("#games"),"/index/grab_gameJson","/index/grab_editGame","/index/grab_addGame","/index/grab_deleteGame",false,"游戏名称");
		cmGame.unFold();
	});
	var cmReportKind=null;
	$("#reportKind").find(".zhankai").click(function(){
		var p=$("#reportKind");
		var brothers=p.siblings();
		$.each(brothers,function(){
			var me=$(this);
			me.find("#content").html("");
			me.find(".add").hide(); 
			me.find(".flush").hide();
		});
		p.find(".add").show();
		p.find(".flush").show();
		if(cmReportKind==null)//单例
			cmReportKind=new cm($("#reportKind"),"/index/grab_reportKindJson","/index/grab_editReportKind","/index/grab_addReportKind","/index/grab_deleteReportKind",false,"违规类型");
		cmReportKind.unFold();
	});
	var cmRecharge=null;
	$("#recharge").find(".zhankai").click(function(){
		var p=$("#recharge");
		var brothers=p.siblings();
		$.each(brothers,function(){
			var me=$(this);
			me.find("#content").html("");
			me.find(".add").hide(); 
			me.find(".flush").hide();
		});
		p.find(".add").show();
		p.find(".flush").show();
		if(cmRecharge==null)//单例
			cmRecharge=new cm($("#recharge"),"/index/grab_rechargeTypeJson","/index/grab_editRechargeType","/index/grab_addRechargeType","/index/grab_deleteRechargeType",false,"充值类型");
		cmRecharge.unFold();
	});
//	var cmIssue=null;
//	$("#issue").find(".zhankai").click(function(){
//		var p=$("#issue");
//		var brothers=p.siblings();
//		$.each(brothers,function(){
//			var me=$(this);
//			me.find("#content").html("");
//			me.find(".add").hide(); 
//			me.find(".flush").hide();
//		});
//		p.find(".add").show();
//		p.find(".flush").show();
//		if(cmIssue==null)//单例
//			cmIssue=new cm($("#issue"),"/index/grab_issueTypeJson","/index/grab_editIssue","/index/grab_addIssue","/index/grab_deleteIssue",false,"问题类型");
//		cmIssue.unFold();
//	});
	var cmAdvisory=null;
	$("#advisory").find(".zhankai").click(function(){
		var p=$("#advisory");
		var brothers=p.siblings();
		$.each(brothers,function(){
			var me=$(this);
			me.find("#content").html("");
			me.find(".add").hide(); 
			me.find(".flush").hide();
		});
		p.find(".add").show();
		p.find(".flush").show();
		if(cmAdvisory==null)//单例
			cmAdvisory=new cm($("#advisory"),"/index/grab_advisoryTypeJson","/index/grab_editAdvisory","/index/grab_addAdvisory","/index/grab_deleteAdvisory",false,"咨询类型");
		cmAdvisory.unFold();
	});
	var cmBatch=null;
	$("#batch").find(".zhankai").click(function(){
		var p=$("#batch");
		var brothers=p.siblings();
		$.each(brothers,function(){
			var me=$(this);
			me.find("#content").html("");
			me.find(".add").hide(); 
			me.find(".flush").hide();
		});
		p.find(".add").show();
		p.find(".flush").show();
		if(cmBatch==null)//单例
			cmBatch=new cm($("#batch"),"/index/cache_getBatchTypeToJon","/index/cache_putBatchTypeToCache","/index/cache_putBatchTypeToCache","/index/cache_removeBatchTypeCacheDataByKey",false,"批量操作类型");
		cmBatch.unFold();
	});
})
function cm(parent,grabUrl,editUrl,addUrl,deleteUrl,hasSelect,title){
	this.title=title;
	this.grabUrl=grabUrl;
	this.editUrl=editUrl;
	this.addUrl=addUrl;
	this.deleteUrl=deleteUrl;
	this.parent=parent;
	this.hasSelect=hasSelect;
	this.selectDate=null;//缓存读取的下拉单内容
	this.grabData=null;//缓存读取的列表信息
	this.isbind=0;
}
	cm.prototype.unFold=function(){
		//alert(parent);
		var conElem=this.parent.find("#content");
		//alert(conElem.text());
		this.grab(conElem);
	}
	cm.prototype.grab=function(conElem){
		//alert("grab");
		var self=this;
		this.clear(conElem);
		//conElem.html("");
		if(this.grabData==null){
			$.getJSON(self.grabUrl+"?t="+new Date().getTime(),function(data){
				if(data.empty!=null){
					alert(data.empty);
				}else{
				//	alert(data.confList);
					self.grabData=data.confList;
					self.buildHtml(self.grabData,conElem);
				}
			});
			if(this.isbind==0){
				self.bindFun();
				this.isbind=1;
			}
		}
		else{
			//alert("empty");
			self.buildHtml(self.grabData,conElem);
			//self.bindFun();
		}
//		
	}
	cm.prototype.buildHtml=function(data,conElem){
		var self=this;
		$.each(data,function(i,e){
			self.appendHtml(e,conElem);
		});
	}
	cm.prototype.appendHtml=function(jjson,elem){
	//	alert("buildHtml");
	//	this.clear(elem);
		var html='\
			<div class="info-box">\
			<div class="id">'+jjson.id+'</div>\
			<div class="name">'+jjson.name+'</div>\
			<div class="edit">编辑</div>\
			<div class="delete">删除</div>\
		</div>';
	$(elem).append(html).show();
	}
	cm.prototype.bindFun=function(){
		//cm.prototype.bindEditClick=function(selfs,urlll){
	//	alert("bindFun");
		var self=this;
		var edits=this.parent.find(".edit");//绑定编辑按钮事件
		var deletes=this.parent.find(".delete");
		
		var sendBtn=this.parent.find("#sendBtn");
		var saveBtn=this.parent.find("#addBtn");
		var selectBtn=this.parent.find("#selectBtn");
		var flushBtn=this.parent.find(".flush");
		var addBtn=this.parent.find(".add");
		var closeBtn=this.parent.find(".close");
		
		edits.live("click",function(){
			var se=$(this);
			var id=se.siblings(".id").text();
			var name=se.siblings(".name").text();
			self.showEditBox(id,name);
		});
		deletes.live("click",function(){
			var sel=$(this);
			var id=sel.siblings(".id").text();
			self.deleteSth(self.deleteUrl,id);
		});
		sendBtn.live("click",function(){//绑定发送按钮事件
			//alert(selfs.find("#url").val())
			//self.parent.find("#url").val()备用获取URL方法
			self.editSth(self.editUrl);//！！！！！！2011.09.08修改
			$("#utilForm").remove();
		});
		
		selectBtn.live("click",function(){//绑定游戏大区下拉单事件
			//alert("down");
			self.buildSelect(this);
		});
		flushBtn.live("click",function(){
			self.grabData=null;
			self.unFold();
		});
		addBtn.live("click",function(){
			self.showAddBox();
		});
		saveBtn.live("click",function(){
			self.addSth(self.addUrl);
			$("#utilForm").remove();
		});
		closeBtn.live("click",function(){
			$("#utilForm").remove();
		});
		return false;
	}
	/**
	 * 解绑
	 */
	cm.prototype.unBindFun=function(){
	}
	cm.prototype.showEditBox=function(idVal,nameVal){
		var self=this;
//		alert("parent++="+this.parent.html());
//		alert(this.parent.find("#oper"));
		if(self.hasSelect){
			this.parent.find("#oper").html(self.createSelectInput(idVal,nameVal)).show();
		}else{
			this.parent.find("#oper").html(self.createInput(idVal,nameVal)).show();
		}
		return false;
	}
	cm.prototype.createSelectInput=function(idVal,nameVal){
		//alert("createSelectInput");
		var html="";
		html="<div id=\"utilForm\">" +
				"<div id=\"inner\">"+
				"<div id=\"inner-title\">"+this.title+"<span class=\"close\">[关闭]</span>"+
				"</div>"+
				"<div id=\"inner-content\">"+
				"<input type=\"hidden\" id=\"id\" name=\"id\" value="+idVal+">"+
				"名称：<input type=\"text\" id=\"name\" name=\"name\" value="+nameVal+"><br/>"+
				"大区：<select id=\"selectBtn\" name=\"areaId\"></select><br/>"+
				"<input type=\"button\" id=\"sendBtn\" value=\"提交\"/>" +
				"</div>"+
				"</div>"+
				"</div>";
		return html;
	}
	cm.prototype.createInput=function(idVal,nameVal){
		//alert("createInput");
		var html="";
		html="<div id=\"utilForm\">" +
				"<div id=\"inner\">"+
				"<div id=\"inner-title\">"+this.title+"<span class=\"close\">[关闭]</span>"+
				"</div>"+
				"<div id=\"inner-content\">"+
				"<input type=\"hidden\" id=\"id\" name=\"id\" value="+idVal+">"+
				"名称：<input type=\"text\" id=\"name\" name=\"name\" value="+nameVal+"><br/>"+
				"<input type=\"button\" id=\"sendBtn\" value=\"提交\"/>" +
				"</div>"+
				"</div>"+
				"</div>";
		return html;
	}
	cm.prototype.editSth=function(url){
		var self=this;
		//alert("edit!!!");
		//var param=this.parent.find("#util-box");
		var id=this.parent.find("#id").val();
		var name=this.parent.find("#name").val();
		var areaId=this.parent.find("#selectBtn").val();
		//alert(id)
		$.ajax({
			type: 'POST',  
			 url: url,
			 data:this.hasSelect?{"id":id,"name":name,"areaId":areaId}:{"id":id,"name":name},
			 dataType: "text",
			 cache:false,
			 success:function(result){
					if(result!=null){
						alert(result);
					}else{
						alert("请重试~");
					}		
				 }
		});
	}
	cm.prototype.deleteSth=function(url,id){
		var self=this;
		alert("delete!!!");
		$.ajax({
			type: 'POST',  
			 url: url,
			 data:{"id":id},
			 dataType: "text",
			 cache:false,
			 success:function(result){
					if(result!=null){
						alert(result);
					}else{
						alert("请重试~");
					}		
				 }
		});
	}
	/**
	 * 异步读取数据并添加到Select
	 */
	cm.prototype.buildSelect=function(obj){
		var self=this;
		if ($("option",$(obj)).size()<=1)//如果select未加载过数据
		{
			if (self.selectData==null)//如果select DATA未加载异步加载
			{
				$.getJSON("/index/grab_areaJson",function(data){
						self.selectData=data.confList;
						self.appendSelect($(obj));
					});
			}else//直接绑定
			{
				self.appendSelect($(obj));
			}
		}

	}
	cm.prototype.appendSelect=function(obj){
		$(this.selectData).each(function(index) {
			$("<option value='"+this.id+"'>"+this.name+"</option>").appendTo($(obj));
			  });
	}
	cm.prototype.clear=function(element){
		element.html("");
	}
	cm.prototype.showAddBox=function(){
		var self=this;
		if(self.hasSelect){
			this.parent.find("#oper").html(self.createAddSelectInput());
		}else{
			this.parent.find("#oper").html(self.createAddInput()).show();
		}
		return false;
	}
	cm.prototype.createAddSelectInput=function(){
		//alert("createSelectInput");
		var html="";
		html="<div id=\"utilForm\">" +
				"<div id=\"inner\">"+
				"<div id=\"inner-title\">"+this.title+"<span class=\"close\">[关闭]</span>"+
				"</div>"+
				"<div id=\"inner-content\">"+
				"名称：<input type=\"text\" id=\"name\" name=\"name\"><br/>"+
				"大区：<select id=\"selectBtn\" name=\"areaId\"></select><br/>"+
				"<input type=\"button\" id=\"addBtn\" value=\"提交\"/>" +
				"</div>"+
				"</div>"+
				"</div>";
		return html;
	}
	cm.prototype.createAddInput=function(idVal,nameVal){
		//alert("createInput");
		var html="";
				html="<div id=\"utilForm\">" +
				"<div id=\"inner\">"+
				"<div id=\"inner-title\">"+this.title+"<span class=\"close\">[关闭]</span>"+
				"</div>"+
				"<div id=\"inner-content\">"+
				"名称：<input type=\"text\" id=\"name\" name=\"name\"><br/>"+
				"<input type=\"button\" id=\"addBtn\" value=\"提交\"/>" +
				"</div>"+
				"</div>"+
				"</div>";
		return html;
	}
	cm.prototype.addSth=function(url){
		var self=this;
		//alert("add!!!");
		//var param=this.parent.find("#util-box");
		//var id=this.parent.find("#id").val();
		var name=this.parent.find("#name").val();
		var areaId=this.parent.find("#selectBtn").val();
		//alert(id)
		$.ajax({
			type: 'POST',  
			 url: url,
			 data:this.hasSelect?{"name":name,"areaId":areaId}:{"name":name},
			 dataType: "text",
			 cache:false,
			 success:function(result){
					if(result!=null){
						alert(result);
					}else{
						alert("请重试~");
					}		
				 }
		});
	}
	
var operIssue={
		url:"/index/grab_issueTypeJson",
		param:{pid:9,t:new Date().getTime()},
		getIssueList:function(){
	
				$.getJSON(this.url,this.param,function(data){
					if(data.empty!=null){
						var elem=$("<p>暂无数据！</p>");
						operIssue.makeOpBox(elem);
						setTimeout(operIssue.closeOpBox,2000);
					}else{
						operIssue.makeHtml(data.confList);
					}
				});
			},
		makeHtml:function(issues){
				if(!document.getElementById("issuePaper"))
					$("<div id='issuePaper'></div>").appendTo($("body"));
				var paper=$("#issuePaper");
				
				var bWidth=$(window).width();
				var bHeight=$(window).height();
				//var iWidth=issueBox.width();
				
				paper.css({top:0,background:"#ccc"});
				paper.animate({width:bWidth,height:bHeight,opacity:0.7},200);
				//issueBox.animate({top:150},100);
				//issueBox.animate({left:(bWidth-iWidth)/2,opacity:1},200);
				
				if(!document.getElementById("issueBox"))
					$("<div id='issueBox'></div>").appendTo($("body"));
				
				var issueBox=$("#issueBox");
				issueBox.html("");
				$("<div id='head1'>问题类型>>><span id='lastI'>查看上级</span" +
							">>>><span id='add1'>添加大类</span></div><table id='issueTable'></table>").appendTo(issueBox);
				var issueTable=$("#issueTable");
				$("<tr><td>Index</td><td>ParentId</td><td>Name</td><td>Operation</td></tr>")
					.appendTo(issueTable);
				$.each(issues,function(o,n){
					$("<tr>" +
							"<td>"+n.id+"</td>" +
							"<td>"+n.pid+"</td>" +
							"<td class='btnName'>"+n.name+"</td>" +
							"<td>" +(n.pid==9?"<span id='add'>添加</span>":"")+
							"<span id='edi'>编辑</span>" +
								"<span id='del'>删除</span>"+
							"</td></tr>")
					.appendTo(issueTable);
				});
				var bWidth=$(window).width();
				var iWidth=issueBox.width();
				
				issueBox.css({opacity:0});
				issueBox.animate({left:(bWidth-iWidth)/2-150},100);
				issueBox.animate({top:150},100);
				issueBox.animate({left:(bWidth-iWidth)/2,opacity:1},200);
				//issueBox.css({opacity:1});
			},
		makeOpBox:function(elem){
				if(!document.getElementById("opBox"))
					$("<div id='opBox'></div>").appendTo($("body"));
				
				var opBox=$("#opBox");
				opBox.html("");
				
				elem.appendTo(opBox);
				
				var bWidth=$(window).width();
				var iWidth=opBox.width();
				
				opBox.css({opacity:0});
				opBox.animate({left:(bWidth-iWidth)/2-150},100);
				opBox.animate({top:300},100);
				opBox.animate({left:(bWidth-iWidth)/2,opacity:1},200);
			},
		makeOpHtml:function(type){
				var iName=this.param.name?this.param.name:null;
				var oType=type=="add"?"添加子类":(type=="edi"?"编辑该类":"");
				var elem=$("<div id='opHead'>"+oType+"</div>"+
				"<div id='opContent'><input id='nameValue' type='text' name='iname'" +
						" value='"+(iName||"")+"' /></div>"+
				"<span id='opSend'>确定</span><span id='opCancel'>取消</span>");
				return elem;
			},
		doOper:function(){
				$.getJSON(this.url,this.param,function(data){
					if(!data){
						alert("sorry,there is sth wrong!");
					}else{
						var elem=$("<p>OK，操作成功！</p>");
						operIssue.makeOpBox(elem)
						setTimeout(operIssue.closeOpBox,2000);
					}
				});
			},
		
		bind:function(){
				var iClose=$("#head1");
				iClose.live("dblclick",function(){
					$("#issueBox").animate({top:50,opacity:0},300);
					$("#issuePaper").animate({opacity:0});
					setTimeout(function(){
						$("#issuePaper").css({display:"none"});
					},500);
				});
				
				var lastI=$("#lastI");
				lastI.live("click",function(){
					operIssue.url="/index/grab_issueTypeJson";
					operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{pid:9});
					operIssue.getIssueList();
				});
				
				var btnName=$(".btnName");
				btnName.live("click",function(){
					var pid=$(this).siblings().eq(0).html();
					operIssue.url="/index/grab_issueTypeJson";
					operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{pid:pid});
					//operIssue.pid=$(this).siblings().eq(0).html();
					operIssue.getIssueList();
				})
				
				var add1=$("#add1");
				add1.live("click",function(){
					operIssue.url="/index/grab_addIssue";
					var pid=$(this).parent().siblings().eq(0).html();
					operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{pid:9,name:null});
					operIssue.makeOpBox(operIssue.makeOpHtml("add"));
					
				});
				
				var add=$("#add");
				add.live("click",function(){
					operIssue.url="/index/grab_addIssue";
					var pid=$(this).parent().siblings().eq(0).html();
					operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{pid:pid,name:null});
					operIssue.makeOpBox(operIssue.makeOpHtml("add"));
					
				});
				
				var edi=$("#edi");
				edi.live("click",function(){
					operIssue.url="/index/grab_editIssue";
					var p=$(this).parent().siblings()
					var id=p.eq(0).html();
					var iname=p.eq(2).html();
					operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{id:id,name:iname});
					operIssue.makeOpBox(operIssue.makeOpHtml("edi"));
				});
				
				var del=$("#del");
				del.live("click",function(){
					operIssue.url="/index/grab_deleteIssue";
					var p=$(this).parent().siblings()
					var id=p.eq(0).html();
					//var iname=p.eq(2).html();
					operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{id:id});
					var ruSure=$("<p>确定删除？</p>" +
							"<span id='opSend'>确定</span><span id='opCancel'>取消</span>");
					operIssue.makeOpBox(ruSure);
				});
				
				var opSend=$("#opSend");
				opSend.live("click",function(){
					var iname=$("#nameValue").val();
					//operIssue.param={t:new Date().getTime()}
					$.extend(operIssue.param,{name:iname});
					
					operIssue.doOper();
				});
				
				var opCancel=$("#opCancel");
				opCancel.live("click",function(){
					operIssue.closeOpBox();
				});
			},
		closeOpBox:function(){
				$("#opBox").animate({top:250,opacity:0},300);
				$("#opBox").animate({top:0},100);
			}
};
window.onload=function(){
	var isue=$("#issue").find(".zhankai");
	isue.click(function(){
		operIssue.getIssueList();
		operIssue.bind();
	});
}