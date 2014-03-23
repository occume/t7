function loadindex()
{
	var tpl='<li><a href="$htmlpath" class="amarqueebox0" title="$titlefull" >$title</a></li>'
	var html="";
	//首页新闻信息
	var indexnews = newslist["list_index"];
	if (indexnews==null||indexnews.length==0)
	{
		html="<li>暂无数据</li>";
	}else{
		for(var i=0;i<indexnews.length;i++) {
			var tmp = tpl;
				tmp = tmp.replace("$htmlpath", "http://fy.2211.com"+indexnews[i].htmlpath);
				tmp = tmp.replace("$titlefull", indexnews[i].title);
				tmp = tmp.replace("$title", substringFormat(indexnews[i].title,18));
				html = html + tmp+"\n";
		}
	}
	$("#newsTpl").html(html);
}




function substringFormat(str,length)
{

	if (str.length>length)
	{
		return str.substring(0,length)+"...";
	}else
		return str;
	
}