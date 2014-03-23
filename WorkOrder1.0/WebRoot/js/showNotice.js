function showAssignNotice(data){
	//for(var o in data)
	//alert("dao");
		//alert(data.nvp.name);
		//document.title="您有最新派单！";
		var i=0,title_temp=document.title;
		function hint(){
			if(i>100) return;
			if(i%2==0)
				document.title="派单到！";
			else
				document.title=" <(n_n)> ";
			i++
			timer=setTimeout(hint,1000);
		}
		hint();
		window.onfocus=function(){
			clearTimeout(timer);
			document.title=title_temp;
			window.onfocus=null;
		}
		notice.showOneAssign(data);
}
function showNotice(data){
		notice.showOne(data.faq);
}

$(function(){
	if(!document.getElementById("noticeWall")){
		var outWall = document.createElement("div");
		outWall.setAttribute("id","outWall")
		var closeAll = document.createElement("div");
		closeAll.setAttribute("id","closeAll");
		var text=document.createTextNode("关闭");
		closeAll.appendChild(text);
		var wall = document.createElement("div");
		wall.setAttribute("id","noticeWall");
		outWall.appendChild(wall);
		outWall.appendChild(closeAll);
		document.getElementsByTagName("body")[0].appendChild(outWall);
	}
	if(!document.getElementById("assignWall")){
		var assignWall = document.createElement("div");
		assignWall.setAttribute("id","assignWall")
		var closeAssign = document.createElement("div");
		closeAssign.setAttribute("id","closeAssign");
		var text1=document.createTextNode("关闭");
		closeAssign.appendChild(text1);
		
		assignWall.appendChild(closeAssign);
		document.getElementsByTagName("body")[0].appendChild(assignWall);
	}
	notice.bind();
//	var data={
//			id:1000,
//			descrip:'十个字加十个字十个字十个字加十个字十个字十个字加十个字十个字十个字加十个字十个字',
//			gameName:'XXX'
//	}
//	notice.showOneAssign(data);
//	notice.showOneAssign(data);
})

var notice={
		toEmpty:false,
		isFirst:true,
		index:0,aindex:0,
		noticeSet:[],
		putOnWall:function(data){
			var the=notice.create("noticeWall");
			//alert(the.offsetHeight)
			var content=find(the,"content");
			content.innerHTML=data.descrip;
			var tail=find(the,"tail");
			var temp=tail.innerHTML;
			tail.innerHTML=data.gameName+"&nbsp;"+new Date(data.createTime).pattern("yyyy-MM-dd hh:mm:ss")+temp;
			return the;
		},
		create:function(elem){
			var wall=document.getElementById(elem);
			
			var node="<div class='pack'><div class='notice'>" +
						"<div class='title'><div class='t1'>小盆友，小喇叭广播开始了！</div><div class='close'>知道了</div></div>" +
						"<div class='content'></div>" +
						"<div class='tail'> <img  src='/images/ly.png'/></div>" +
					 "</div></div>";
			wall.innerHTML+=node;
			return getElementsByClassName("pack",wall,"div")[notice.index++];
		},
		createAssign:function(){
			var assignWall=document.getElementById("assignWall");
			var node="<div class='apack'><div class='anotice'>派单消息››" +
						"<div class='content'></div>"+
					 "</div></div>";
			assignWall.innerHTML=node+assignWall.innerHTML;
			return getElementsByClassName("apack",assignWall,"div")[notice.aindex];
		},
		showOneAssign:function(data){
			var aWall=$("#assignWall");
			var wall=$("#noticeWall");
			if(notice.isFirst){
				aWall.css({top:-1000});
				notice.isFirst=false;
			}
			aWall.css({}).show();
			if(wall.attr("display")=='none'){
				aWall.animate({top:0});
			}else{
				aWall.animate({top:wall.height()});
			}
			
			var curr=notice.createAssign();
			
			var content=find(curr,"content"),
				temp=content.innerHTML,
				txt=data.descrip;
			txt=txt.length>25?(txt.substring(0,25)+"..."):txt;
			content.innerHTML=temp+"工单号:「"+data.id+"」「"+txt+"」"+new Date().pattern("yyyy-MM-dd hh:mm:ss");
			$(curr).click(function(){
				//alert("open");
				window.open("/json/query_toSingleWorkOrder?workOrderId="+data.id, "newwindow100");
			});
			$(curr).css({borderColor:"#969"});
			$(curr).siblings().css({borderColor:""});
			
			
			aWall.find(".apack").slideDown();
			//notice.replaceAssign();
		},
		replaceAssign:function(){
			
			var assignWall=$("#assignWall"),
				wall=$("#outWall"),
				h=wall.height();
			if(assignWall.attr("display")=='none') return;
			if(arguments.length>0){
				assignWall.animate({top:0});
				return;
			}
			assignWall.animate({top:h});
		},
		showAll:function(elem){
			$("#outWall").css({top:-1000}).show();
			notice.toEmpty=false;
			var p=window.opener||null;
			//alert(window.opener);
			var data=notice.noticeSet;
			if(notice.noticeSet.length==0 && p.notice.noticeSet.length!=0){
				data=p.notice.noticeSet;
			}
		//	if(data.length==0) return;
			//data.push({d:"通知7",g:"xxx7"});
			var wall=document.getElementById("noticeWall");
			notice.index=0;
			wall.innerHTML="";
			for(var o in data){
				if(!data.hasOwnProperty(o)) continue;
				notice.putOnWall(data[o]);
			}
			pub.initAll(wall);
			notice.replaceAssign();
		},
		//oneIndex:0,
		showOne:function(obj){
			
			$("#outWall").show();
			if(notice.toEmpty){
				$("#outWall").find("#noticeWall").html("");
				notice.toEmpty=false;
				}
			notice.noticeSet.push(obj);
			var curr=notice.putOnWall(obj);
			pub.initAll(document.getElementById("noticeWall"));
			$(curr).css({backgroundColor:"#366"});
			$(curr).siblings().css({backgroundColor:""});
			notice.replaceAssign();
		},
		close:function(){},
		closeOne:function(){
				
		},
		bind:function(){
			$(".close").live("click",function(){
				var p=$(this).parent().parent().parent();
				
				p.hide();
				p.attr("go","");
				if(p.parent().find(":visible").length==0){
					$("#outWall").animate({top:-1000},800,"",function(){
						$(this).css({display:"none"});
					});
					notice.replaceAssign("toLeft0");
					return;
				}
				pub.initAll(document.getElementById("noticeWall"));
			});
			$("#closeAll").live("click",function(){
				$("#outWall").animate({top:-1000},800,"",function(){
					$(this).css({display:"none"});
				});
				notice.toEmpty=true;
				notice.index=0;
				notice.replaceAssign("toLeft0");
			});
			$("#lookMore").live("click",function(){
				
				var p=window.opener||null,num=0;
				if(p){
					num=p.notice.noticeSet.length;
				}
				if(notice.noticeSet.length==0 && num==0){
					$.alert("没有最新通知！",true);
					return;
				}
				notice.showAll();
			});
			$(window).scroll(function(){
				
				if($("#outWall").offset().top<0){
					return;
				}
				var pY=$(window).scrollTop();
				$("#outWall").css({top:pY});
			});
			var aw=$("#assignWall");
			aw.find(".apack").live("mouseover",function(){
				
				$(this).css({borderColor:"#969"});
				$(this).siblings().css({borderColor:""});
			});
			aw.find("#closeAssign").live("click",function(){
				$(this).parent().animate({top:-1000},1000,"",function(){
					$(this).css({display:"none"}).html("<div id='closeAssign'>关闭</div>");
				})
				notice.isFirst=true;
			})
		}
	}

	var pub={};
	pub.isFirst=true;
	pub.initAll=function(elem){
		pub.isFirst=false;
		var nx,ny,h,w;
		pub.mbox=[];
		pub.boxSet=[];
		pub.gridObj={};
		pub.gridSet=[];
		pub.index=0;

		pub.row=document.documentElement.offsetWidth/380 >> 0;
		var tempBox=getElementsByClassName("pack",elem,"div");
		for(var i=0;i<tempBox.length;i++){
			var temElem=tempBox[i];
		//	alert(temElem.getAttribute("go"));
			if(temElem.getAttribute("go")==null){
				pub.mbox.push(temElem);
			}
		}
		//pub.mbox=tempBox;
		//alert(pub.mbox.length);
		pub.maxY=-1;
		elem.style.width=pub.row*380+"px";
		//alert(pub.mbox.length);
		//var mboxSet=getElementsByClassName("notice",elem,"div");
		for(var i=0;i<pub.mbox.length;i++){
			//alert(pub.mbox[i].getAttribute("go"));
			//if(pub.mbox[i].getAttribute("go")!=null) continue; 
			//alert(i);
			var cuBox=pub.mbox[i];
				the=find(cuBox,"notice"),
				h=the.offsetHeight;
				ny=Math.ceil(h/180);
				//alert(ny);
			if(h>180){
				the.style.height=ny*180-30+"px";
				pub.boxSet.push([1,ny]);
			}else{
				the.style.height=ny*180-30+"px";
				pub.boxSet.push(1);
			}
			
		}
		//printAarry(pub.boxSet);
		pub.boxSort(elem);
	};
	pub.boxSort=function(elem){
		var y=0, x=0, temp={x:Infinity, y:Infinity}, flag=Infinity, name;
		var bset=this.boxSet;
		
		for(var i=0;i<bset.length;i++){
			//if(pub.mbox[i].getAttribute("go")!=null) continue; 
			if(flag == 0){
				x=temp.x;
				y=temp.y;
			}
			flag=flag-1;

			if(x>=pub.row){
				x=0;
				y++;
			}
			
			name=x+"_"+y;
			if(pub.hasN(name)){
				i--;
				x++;
				if(flag<Infinity) flag=flag+1;
				continue;
			}

			if(!bset[i].length){
				pub.gridObj[name]=[x,y];
				x++;
			}else{
				if(pub.over(x,y,i)){
					if(temp.y > y){
						temp.y = y;
						temp.x = x;
					}
					if(temp.y < Infinity){
						flag=1;
					}
					i--;
					x++;
					continue;
				}
				
				pub.gridObj[name]=[x,y];
				pub.occu(x,y,i);
				x+=pub.boxSet[i][0];
			}
				if(flag==-1) {
					flag = 	Infinity;
					temp.y = Infinity;
					temp.x = Infinity;
				}
			var h=this.boxSet[i][1]-1 || 0;
			pub.maxY=(pub.maxY > y+h)? pub.maxY : y+h;
		}
		
		for(var o in pub.gridObj){
			if(pub.gridObj[o]===0 || !pub.gridObj.hasOwnProperty(o)) continue;
			pub.gridSet.push(pub.gridObj[o]);
		}
		pub.local(elem);
	};
	pub.hasN=function(name){
		return name in pub.gridObj;
	};
	pub.over=function(x,y,n){
		var name;
		if(x+pub.boxSet[n][0] > pub.row) return true;
		for(var i=1;i<pub.boxSet[n][0];i++){
			//if(pub.mbox[i].getAttribute("go")!=null) continue; 
			name=(x-0+i)+"_"+y;
			if(pub.hasN(name)) return true;
		}
		for(var j=1;j<pub.boxSet[n][1];j++){
			//if(pub.mbox[i].getAttribute("go")!=null) continue; 
			name=x+"_"+(y-0+j);
			if(pub.hasN(name)) return true;
		}
		
	};
	pub.occu=function(x,y,n){
		var w=this.boxSet[n][0],
			h=this.boxSet[n][1],
			pX=x,pY=y,
			name;
			for(var i=0;i<w;i++){
				for(var j=0;j<h;j++){
					if(i==0 && j==0) continue;
					name=(pX+i)+"_"+(pY+j);
					pub.gridObj[name]=0;
				}
			}
		//pub.gridObj[]
	};
	pub.local=function(elem){
		//alert(pub.gridSet.length);
		//alert(pub.mbox.length);
		
		//$(elem).hide();
		
		
		var arr=pub.gridSet;
		var x,y;
		//alert(pub.mbox[1]);
		
		
			for(var i=0;i<arr.length;i++){
				//if(pub.mbox[i].getAttribute("go")!=null) continue; 
				x=380*arr[i][0];
				y=180*arr[i][1];
				//pub.mbox[i].style.cssText = "position:absolute;left:"+ x +"px;top:" + y + "px;";
				var pos=pub.mbox[i].style.position;
				if(pos!='absolute')
					pub.mbox[i].style.cssText = "position:absolute;top:-500px;";
			}
		
		elem.style.height= 180*(pub.maxY+1) +'px';
		elem.parentNode.style.height=180*(pub.maxY+1)+20 +'px';
		if(pub.isFirst){
			$(elem).parent().css({top:-1000});
		}
		$(elem).parent().animate({top:0},800,function(){
			for(var i=0;i<arr.length;i++){
				//if(pub.mbox[i].getAttribute("go")!=null) continue; 
				x=380*arr[i][0];
				y=180*arr[i][1];
				$(pub.mbox[i]).animate({top:y,left:x},600);
			}
			
		});
	};


	function find(elem,className){
		return getElementsByClassName(className,elem,"div")[0];
	};
	var getElementsByClassName = function(className,parent,tag) {
		parent = parent || document;
	    if(parent.getElementsByClassName){
	        return  parent.getElementsByClassName(className)
	    }else{
	        tag = tag || '*';
	        var returnElements = []
	        var els =  parent.getElementsByTagName(tag);
	        className = className.replace(/\-/g, "\\-");
	        var pattern = new RegExp("(^|\\s)"+className+"(\\s|$)");
			var i = 0;
	        while(i < els.length){
	            if (pattern.test(els[i].className) ) {
	                returnElements.push(els[i]);
	            }
				i++;
	        }
	        return returnElements;
	    }
	};
	