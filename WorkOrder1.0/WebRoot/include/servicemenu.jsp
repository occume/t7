<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<% 
	String url =request.getRequestURI();
	//System.out.println("-----currentpage:"+url+"-------------");
	String Rootselect="";
	String SubSelect="";
	
	//先这样，将来改枚举里去
	
	if (url.toLowerCase().startsWith(("/selfServiceMain.jsp").toLowerCase()))
	{
		Rootselect="0";
	}
	else if (url.toLowerCase().startsWith(("/selfService/serverFailure.jsp").toLowerCase())
			)//如果以后有带参数用全部改成这种方式
	{
		Rootselect="1";
		SubSelect="1_3";
	//}else if (url.toLowerCase().startsWith(("/selfService/loseItems.jsp").toLowerCase())
			//)//如果以后有带参数用全部改成这种方式
	//{
	//	Rootselect="1";
		//SubSelect="1_2";
	}else if (url.toLowerCase().startsWith(("/selfService/roleRecovery.jsp").toLowerCase())
			)//如果以后有带参数用全部改成这种方式
	{
		Rootselect="1";
		SubSelect="1_4";
	}else if (url.toLowerCase().startsWith(("/selfService/roleOfAbnormal.jsp").toLowerCase())
			)//如果以后有带参数用全部改成这种方式
	{
		Rootselect="1";
		SubSelect="1_2";
	}else if (url.toLowerCase().startsWith(("/selfService/gameRunning.jsp").toLowerCase())
			)//如果以后有带参数用全部改成这种方式
	{
		Rootselect="1";
		SubSelect="1_1";
	}
	//else if (url.toLowerCase().startsWith(("/selfService/rechargeProblem.jsp").toLowerCase())
	//)//如果以后有带参数用全部改成这种方式
//	{
	//Rootselect="1";
	//SubSelect="1_7";
	//}
	//else if (url.toLowerCase().startsWith(("/selfService/plugAbuse.jsp").toLowerCase())
//	)//如果以后有带参数用全部改成这种方式
	//{
	//Rootselect="1";
//	SubSelect="1_8";
	//}
	else if (url.toLowerCase().startsWith(("/selfService/advisory.jsp").toLowerCase())
	)//如果以后有带参数用全部改成这种方式
	{
	Rootselect="1";
	SubSelect="1_5";
	}
	else if (url.toLowerCase().startsWith(("/selfService/others.jsp").toLowerCase())
	)//如果以后有带参数用全部改成这种方式
	{
	Rootselect="1";
	SubSelect="1_6";
	}
	else if (url.toLowerCase().startsWith(("/selfService/losepasswdMenu.jsp").toLowerCase())
	)//如果以后有带参数用全部改成这种方式
	{
	Rootselect="2";
	SubSelect="2_1";
	}
	else if (url.toLowerCase().startsWith(("/selfService/userlock").toLowerCase())||
			url.toLowerCase().startsWith(("/selfService/accountlock.jsp").toLowerCase())
	)//如果以后有带参数用全部改成这种方式
	{
	Rootselect="2";
	SubSelect="2_2";
	}
	else if (url.toLowerCase().startsWith(("/selfService/accountSecurity.jsp").toLowerCase())
	)//如果以后有带参数用全部改成这种方式
	{
	Rootselect="2";
	SubSelect="2_3";
	}
	else if(url.toLowerCase().startsWith(("/selfService/loseItems.jsp").toLowerCase())){
		Rootselect="3";
		SubSelect="3_1";
	}
	else if(url.toLowerCase().startsWith(("/selfService/equip.jsp").toLowerCase())){
		Rootselect="3";
		SubSelect="3_2";
	}
	else if(url.toLowerCase().startsWith(("/selfService/process.jsp").toLowerCase())){
		Rootselect="3";
		SubSelect="3_3";
	}
	else if(url.toLowerCase().startsWith(("/selfService/gameBug.jsp").toLowerCase())){
		Rootselect="4";
		SubSelect="4_2";
	}
	else if(url.toLowerCase().startsWith(("/selfService/suggestion.jsp").toLowerCase())){
		Rootselect="4";
		SubSelect="4_1";
	}else if(url.toLowerCase().startsWith(("/selfService/faqSelfList.jsp").toLowerCase())){
		Rootselect="5";
	}else if(url.toLowerCase().startsWith(("/selfService/uniqueSelfFaq.jsp").toLowerCase())){
		Rootselect="5";
	}
	else 
	{
		Rootselect="-1";
		SubSelect="0_0";
	}
//	System.out.println("-----Rootselect:"+Rootselect+"-------------");
	//System.out.println("-----SubSelect:"+SubSelect+"-------------");
%>	

<div id="userleftmenu">
	
	<!--  div class="memberlogin">
		<a href="/selfService/login.jsp"
			style="height: 33px; display: block; width: 180px;"></a>
	</div-->

	<div id="PARENT">
		<h4 class="top">
			<a href="/selfServiceMain.jsp">客服中心首页</a>
		</h4>
		<ul id="nav">
		
		<li>
				<a href="#" onclick="DoMenu('ChildMenu5')">常见问题FAQ</a>
				<ul id="ChildMenu5" class="collapsed">
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=Acount"  class="${(currentTag==1)?'selected':''}">账号问题</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=Acount"  class="${(currentTag==1)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">账号问题</a>
						</s:else>
					</li>
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=GAMEABNOMAL"  class="${(currentTag==2)?'selected':''}">游戏异常</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=GAMEABNOMAL"   class="${(currentTag==2)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">游戏异常</a>
						</s:else>
					</li>
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=GoodsLost"  class="${(currentTag==3)?'selected':''}">物品丢失</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=GoodsLost" class="${(currentTag==3)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">物品丢失</a>
						</s:else>					
					</li>
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=BUGSUGESTTION"  class="${(currentTag==4)?'selected':''}">BUG意见</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=BUGSUGESTTION"  class="${(currentTag==4)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">BUG意见</a>
						</s:else>
					</li>
					<!--  
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=RECHARGEISSUE"  class="${(currentTag==5)?'selected':''}">充值问题</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=RECHARGEISSUE"  class="${(currentTag==5)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">充值问题</a>
						</s:else>
					</li>
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=ROLEDATA"  class="${(currentTag==6)?'selected':''}">角色数据异常</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=ROLEDATA"  class="${(currentTag==6)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">角色数据异常</a>
						</s:else>
					</li>
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=SERVERISSUE"  class="${(currentTag==7)?'selected':''}">服务器问题</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=SERVERISSUE"  class="${(currentTag==7)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">服务器问题</a>
						</s:else>
					</li>
					-->
					<li>
						<s:if test="#session.member==null">
							<a href="/faq/getUserFaqByType?itype=MORE"  class="${(currentTag==8)?'selected':''}">其它问题</a>
						</s:if>
						<s:else>
							<a href="/faq/getUserFaqByType?itype=MORE"  class="${(currentTag==8)?'selected':''}" target="${(Rootselect==5)?'self':'blank'}">其它问题</a>
						</s:else>
					</li>
				
				</ul>
			</li>
		
			<li>
				<a href="#" onclick="DoMenu('ChildMenu1')">游戏问题</a>
				<ul id="ChildMenu1" class="collapsed">
					<li>
						<a href="/selfService/gameRunning.jsp?classification_second=8" <%=SubSelect=="1_1"?"class=\"selected\"":"" %> >游戏运行异常</a>
					</li>
					<li>
						<a href="/selfService/roleOfAbnormal.jsp?classification_second=7" <%=SubSelect=="1_2"?"class=\"selected\"":"" %>>角色数据异常</a>
					</li>
					<li>
						<a href="/selfService/serverFailure.jsp?classification_second=4" <%=SubSelect=="1_3"?"class=\"selected\"":"" %>>服务器故障</a>
					</li>
					<!--  
					<li>
						<a href="/selfService/roleRecovery.jsp?classification_second=6" <%=SubSelect=="1_4"?"class=\"selected\"":"" %>>角色恢复</a>
					</li>
					-->
					<li>
						<a href="/selfService/advisory.jsp?classification_second=14" <%=SubSelect=="1_5"?"class=\"selected\"":"" %>>游戏咨询</a>
					</li>
					
					<li>
						<a href="/selfService/others.jsp?classification_second=12" <%=SubSelect=="1_6"?"class=\"selected\"":"" %>>其它</a>
					</li>
				</ul>
			</li>

			<li>
				<a href="#" onclick="DoMenu('ChildMenu2')">账号问题</a>
				<ul id="ChildMenu2" class="collapsed">
					<li>
						<a href="/selfService/losepasswdMenu.jsp" <%=SubSelect=="2_1"?"class=\"selected\"":"" %> >找回账号</a>
					</li>
					<li>
						<a href="/selfService/userlockIndex.jsp"  <%=SubSelect=="2_2"?"class=\"selected\"":"" %> >账号被封</a>
					</li>
					<li>
						<a href="/selfService/accountSecurity.jsp"  <%=SubSelect=="2_3"?"class=\"selected\"":"" %> >账号安全</a>
					</li>

				</ul>
			</li>
			
			<li>
					<a href="#" onclick="DoMenu('ChildMenu3')">物品丢失</a>
						<ul id="ChildMenu3" class="collapsed">
							<li>
								<a href="/selfService/loseItems.jsp?classification_second=5" <%=SubSelect=="3_1"?"class=\"selected\"":"" %>>申报丢失</a>
							</li>
							<li>
								<a href="/selfService/equip.jsp" <%=SubSelect=="3_2"?"class=\"selected\"":"" %> >申请找回细则</a>
							</li>
							<li>
								<a href="/selfService/process.jsp"  <%=SubSelect=="3_3"?"class=\"selected\"":"" %> >申请流程图</a>
							</li>
					</ul>
			</li>
			
			<li>
					<a href="#" onclick="DoMenu('ChildMenu4')">BUG意见</a>
						<ul id="ChildMenu4" class="collapsed">
							<li>
								<a href="/selfService/suggestion.jsp?classification_second=13" <%=SubSelect=="4_1"?"class=\"selected\"":"" %> >意见建议</a>
							</li>
							<li>
								<a href="/selfService/gameBug.jsp?classification_second=9"  <%=SubSelect=="4_2"?"class=\"selected\"":"" %> >游戏BUG</a>
							</li>
					</ul>
			</li>
		
		
		</ul>
		<!--  h4 class="bottom"><a target="blank" href="/selfService/equip.jsp">物品被盗找回申请</a></h4 -->
		<h4 class="bottom"><a  href="/selfService/rechargeProblem.jsp?classification_second=10">充值问题</a></h4>
		<h4 class="bottom"><a  href="/selfService/plugAbuse.jsp?classification_second=11">违规举报</a></h4>
		<h4 class="bottom"><a target="blank" href="/selfService/gm.jsp">游戏管理条例</a></h4>
		
		<h4 class="bottom">
			
			<a href="/selfService/userWOList"><img src="../images/sevicejl.gif" width="15px" height="15px" />我的提问记录</a>
		</h4>
	</div>

	<div id="leftService">
		<div class="title"></div>
		<div class="subtitle subtitle_1" style="font-weight:bold;">
			客服邮箱
		</div>
		<div class="subitem">
			<a  style="color:#ffffff" href="mailto:fykf@2211.com">fykf@2211.com</a>
		</div>
		<div class="subtitle subtitle_2" style="font-weight:bold;">
			官方论坛
		</div>
		<div class="subitem">
			<a  style="color:#ffffff" href="http://bbs.2211.com" target="blank">http://bbs.2211.com</a>
		</div>
		
	</div>
</div>




<script>
var LastLeftID = "";
function menuFix() {
 var obj = document.getElementById("nav").getElementsByTagName("li");
 
 for (var i=0; i<obj.length; i++) {
  obj[i].onmouseover=function() {
   this.className+=(this.className.length>0? " ": "") + "sfhover";
  }
  obj[i].onMouseDown=function() {
   this.className+=(this.className.length>0? " ": "") + "sfhover";
  }
  obj[i].onMouseUp=function() {
   this.className+=(this.className.length>0? " ": "") + "sfhover";
  }
  obj[i].onmouseout=function() {
   this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
  }
 }
}
function DoMenu(emid)
{
 var obj = document.getElementById(emid); 
 obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
 if((LastLeftID!="")&&(emid!=LastLeftID)) //关闭上一个Menu
 {
  document.getElementById(LastLeftID).className = "collapsed";
 }
 LastLeftID = emid;
}
function GetMenuID()
{
 var MenuID="";
 var _paramStr = new String(window.location.href);
 var _sharpPos = _paramStr.indexOf("#");
 
 if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1)
 {
  _paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length);
 }
 else
 {
  _paramStr = "";
 }
 
 if (_paramStr.length > 0)
 {
  var _paramArr = _paramStr.split("&");
  if (_paramArr.length>0)
  {
   var _paramKeyVal = _paramArr[0].split("=");
   if (_paramKeyVal.length>0)
   {
    MenuID = _paramKeyVal[1];
   }
  }
  /*
  if (_paramArr.length>0)
  {
   var _arr = new Array(_paramArr.length);
  }
  
  //取所有#后面的，菜单只需用到Menu
  //for (var i = 0; i < _paramArr.length; i++)
  {
   var _paramKeyVal = _paramArr[i].split('=');
   
   if (_paramKeyVal.length>0)
   {
    _arr[_paramKeyVal[0]] = _paramKeyVal[1];
   }  
  }
  */
 }
 
 if(MenuID!="")
 {
  DoMenu(MenuID)
 }
}
GetMenuID(); //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果
menuFix();
<%
if (Rootselect=="1"||Rootselect=="2" || Rootselect=="3"||Rootselect=="4"||Rootselect=="5")
{%>
DoMenu("ChildMenu<%=Rootselect%>");
<%	}
%>

</script>