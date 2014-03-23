var gdedit={
		EDIT_STATUS:0,
		orientType:"",
		init:function(){
				gdedit.addExtra();
				gdedit.bindhandler();
		},
		addExtra:function(){
			var self=this;
			$(".editMe").bind("click",function(){
				
					self.orientType=$(this).html();
					self.edit();
					$(this).unbind("click");
					
			});
			$("#btn_edit").live("click",function(){
				
				gdedit.extraEdit();
			
				//gdedit.extraSave();
			
		});
		},
		extraEdit:function(){
			//$("#ediggdform textarea").attr("disabled","");
			var textArea="<form id='extraForm' action='/json/query_extraUpdateWorkOrder' method='post' enctype='multipart/form-data'>" +
					"<input type='hidden' name='woId' value='"+$("#workOrderId").val()+"'>" +
					"<textarea id=\"addMemo\" name='description' style=\"height:200px;width:400px;\"></textarea>" +
					"<input name='upload' type='file'/>"+
					"<input name='upload' type='file'/>"+
					"<input name='upload' type='file'/>"+
					"</form>"+
					"<h3 style='color:red;'>严重注意：单个上传文件不要大于2M!</h4>";
			dialog.Confirm(textArea,function(){
				$("#extraForm").submit();
			});
		},
		extraSave:function(){
//			$.ajax({
//				   type: "POST",
//				   url: "/json/query_extraUpdateWorkOrder",
//				   data: "description="+$("#addMemo").val()+"&woId="+$("#workOrderId").val(),
//				   dataType:"text",
//				   cache:false,
//				   success:function(data){
//						dialog.close();
//						parent.window.location.href=parent.window.location.href;
//					   }
//				   });
		},
		bindhandler:function(){
			var self=this;
			$("#bNo").live("click",function(){
				$(".editMe").html(self.orientType);
				self.addExtra();
			});
			$("#bYes").live("click",function(){
				dialog.Alert("操作进行中...");
				self.orientType=$("#issueSelect1").val();
				self.save();
				self.addExtra();
			});
},
	edit:function(){
	//alert("edit");
		$.ajax({
			   type: "GET",
			   url: "/index/index_getSelectInfo",
			   dataType: "json",
			   cache:false,
			   success:function(data){
			
			//jobj.element("area", woService.initAreaMap());
			//jobj.element("game", woService.initGameMap());
			//jobj.element("source",woService.initSourceList());
			//jobj.element("urgency",woService.initUrgencyList());
			//jobj.element("server", woService.initServerMap());
			//jobj.element("issue", woService.initIssueMap());
			//jobj.element("roleVaca", woService.initClassCategoryMap());
			
			//gdedit.setSelect(data.area, "area");
			//gdedit.setSelect(data.game, "game");
			//gdedit.setSelect(data.source, "source");
			//gdedit.setSelect(data.urgency, "urgency");
			//gdedit.setSelect(data.server, "server");
			//alert(data.issue);
			gdedit.setSelect(data.issue, "editMe");
			//gdedit.setSelect(data.roleVaca, "roleVaca");
			//gdedit.enabledinput();
		//	gdedit.EDIT_STATUS=1;
		//	$("#btn_edit").html("保存工单");
				   }
			   });
	
},
		save:function(){
				var self=this;
				$.ajax({
					   type: "POST",
					   url: "/wo/save_updateWorkOrder",
					   data: {woId:$(".editMe").attr("woId"),"userInfo.lvlOne.name":$("#issueSelect1").val()},
					   dataType:"text",
					   cache:false,
					   success:function(data){
						   dialog.Alert(data);
						   $(".editMe").html(self.orientType);
						   setTimeout(dialog.close, 2000);
						   }
					   });
},
		
		setSelect:function(data,Container){
		//alert(data);
			$("."+Container).html("");
			var inputValue=this.orientType;
			var select= gdedit.bulidSelect(data,inputValue);
			$("."+Container).append(select);
			$("."+Container).append("<span id='bYes'>YES</span>|<span id='bNo'>NO</span>");
		},
		bulidSelect:function(data,value){
			
			var s="<select id='issueSelect1' name=''>";
			
			$.each(data,function(i,n){
				if (n==value){
					s=s+"<option value='"+n+"' selected=\"selected\" >"+n+"</option>";
				}else{
					s=s+"<option value='"+n+"'>"+n+"</option>";
				}
			});
			
			s=s+"</select>";
			return s;
		}
		
};

$(function(){
	gdedit.init();
	$("#innerMark").click(function(){
		var self=$(this);
		//alert("mark");
		funInnerMark();
	});
//	$("#btn_GM").click(function(){
//		var self=$(this);
//		funAssignGM(self);
//	});
	$(".ishf").click(function(){
		funAlterIshf();
	});
	
	$(".state").click(function(){
		funAlterState(this);
	});
})
//改变需要电话回访状态
function funAlterIshf(){//
	$.ajax({
		   type: "POST",
		   url:"/json/query_alterIshf",
		   data: "id="+$("#workOrderId").val()+"&t="+new Date().getTime(),
		   dataType:"json",
		   cache:false,
		   success:function(data){
					if(data){
						dialog.Alert(data.message);
						//alert(data.message);
						window.location.href=window.location.href;
						//e.attr({"disabled":"disabled"});
					}else{
						dialog.Alert("当前不能改变 !");
					}
					setTimeout(dialog.close, 2000);
			}
		   });
}
//改变工单状态
function funAlterState(elem){//
	
	var states=['处理中','已处理','待补充资料','已反馈'];
	var me=$(elem);
	me.unbind("click");
	var currState=me.html();
	//alert(currState);
	if(me.find("select").length<1){
		me.html(buildStateSelection(states,currState));
		me.parent().append("<span id='sYes'>确定|</span><span id='sNo'>取消</span>");
	}
	
	$("#sYes").css({cursor:'pointer'}).live("click",function (){
		if($("#stateSelect").val()==currState){
			dialog.Alert("请选择一个不同的状态");
			return;
		}
		var bTextArea="<span class=\"hhint\">该操作不可逆，请慎重！</span><br/><textarea class=\"batchReplyTextArea\"></textarea>";
		dialog.Confirm(bTextArea, function(){
			var content=$(".batchReplyTextArea").val()
			
			if(isEmpty(content)){
				$(".hhint").html("操作原因不能为空");
				return;
			}else{
				dialog.Alert("操作进行中，请稍后...");
			}
			$.ajax({
				   type: "POST",
				   url:"/json/query_alterState",
				   data: "id="+$("#workOrderId").val()+"&description="+$("#stateSelect").val()+"&extraCondition="+content+"&t="+new Date().getTime(),
				   dataType:"json",
				   cache:false,
				   success:function(data){
							if(data){
								dialog.Alert(data.message);
								me.html($("#stateSelect").val());
								$("#sNo").remove();
								$("#sYes").remove();
								me.click(function(){
									funAlterState(this);
								});
							}
							setTimeout(dialog.close, 2000);
					}
				   });
		});
		
	});
	$("#sNo").css({cursor:'pointer'}).live("click",function (){
		//alert(currState);
		me.html(currState);
		$(this).remove();
		$("#sYes").remove();
		me.click(function(){
			funAlterState(this);
		});
		return false;
	});
	
}
function buildStateSelection(data,value){
	
	var s="<select id='stateSelect' name=''>";
	
	$.each(data,function(i,n){
		if (n==value){
			s=s+"<option value='"+n+"' selected=\"selected\" >"+n+"</option>";
		}else{
			s=s+"<option value='"+n+"'>"+n+"</option>";
		}
	});
	
	s=s+"</select>";
	return s;
}
function funAssignGM(e){//派转GM
	$.ajax({
		   type: "POST",
		   url:"/json/query_assignGM",
		   data: "id="+$("#workOrderId").val()+"&t="+new Date().getTime(),
		   dataType:"json",
		   cache:false,
		   success:function(data){
				if(data.message!=null){
					alert(data.message);
					e.toggleClass("tools4no_mouseout");
					e.attr({"disabled":"disabled"});
				}else{
					alert("Default to return !");
				}
			   }
		   });
}
function funInnerMark(){//
	$.ajax({
		   type: "POST",
		   url:"/json/query_returnWorkOrder",
		   data: "id="+$("#workOrderId").val()+"&t="+new Date().getTime(),
		   dataType:"json",
		   cache:false,
		   success:function(data){
				if(data){
					dialog.Alert(data.message);
					//alert(data.message);
					// window.location.href=window.location.href;
					//e.attr({"disabled":"disabled"});
				}else{
					dialog.Alert("当前不能标记 !");
				}
				setTimeout(dialog.close, 2000);
			   }
		   });
}

function getOpers(container,type){
	this.url="/json/query_getOpers";
	this.container=container;
	this.operType=type;
}
getOpers.prototype={
	init:function(){
		
	},
	grab:function(){
		this.container.html("");
		var self=this;
		//alert("grab");
		$.ajax({
			   type: "POST",
			   url: self.url,
			   data: "workOrderId="+$("#workOrderId").val()+"&operGenre="+this.operType+"&t="+new Date().getTime(),
			   dataType:"json",
			   cache:false,
			   success:function(data){
					//alert(data.operList.length);
					$.each(data.operList,function(i){self.buildHtml(i,this);});
				   }
			   });
	},
	display:function(){
		this.container.show();
	},
	buildHtml:function(index,operList){
	//	alert(index);
		var html='<div class="tabcont">\
			<div class="tabcont1">'+index+'</div>\
			<div class="tabcont2">'+operList.depart+'</div>\
			<div class="tabcont3">'+operList.person+'</div>\
			<div class="tabcont4">'+operList.operTime+'</div>\
			<div class="tabcont5">'+operList.content+'</div>\
			<div class="clear"></div>\
			</div>';

	this.container.append(html);
	}
}
/**
 * 工单回复内容编辑
 */
var pthfEdit={
		parent:null,
		ind:null,
		content:"",
		overCotent:0,
		overBtnEdit:1,
		init:function(){
							this.bindFun();
						},
		bindFun:function(){
							var bTextArea="<span class=\"hhint\">该操作不可逆，请慎重！</span><br/><textarea class=\"deReasonTextArea\"></textarea>";
							var self=this;
							var gdhfBox=$(".pthf-box");
							var btnEdit=$("#btnEdit");
							var btnDelete=$("#btnDelete");
							var btnHideArea=$("#quxiao");
							var btnSubmit=$("#queding");
							
							gdhfBox.live("mouseover",function(){
								self.showEditBtn($(this));
							})
							gdhfBox.live("mouseleave",function(){
									self.hideEditBtn($(this));
							})
							btnEdit.live("click",function(){
									self.showTextArea();
							});
							btnHideArea.live("click",function(){
									self.hideTextArea();
							})
							btnSubmit.live("click",function(){
								self.content=$("#areaEdit").val();
								self.doEdit();
							});
							btnDelete.live("click",function(){
								var id=gdhfBox.attr("ind");
								dialog.textArea(bTextArea,
										function(){
											pthfEdit.doDelete(id);
										})
							});
//							pBtnEdit.live("mouseover",function(){
//								self.showEditBtn(gdhfBox);
//							})
//							pBtnEdit.live("mouseout",function(){
//									self.showEditBtn(gdhfBox);
//							})
						},				
		showEditBtn:function(obj){
								if(!document.getElementById("pbtnEdit")){
									if(!document.getElementById("pArea")){
										this.content=obj.attr("title");
										this.parent=obj;
										this.ind=obj.attr("ind");
										obj.append("<div id='pbtnEdit'><p id='btnEdit'>编辑</p>" +
																"<p id='btnDelete'>删除</p><div>");
										obj.css({"border":"1px dashed #ccc"});
										$("#pbtnEdit").slideDown();
//										obj..animate({
//											border:"1px solid black"
//											//opacity: 'show'
//										})
									}
								}
						},
		hideEditBtn:function(obj){
							if(document.getElementById("pbtnEdit")){
								
								$("#pbtnEdit").remove();
								obj.css({"border":"none"});
							}
						},
		showTextArea:function(){
							this.parent.html("");
							this.parent.append("<div id='pArea'><textarea rows='10' cols='100' id='areaEdit' name=''></textarea><div>" +
									"编辑原因："+
									"<div id='rArea'><textarea rows='5' cols='100' id='areaReason' name=''></textarea><div>"+
									"<div><span id='queding'>确定</span><span id='quxiao'>取消</span></div>");
							$("#areaEdit").val(this.content);
						},
		hideTextArea:function(){
							this.parent.html("");
							this.parent.html(this.content);
							this.parent.css("border","none");
						},
		doEdit:function(){
							var self=this;
							var reason=$("#areaReason").val();
							var content=$("#areaEdit").val();
							if(isEmpty(content)){
								alert("回复内容不能为空");
							return;
							}
							if(isEmpty(reason)){
								alert("请填写编辑原因");
							return;
							}
							dialog.Alert("操作进行中，请稍后...");
							$.ajax({
									 type: 'GET',
									  url: "/wo/edit_editPtReply",
									  data: {id:this.ind,reply:content,comment:reason},
									  cache:false,
									  dataType:"text",
									  success: function(data){
										  	if(data){
										  		dialog.Alert(data);
										  		self.parent.html(self.content);
										  		self.parent.css("border","none");
										  		setTimeout(function(){
						 							dialog.close();
						 						},2000);
										  	}
										}
							});
						},
			doDelete:function(id){
							var reason=$(".deReasonTextArea").val();
							if(isEmpty(reason)){
								alert("原因不能为空！");
							return;
							}
							dialog.Alert("操作进行中，请稍后...");
							$.ajax({
								 type: 'GET',
								  url: "/wo/edit_deletePtReply",
								  data: {id:id,comment:reason},
								  cache:false,
								  dataType:"text",
								  success: function(data){
									  	if(data){
									  			dialog.Alert(data);
									  	
										  		setTimeout(function(){
						 							dialog.close();
						 							location.href=location.href
						 						},2000);
									  	}
									}
						});
						}			
}
$(function(){
	pthfEdit.init();
})
/**
 * 查询工单锁定状态
 */
$(function(){
	var woInfo=$("#woInfo");
	var thisId=woInfo.children().first().html();
	var from=thisId.indexOf(":");
	thisId=thisId.substring(from+1);
	
	$.ajax({
		  type: 'GET',
		  url: "/lock/isLockedAfter",
		  data: {"id":thisId},
		  dataType: "json",
		  cache:false,
		  success: function(data){
			  	if(data.message){
			  		if(data.message=="LOCKING"){
			  			woInfo.append("<span>该工单被你锁定，"+
			  					"<span id='openLock' style='color:#f93;cursor:pointer;'>解锁</span></span>");
			  		}
			  	}
			}
	});
	$("#openLock").live("click",function(){
		$.ajax({
			  type: 'GET',
			  url: "/lock/openLock",
			  data: {"id":thisId},
			  dataType: "json",
			  cache:false,
			  success: function(data){
				  	if(data.message){
				  		if(data.message=="OK"){
				  			alert("Ok")
				  		}
				  	}
				}
		});
	});
})
/*
 * inner 拉单
 */
 $(function(){
	 var bTextArea="<span class=\"hhint\">该操作需要填写操作原因！</span><br/><textarea class=\"batchReplyTextArea\"></textarea>";
	 	$("#innerLD").live("click",function(){
	 		dialog.textArea(bTextArea,function(){
	 			var content=$(".batchReplyTextArea").val();
	 			if(isEmpty(content)){
					$(".hhint").html("操作原因不能为空");
					return;
				}else{
					dialog.Alert("操作进行中，请稍后...");
					$.ajax({
	 				 	type: "get",
	 				   url: "/json/query_specialPullWorkOrder",
	 				   data: {"id":$("#workOrderId").val(),"reply":content},
	 				   dataType:"json",
	 				   cache:false,
	 				   success:function(data){
	 						dialog.Alert(data.message);
	 						setTimeout(function(){
	 							dialog.close();
	 							window.location.href=window.location.href;
	 							window.parent.location.href=window.parent.location.href;
	 						},2000);
	 					   }
	 			})
				}
	 		
	 		});
	 	});
 })
function isEmpty(s){
	if(s.replace(/(^\s*)|(\s*$)/g, '').length<=0)
	{//为空
	return true;
	}
	else{//不为空
	return false;
	}
	}