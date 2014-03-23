$(function(){
	
	addPageBehavior("undeal");
	addPageBehavior("reply");
	addPageBehavior("deling");
	addPageBehavior("deal");
	addPageBehavior("lackinfo");
	
});


function addPageBehavior(id)
{
	var pagelist =$("#"+id);
	var first = pagelist.find(".buttonfirst>a");
	var front = pagelist.find(".buttonfront>a");
	var next = pagelist.find(".buttonnext>a");
	var last = pagelist.find(".buttonend>a");
	var ddr = pagelist.find(".buttonselect .select40");
	first.live("click",function(){firstpage(id);});
	front.live("click",function(){prevpage(id);});
	next.live("click",function(){nextpage(id);});
	last.live("click",function(){lastpage(id);});
	//ddr.live("change",function(){changepage(id);});
	
}

function firstpage(id)
{
	var currentPage=$(".currentpage","#"+id).val()
	if (parseInt(currentPage)==1)
	{
		alert("已经是第一页了");
		return false;
	}
	$(".currentpage","#"+id).val(1);

	reloadData(id);
	cddr(id);
	}

function prevpage(id)
{
	var currentPage=$(".currentpage","#"+id).val();
	var totalPage=  $(".totalpage","#"+id).val();
	if (parseInt(currentPage)<=1)
	{
		alert("已经是第一页了");
		return false;
	}
	
	$(".currentpage","#"+id).val(parseInt(currentPage)-1);
	reloadData(id);
	cddr(id);
}


function nextpage(id)
{
	
	var currentPage=$(".currentpage","#"+id).val();
	var totalPage=  $(".totalpage","#"+id).val();
	
	if (parseInt(currentPage)>=parseInt(totalPage))
	{
		alert("已经是最后一页了");
		return false;
	}
	
	$(".currentpage","#"+id).val(parseInt(currentPage)+1);
	
	reloadData(id);
	cddr(id);
}

function lastpage(id)
{
	var totalPage=  $(".totalpage","#"+id).val();
	var currentPage=$(".currentpage","#"+id).val();
	if (currentPage>=totalPage)
	{
		alert("已经是最后一页了");
		return false;
	}
	
	var totalPage=  $(".totalpage","#"+id).val();
	var currentPage=$(".currentpage","#"+id).val(totalPage);
	reloadData(id);
	cddr(id);
	
}

function changepage(id)
{
	var pagelist =$("#"+id);
	var currentPage = pagelist.find(".buttonselect .select40").val();
	$(".currentpage","#"+id).val(currentPage);
	reloadData(id);
}

function cddr(id)
{
	var pagelist =$("#"+id);
	var ddr = pagelist.find(".buttonselect .select40");
	var currentPage=$(".currentpage","#"+id).val();
	ddr.val(currentPage);
}

function reloadData(id)
{
	$(".list","#"+id).html("<div style=\"height:200px;text-algin:center;width:100%;\"><img src=\"/images/loading.gif?v=2\" style=\"vertical-align:middle;margin-left:300;margin-right:auto;margin-top:40px; \" /></div>");
	var currentPage=$(".currentpage","#"+id).val();
	var url="/selfService/userwolist_ajax";
	$.ajax({
		 type: 'get',  
		 url: url+"?currentPage="+currentPage+"&states="+id,
		 cache:false,
		 success:function(result){
			if(result!=null){
				$(".list","#"+id).html(result);
			}else{
				$(".list","#"+id).html("<div>无记录</div>");
			}	
		}
	});
}