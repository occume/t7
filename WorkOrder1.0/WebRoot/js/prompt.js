window.onload=function(){
	var r = Raphael("holder", 1500, 219), //Raphael(0, 0, "100%", "100%"),
    dashed = {fill: "none", stroke: "#666", "stroke-dasharray": "- "};
	dashed1 = {fill: "#999", stroke: "#999", "stroke-dasharray": "- "};
	//Text
	var now =1;
	var wel=document.getElementById("welcome");
	var holder=document.getElementById("holder");
//	alert(!now);
//	alert(+(now= !now));
	(function () {
      //  r.text(190, 100, "欢迎你").attr({font: '3px "Helvetica Neue", Arial', fill: "none", stroke: "#666", "stroke-dasharray": "-"});
     //   r.text(190, 100, "Yay!").attr({font: '10px "Helvetica Neue", Arial', fill: "none", stroke: "#666", "stroke-dasharray": "-", transform: "t100,0r360s3"});
        var text=wel.innerHTML;
		var el = r.text(700, 20, text).attr({"font-size": 10,fill: "#99cc33","fill-opacity": 1}),
            // elattrs = [{x: 290, "font-size": 30, transform: "r360"}, {x: 190, "font-size": 10, transform: "r0"}],
			
            elattrs = [{x:700,y:50,"font-size": 50,fill: "#99cc33","fill-opacity": 1}, {x:1230,y:37,"font-size": 14,fill: "#fff"}],
            now = 1;
			//r.rect(el.getBBox().x,el.getBBox().y,el.getBBox().width,el.getBBox().height).attr({fill: "#99cc33","fill-opacity": .5});
			
            el.animate(elattrs[0], 1200,"bounce",function(){
            	setTimeout(function(){el.animate(elattrs[1], 3000,"<>",function(){
            		//r.animate({"fill-opacity":1},1000);
            		holder.style.display="none";
            		wel.style.display="block";
            	})},1200)
            });
    })();
}