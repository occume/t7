document.write("<div id=\"userleftmenu\">");
document.write("<div class=\"userleftmenutop\"></div>");

/*增加内容*/
document.write("<div class=\"memberlogin\"><a href=\"/selfService/login.jsp\" style=\"height:33px;display:block;width:180px;\"></a></div>");
/*增加内容  end*/

document.write("<div id=\"PARENT\">");
document.write("<h4 class=\"top\"><a href=\"/selfServiceMain.jsp\">客服中心首页</a></h4>");
document.write("<ul id=\"nav\">");
document.write("<li><a href=\"#\"  onclick=\"DoMenu('ChildMenu1')\">游戏问题</a>");
document.write(" <ul id=\"ChildMenu1\" class=\"collapsed\">");
document.write(" <li><a href=\"/selfService/serverFailure.jsp?classification_second=4\">服务器故障</a></li>");
document.write(" <li><a href=\"/selfService/loseItems.jsp?classification_second=5\">物品丢失</a></li>");
document.write(" <li><a href=\"/selfService/roleRecovery.jsp?classification_second=6\">角色恢复</a></li>");
document.write(" <li><a href=\"/selfService/roleOfAbnormal.jsp?classification_second=7\">角色异常</a></li>");
document.write(" <li><a href=\"/selfService/gameRunning.jsp?classification_second=8\">游戏运行</a></li>");
document.write(" <li><a href=\"/selfService/rechargeProblem.jsp?classification_second=10\">充值问题</a></li>");
document.write(" <li><a href=\"/selfService/plugAbuse.jsp?classification_second=11\">违规举报</a></li>");
document.write(" <li><a href=\"/selfService/advisory.jsp?classification_second=14\">咨询问题</a></li>");
document.write(" <li><a href=\"/selfService/others.jsp?classification_second=12\">其它</a></li>");
document.write(" </ul>");

document.write("</li>");
document.write("<li><a href=\"#\" onclick=\"DoMenu('ChildMenu2')\">账号问题</a>");
document.write(" <ul id=\"ChildMenu2\" class=\"collapsed\">");
document.write(" <li><a href=\"/selfService/losePwd.jsp\">找回账号</a></li>");
document.write(" <li><a href=\"/selfService/userlockIndex.jsp\">账号被封</a></li>");
document.write(" <li><a href=\"/selfService/accountSecurity.jsp\">账号安全</a></li>");

document.write(" </ul>");
document.write("</li>");


document.write("<li><a href=\"#\" onclick=\"DoMenu('ChildMenu3')\">意见 & 游戏BUG</a>");
document.write(" <ul id=\"ChildMenu3\" class=\"collapsed\">");
document.write(" <li><a href=\"#\">意见建议</a></li>");
document.write(" <li><a href=\"/selfService/gameBug.jsp?classification_second=9\">游戏BUG</a></li>");

document.write(" </ul>");
document.write("</li>");


document.write("</ul>");
//document.write("<h4 class=\"bottom\"><a href=\"/selfService/suggestion.jsp?classification_second=13\">意见建议</a></h4>");



document.write("<h4 class=\"bottom2\"><a href=\"/selfService/userWOList\" ><img src=\"../images/star.gif\" width=\"15px\" height=\"15px\" /> 提问记录</a></h4>");


document.write("</div>");

document.write("                <div id=\"leftService\">");
document.write("                <div class=\"title\"></div>");
document.write("                <div class=\"subtitle subtitle_1\">客服邮箱</div>");
document.write("                <div class=\"subitem\">xxxx@2211.com</div>");
document.write("                <div class=\"subtitle subtitle_2\">官方论坛</div>");
document.write("                <div class=\"subitem\">bbs.2211.com</div>");
document.write("                <div class=\"subtitle subtitle_3\">客服电话</div>");
document.write("                <div class=\"subitem\">111-222-3333 </div>");
document.write("                </div>");



document.write("</div>");


//javascript



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
