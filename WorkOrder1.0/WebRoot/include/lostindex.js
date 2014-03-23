$(document).ready(function(){				
	$(".nav_a").hover(function(){resetNavbtn("#c1");$(".nav_a").css({"background-position": "0px -35px", "color": "#334562" })});
	$(".nav_b").hover(function(){resetNavbtn("#c2");$(".nav_b").css({"background-position": "0px -105px", "color": "#334562" })});
	$(".nav_c").hover(function(){resetNavbtn("#c3");$(".nav_c").css({"background-position": "0px -105px", "color": "#334562" })});
	$(".nav_d").hover(function(){resetNavbtn("#c4");$(".nav_d").css({"background-position": "0px -175px", "color": "#334562" })});

	$(".pt_a").click(function(){resetLogin("#w1");$("#w1").css({"visibility": "visible"});$(".pt_a").css({"background-position": "0px 0", "font-weight": "bold" })});
	$(".pt_b").click(function(){resetLogin("#w2");$("#w1").css({"visibility": "hidden"});;$(".pt_b").css({"background-position": "-125px -31px", "font-weight": "bold" })});


	$("#bb_nav1").click(function(){
		$("#banner1").animate( { top: 0}, 500 );
		$("#banner2").animate( { top: 290}, 500 );
		$("#banner3").animate( { top: 580}, 500 );
		$("#banner4").animate( { top: 870}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_02.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	});
	$("#bb_nav2").click(function(){
		$("#banner1").animate( { top: -290}, 500 );
		$("#banner2").animate( { top: 0}, 500 );
		$("#banner3").animate( { top: 290}, 500 );
		$("#banner4").animate( { top: 580}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_01.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_02_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	});		
	$("#bb_nav3").click(function(){
		$("#banner1").animate( { top: -580}, 500 );
		$("#banner2").animate( { top: -290}, 500 );
		$("#banner3").animate( { top: 0}, 500 );
		$("#banner4").animate( { top: 290}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_01.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_02.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	});		
	$("#bb_nav4").click(function(){
		$("#banner1").animate( { top: -870}, 500 );
		$("#banner2").animate( { top: -580}, 500 );
		$("#banner3").animate( { top: -290}, 500 );
		$("#banner4").animate( { top: 0}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_01.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_02_110518.gif' />"); 
	});		
	$(".bigbanner").hover(
	  function () {
		clearInterval(iID);
	  },
      function () {
		setTimeout(autoBigBanner,0);
		iID = setInterval(autoBigBanner,8000);
      }
	);
});
var iID = window.setInterval(autoBigBanner,8000);

function autoBigBanner()
{
	if($("#banner1").css("top") == '0px')
	{
		$("#banner1").animate( { top: -290}, 500 );
		$("#banner2").animate( { top: 0}, 500 );
		$("#banner3").animate( { top: 290}, 500 );
		$("#banner4").animate( { top: 580}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_01.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_02_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	}
	else if($("#banner2").css("top") == '0px')
	{
	    $("#banner1").animate( { top: -580}, 500 );
	    $("#banner2").animate( { top: -290}, 500 );
	    $("#banner3").animate( { top: 0}, 500 );
		$("#banner4").animate( { top: 290}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_01.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_02.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	}
	else if($("#banner3").css("top") == '0px')
	{
		$("#banner1").animate( { top: -870}, 500 );
		$("#banner2").animate( { top: -580}, 500 );
		$("#banner3").animate( { top: -290}, 500 );
		$("#banner4").animate( { top: 0}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_01.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_02_110518.gif' />"); 
	}
	else if($("#banner4").css("top") == '0px')
	{
		$("#banner1").animate( { top: 0}, 500 );
		$("#banner2").animate( { top: 290}, 500 );
		$("#banner3").animate( { top: 580}, 500 );
		$("#banner4").animate( { top: 870}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_02.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	}
	else
	{
	    $("#banner1").animate( { top: 0}, 500 );
	    $("#banner2").animate( { top: 290}, 500 );
        $("#banner3").animate( { top: 580}, 500 );
		$("#banner4").animate( { top: 870}, 500 );
		$("#bb_nav1").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-5_02.gif' />"); 
		$("#bb_nav2").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-1_01_20110602.gif' />"); 
		$("#bb_nav3").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-2_01.gif' />"); 
		$("#bb_nav4").html("<img src='https://img.99bill.com/jt/p/i/index/0817/banner-3_01_110518.gif' />"); 
	}
}

function resetNavbtn(str)
{
	$(".nav_a").css({"background-position": "0px 0px", "color": "#5f6e85" });
	$(".nav_b,.nav_c").css({"background-position": "0px -70px", "color": "#5f6e85" });
	$(".nav_d").css({"background-position": "0px -140px", "color": "#5f6e85" });
	$("#c1,#c2,#c3,#c4").hide();
	$(str).show();
}
function resetLogin(str)
{
	$(".pt_a").css({"background-position": "0px -31px","font-weight": "normal"});
	$(".pt_b").css({"background-position": "-125px 0px","font-weight": "normal"});
	$("#w1,#w2").hide();
	$(str).show();
}

//牌照，牌照在第一个，4帧