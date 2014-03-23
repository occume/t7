<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
 <link rel="stylesheet" type="text/css" href="css/dialog.css"/>
 <link rel="stylesheet" type="text/css" href="css/dwr.css"/>
 <script type="text/javascript" src="js/jquery.js"></script>
 <style type="text/css">
 	#fastAlert{}
 	#closeAlert{}
 </style>
 <title>Stop Alert</title> 
</head> 
<body>
<button id="fastAlert">快速报警</button>
<button id="closeAlert">关闭报警</button>

<script type="text/javascript">
   $("#fastAlert").click(function(){
   		$.ajax({
   			url:"/im/alert",
   			method:"post",
   			cache:false
   		});
   });
   
   $("#closeAlert").click(function(){
   		$.ajax({
   			url:"/im/closeAlert",
   			method:"post",
   			cache:false
   		});
   });
</script>

</body>
</html>
