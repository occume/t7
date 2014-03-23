$(function(){
//	alert(Math.ceil(10/10));
	batch.init();
})
var batch={
			currentPage:1,
			pageSize:10,
			totalPage:0,
			totalCount:0,
			batchData:[],
			init:function(){
					//alert("batch");
					batch.batchData=window.opener.batchHandle.batchArray.slice(0);
					//window.opener.batchHandle.batchArray=[];
					
					while(true){
						//alert(window.opener.batchHandle.batchArray.length);
						if(window.opener.batchHandle.batchArray.length==0){
							//alert(window.opener.batchHandle.batchArray.length);
							break;
						}
						window.opener.batchHandle.batchArray.pop();
						//batch.batchData.push(c);
					}
					//alert(batch.batchData.length);
					batch.totalCount=batch.batchData.length;
					batch.totalPage=Math.ceil(batch.totalCount/batch.pageSize);
					//alert(batch.batchData.constructor);
					batch.buildHtml();
					//batch.test();
					batch.addPageHandler();
					batch.bindOpr();
					},
			buildHtml:function(){
						
						batch.clearList();
					
						var begin=(batch.currentPage-1)*batch.pageSize;
						var end=batch.currentPage*batch.pageSize;
						var page=batch.batchData.slice(begin,end);
					
						$.each(page,function(i,n){
							batch.appendRow(n);
						});
						batch.bulidPage();
					},
			appendRow:function(woList){
				var html='<div class="mylistlight">\
							<div class="boxcont1 ids" style="cursor:pointer;" >'+woList.id+'</div>\
							<div class="boxcont4" style="cursor:pointer;" >'+woList.acc+'</div>\
							<div class="boxcont5" style="cursor:pointer;">'+woList.type+'</div>\
							<div class="boxcont6" style="cursor:pointer;">'+woList.createTime+'</div>\
							<div class="boxcont6" style="cursor:pointer;">'+woList.memo+'</div>\
							<div class="boxcont2">'+woList.repleyType+'</div>\
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
						if (i==batch.currentPage){
							$("<option value='"+i+"' selected=\"selected\">"+i+"</option>").appendTo(ddr);
							}
						else
							$("<option value='"+i+"'>"+i+"</option>").appendTo(ddr);
					}
					
					pagelist.find("#tp").html(batch.totalPage);
					pagelist.find("#ts").html(batch.pageSize);	
					pagelist.find("#tc").html(batch.totalCount);	
					},
				addPageHandler:function(){
					var self=this;
					var pagelist =$("#mygdgclistpage")
					var first = pagelist.find(".buttonfirst>a");
					var front = pagelist.find(".buttonfront>a");
					var next = pagelist.find(".buttonnext>a");
					var last = pagelist.find(".buttonend>a");
					
					var ddr = pagelist.find(".buttonselect .select40");
					first.live("click",batch.firstpage);
					front.live("click",batch.prevpage);
					next.live("click",batch.nextpage);
					last.live("click",batch.lastpage);
					ddr.livequery("change",function(){batch.changepage(this);});
					},
					changepage:function(obj){
						//alert($(obj).val());
						batch.currentPage=$(obj).val();
						batch.buildHtml();
						},
					firstpage:function(){
							//alert("first");
							batch.currentPage=1;
							batch.buildHtml();
						},
					nextpage:function(){
							//alert("before"+batch.currentPage);
							batch.currentPage+=1;
							//alert("after"+batch.currentPage);
						if (batch.currentPage>batch.totalPage)
							batch.currentPage=batch.totalPage;
							
						batch.buildHtml();
						},
					prevpage:function(){
							//alert("prev");
							batch.currentPage-=1;
						if (batch.currentPage<1)
							batch.currentPage=1;
							
						batch.buildHtml();
						},
					lastpage:function(){
							//alert("last");
							batch.currentPage=batch.totalPage;
							batch.buildHtml();
						},
					clearList:function(){
							$("#mygdcxlist .mylistlight").remove();
							},
				bindOpr:function(){
								var bTextArea="<span class=\"hhint\">该操作不可逆，请慎重！</span><br/><textarea class=\"batchReplyTextArea\"></textarea>";
								$(".batchReply").click(function(){
									
										if(batch.isNothing()){
											dialog.Alert("批量操作清单为空！");
											return ;
										}
										dialog.textArea(bTextArea,
												function(){
													batch.batchOprate("/json/query_batchReply");
												})
								});
								$(".batchDelete").click(function(){
									if(batch.isNothing()){
										dialog.Alert("批量操作清单为空！");
										return ;
									}
									dialog.textArea(bTextArea,
											function(){
												batch.batchOprate("/json/query_batchDelete");
											})
										
								});
								$(".batchReturn").click(function(){
									if(batch.isNothing()){
										dialog.Alert("批量操作清单为空！");
										return ;
									}
									dialog.textArea(bTextArea,
											function(){
										batch.batchOprate("/json/query_batchReturn");
											})
							});
							},
				batchOprate:function(url){
								var arrId=[];
								var ids=$(".ids");
								var content=$(".batchReplyTextArea").val();
								if(ids.length==0){
									dialog.Alert("批量操作清单为空！");
									return;
								}
								if(isEmpty(content)){
									$(".hhint").html("操作原因不能为空");
									return;
								}else{
									dialog.Alert("操作进行中，请稍后...");
								}
								
								$.each(ids,function(i,n){
										arrId.push($(n).html());
								});
								
								$.ajax({
										  type: 'POST',
										  url: url,
										  data: {"ids":arrId.toString(),"content":content},
										  dataType: "json",
										  cache:false,
										  success: function(data){
																if(data.message){
																	dialog.Alert(data.message);
																	setTimeout(function(){
																		dialog.close();
																		window.location.href=window.location.href;
																		window.opener.location.href=window.opener.location.href;
																	},2000);
																	
																}else{
																	dialog.Alert("批量操作出现错误！");
																	setTimeout(function(){
																		dialog.close();
																		window.location.href=window.location.href;
																		window.opener.location.href=window.opener.location.href;
																	},2000);
																}
														}
								});
							},
							isNothing:function(){
								var ids=$(".ids");
								if(ids.length==0){
									return true;
								}
								return false;
							},
				test:function(){
					var arr=window.opener.batchHandle.batchArray;
					var subArr=arr.slice(1,3);
					alert(arr.length);
				}
}
function isEmpty(s){
	if(s.replace(/(^\s*)|(\s*$)/g, '').length<=0)
	{//为空
	return true;
	}
	else{//不为空
	return false;
	}
	}