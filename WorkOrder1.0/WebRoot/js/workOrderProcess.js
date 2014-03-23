
var batchHandle={
		cbParent:".mylistlight",
		batchArray:[],
		init:function(){
						$(".optAll").click(function(){
							batchHandle.optAll(batchHandle.cbParent);
						});
						$(".optReverse").click(function(){
							batchHandle.optReverse(batchHandle.cbParent);
						});
						$(".addToBatch").click(function(){
							batchHandle.addToBatchHandle(batchHandle.cbParent);
						})
						$("#btn_plhf").click(function(){
							//alert("plhf");
							batchHandle.senData();
						});
					//	batchHandle.test();
					},
		optAll:function(cbParent){//全选
						var batchAll=$(cbParent).find("input[type='checkbox']");
						$.each(batchAll,function(i,n){
							if(n.checked){
								//do nothing
							}else{
								$(n).attr("checked","true");
							}
						})
						},
		optReverse:function(cbParent){//反选
							var batchAll=$(cbParent).find("input[type='checkbox']");
							$.each(batchAll,function(i,n){
								if(n.checked){
									$(n).attr("checked","");
								}else{
									$(n).attr("checked","true");
								}
							})
						},
		addToBatchHandle:function(cbParent){//缓存
							var batchAll=$(cbParent).find("input[checked='true']");
							if(batchAll.length>0){
									$.each(batchAll,function(i,n){
										//alert("this.siblings="+$(n).parent().siblings(".boxcont1").html());
										//alert(batchHandle.buildJson(n));
										var d=batchHandle.buildJson(n);
										
										var flag=batchHandle.batchArray.contains(d);
										if(!flag){
											batchHandle.putToBatchArray(d);
										}else{
											
										}
									});
									dialog.Alert(""+batchHandle.batchArray.length+"条数据加入批量操作")
									setTimeout(dialog.close,2000);
							}else{
								dialog.Alert("请选择要加入批量操作的数据");
							}
						},
		putToBatchArray:function(obj){
							batchHandle.batchArray.push(obj);
						},
		buildJson:function(obj){
							var o=$(obj);
							var id=o.parent().siblings(".boxcont1").html();
							var acc=o.parent().siblings(".boxcont4").html();
							var type=o.parent().siblings(".boxcont5").html();
							var createTime=o.parent().siblings(".boxcont6").html();
							var memo=o.parent().parent().attr("title");
							var replyType=o.parent().siblings("boxcont10").find("input").val();
							var jsonStr="";
							jsonStr+='{"id":'+id+',"acc":"'+acc+'","type":"'+type+'","createTime":"'+
													createTime+'","memo":"'+(memo.length<20?"":memo.substring(0,19))+'","replyType":"'+replyType+'"}';
							//JSON.
							return JSON.parse(jsonStr);
						},	
		senData:function(){
						window.open("/ctService/batchOperate.jsp?batchData="+batchHandle.batchArray, "batchData");
//							$.ajax({
//								 type: 'POST',
//								  url: "/ctService/batchDataOperation.jsp",
//								  data: "batchData"+batchHandle.batchArray,
//								  dataType: "json",
//								  cache:false,
//								  success: function(data){
//										
//									}
//							})
						},				
		test:function(){
							var testArr=[1000,1001];
							alert(testArr.contains(1002));
						}
}

$(function(){
	batchHandle.init();
	var radios=$("#replyForm").find("span");
	$(radios[0]).css({"border":"1px dashed black","margin-left":20,"padding":3,"color":"black"});
	$(radios[1]).css({"border":"1px dashed blue","margin-left":20,"padding":3,"color":"blue"});
	$(radios[2]).css({"border":"1px dashed red","margin-left":20,"padding":3,"color":"red"});
	$("#replyForm").submit(function(){
		if(!ThreeIN1()) return false;
	});
	//
	//$("body").append("<div id='cellBox'></div>");
//	_.addEvent(window, 'load', clock.init)
	//clock.init();
})
/**
 * 回复操作时 必须勾选一个单选框
 * @param element
 * @return
 */
function ThreeIN1(){
	var three=$("input:checked");
	//alert(three.length);
	if(three.length<1){
		alert("请选择一个操作选项！");
		return false;
	}
		
	var text=$("#reply").val();
	//alert(3123123);
	if(isEmpty(text)){
		alert("回复内容不能为空!!!!!!!！");
		return false;
	}
	if(text.replace(/(^\s*)|(\s*$)/g, '').length<=3){
		alert("回复内容不能少于3个字符！");
		return false;
	}
	return true;
//	
//	alert(three.length);
}
Array.prototype.contains=function(element){
	var len=this.length;
	if(len==0)
		return false;
	for(var i=0;i<len;i++){
		if(this[i].id==element.id){
			return true;
		}
	}
	return false;
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
/**
 * Clock
 */
var _ = {
        extend: function (t, s, o) {
            if (o === undefined) o = true;
            for (var p in s) {
                if (!(p in t) || o) {
                    t[p] = s[p]
                }
            }
            return t;
        },
        addEvent: function (o, e, f) {
            o.addEventListener ? o.addEventListener(e, f, false) : o.attachEvent('on' + e, function () {f.call(o)})
        },
        $ : function (id) {
            return typeof id === 'string' ? D.getElementById(id) : id;
        },
        log : function (s) {
            !!window.console && console.log(s)
        }
    };
var Asist={
		log:function(mes){
			if(!document.getElementById("logBox")){
				var logBox=document.createElement("div");
				logBox.setAttribute("id","logBox");
				document.body.appendChild(logBox);
			}
			var logBox = document.getElementById("logBox");
			logBox.innerHTML +=("<p>"+mes+"</p>");
		}
	}

    var NUM = [
        '####   ##########  #####################',
        '#  #   #   #   ##  ##   #      ##  ##  #',
        '#  #   #   #   ##  ##   #      ##  ##  #',
        '#  #   #####################   #########',
        '#  #   ##      #   #   ##  #   ##  #   #',
        '#  #   ##      #   #   ##  #   ##  #   #',
        '####   #########   #########   #########'
    ]
	//alert(document.getElementById("cellBox"));
	var clock={
		paper:null,W:300,H:100,
		cells:[],cells1:[],outCell:[],inCell:[],isFirst:false,
		init:function(){
			var clockBox=document.createElement("div");
			clockBox.id="cellBox";
			clockBox.style.position="absolute";
			clockBox.style.left=1300+"px";
			clockBox.style.top=98+"px";
			clockBox.style.width=300+"px";
			clockBox.style.height=100+"px";
			//clockBox.style.margin="10px 0 0 100px";
			//clockBox.style.backgroundColor="#ddd";
			document.childNodes[1].appendChild(clockBox);
			//alert(document.childNodes[1]);
			clock.paper=document.getElementById("cellBox");
			clock.run();
		},
		run:function(){
			var now=new Date;
		//	Asist.log(now.getTime());
			var H=now.getHours(),h0=H/10 >>0,h1=H%10,
				M=now.getMinutes(),m0=M/10 >>0,m1=M%10,
				S=now.getSeconds(),s0=S/10 >>0,s1=S%10,
				times=[h0,h1,m0,m1,s0,s1];
				
			for(var j=0;j<NUM.length;j++){
				var row=NUM[j],str="";
				for(var i=0;i<times.length;i++){
					if(i==2 || i==4){
						if(j==2 || j==4)
							str+="# ";
						else
							str+="  "
					}
					str+=row.substr(times[i]*4,4);
					str+=" "
				}
				clock.cells[j]=str.split('');
			}
			var temp=clock.cells,temp1=clock.cells1;
			if(clock.isFirst){
				var tempIn=[], tempOut=[];
				for(var i=0;i<temp.length;i++){
					var the=temp[i];
					for(var k=0;k<the.length;k++){
						if(the[k]=="#" && temp1[i][k]==" "){
							tempIn.push([i,k]);
						}
						else if(the[k]==" " && temp1[i][k]=="#"){
							tempOut.push([i,k]);
						}
					}
				}
				clock.inCell=tempIn;
				clock.outCell=tempOut;
			}
			
			clock.cells1=clock.cells.slice(0);
			clock.draw();
			var end=new Date;
			//Asist.log(clock.paper.getElementsByTagName("div").length);
			setTimeout(clock.run,1000);
		},
		draw:function(){
			if(clock.isFirst){
				var inCel=clock.inCell,outCel=clock.outCell;

					for(var n=0;n<outCel.length;n++){
						var outC=document.getElementById("cell"+outCel[n][0]+"_"+outCel[n][1]);
					//	$(outC).animate({opacity:0,top:300},function(){
						//	clock.paper.removeChild(outC);
						//});
						
						//Asist.log(outC.getAttribute('class'));
						(function(elem,pos){
							var c=new Dot(elem,pos);
							c.anim();
						})(outC,{l:outCel[n][0],t:outCel[n][1]})
						
					}

					for(var m=0;m<inCel.length;m++){
						var inC=clock.makeCell("#030",{x:inCel[m][0],y:inCel[m][1]});
						//var inC_=$(inC);
						//$(clock.paper).append(inC_);
						//inC_.animate({opacity:1,});
						clock.paper.appendChild(inC);
					}

					
				
					
				
			
			}else{
			
				var cels=clock.cells;
					for(var i=0;i<cels.length;i++){
						var cel=cels[i];
						for(var k=0;k<cel.length;k++){
							//var colo= k<10
							var color=cel[k]=="#"?"#030":"#fff";
							clock.paper.appendChild(clock.makeCell(color,{x:i,y:k}));
						}
					}
					clock.isFirst=true;
				}
		},
		makeCell:function(color,pos){
			if(document.getElementById("cell"+pos.x+"_"+pos.y)) 
				clock.paper.removeChild(document.getElementById("cell"+pos.x+"_"+pos.y));
			var node=document.createElement("div");
			node.setAttribute("class","cell");
			node.setAttribute("id","cell"+pos.x+"_"+pos.y);
			node.style.position="absolute";
			node.style.backgroundColor=color;
			node.style.top=pos.x*5+"px";
			node.style.left=pos.y*5+"px";
			node.style.width=3+"px";
			node.style.height=3+"px";
			return node;
		}
	}
	
	function Dot (elem, pos) {
        var g = Math.random();
            
        this.o = elem;
        this.vx = this.vy = this.dx = this.dy = 0;
        this.vy = -this.randNum(1, 5);
        this.dir = Math.random() > .5 ? this.randNum(1, 5) : -1*this.randNum(1, 5);
        this.g = g < .1 ? .1 : g;
        this.x = parseInt(elem.style.left);
        this.y = parseInt(elem.style.top);
      //  this.setStyle(color, pos);
    }
    _.extend(Dot.prototype, {
        setStyle: function (c, pos) {
            var sty = this.o.style;
            this.o.className = c;
            sty['width'] = ow + 'px';
            sty['height'] = ow + 'px';
            sty['position'] = 'absolute';
            sty['left'] = pos.l + 'px';
            sty['top'] = pos.t + 'px';
        },
        randNum: function (f, t) {
            return Math.round(Math.random()*(t-f)) + f;
        },
        move: function () {
            this.x += this.dx;
            this.y += this.dy;
            this.vx += this.dx;
            this.vy += this.dy;
            
            this.o.style['left'] = this.x + 'px';
            this.o.style['top'] = this.y + 'px';
            
        },
        boundary: function () {
            //gravity
            this.vy += this.g;
            this.x += this.dir;
            this.y += this.vy;
            
            if (this.x < 0  || this.x > clock.W) {
                clearInterval(this.st);
				//if(this.o)
				try{
					clock.paper.removeChild(this.o);
				}catch(e){}
            }
            
            if (this.y > clock.H-10 && this.vy > 0) {
                this.vy *= -1;
            }
            
           
        },
       
        anim: function () {
            var _this = this;
            this.st = setInterval(function () {
                _this.move();
                _this.boundary();
            }, 16)
        }
    })

	



