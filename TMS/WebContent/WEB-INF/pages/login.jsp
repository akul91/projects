<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Training Management System</title>
<meta charset="utf-8">
<link rel="stylesheet" href="../css/demo.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="../css/style.css" type="text/css"
	media="all" />
<link rel="stylesheet" href="sexybuttons.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/cuf`on-yui.js"></script>
<script type="text/javascript" src="../js/Humanst521_BT_400.font.js"></script>
<script type="text/javascript" src="../js/Humanst521_Lt_BT_400.font.js"></script>
<script type="text/javascript" src="../js/roundabout.js"></script>
<script type="text/javascript" src="../js/roundabout_shapes.js"></script>
<script type="text/javascript" src="../js/gallery_init.js"></script>
<script type="text/javascript" src="../js/cufon-replace.js"></script>
<script>
function user()
{	
	  if(isNaN(document.getElementById("userId").value))
	 document.getElementById("spanpass").innerHTML="Please enter employeeID i.e. digits";			 
	  else
	  	 document.getElementById("spanpass").innerHTML="";			 
}

  function register()
  {
	  window.location="cregistration.htm";
	  
  }
 function login()
  {
	 if(isNaN(document.getElementById("empId").value))
			 {
		 document.getElementById("spanpass").innerHTML="Please enter employeeID i.e. digits";
				 
		 return;
			 }
	 var str=window.parseInt(document.getElementById("empId").value);
		var pass=document.getElementById("password").value;
		 var send="clogin.htm?userId="+str+"&password="+pass;
		var xmlhttp;

	if (window.XMLHttpRequest)
	  {
		
		xmlhttp=new XMLHttpRequest();
	  }
	else
	  {
		
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
			
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 var status=xmlhttp.responseText;
		 	
			if(status.indexOf("loggedInEmployee")!=-1)
				{
				 window.location="cloggedInEmployee.htm";
							
				}
			if(status.indexOf("loggedInTA")!=-1)
			{
			 window.location="cloggedInTA.htm";
						
			}
			if(status.indexOf("loggedInPM")!=-1)
			{
			 window.location="cloggedInPM.htm";
						
			}
		
			if(status.indexOf("error")!=-1)
			  {
			 document.getElementById("spanpass").innerHTML="UserId or Password is incorrect";
			  }
			
	    }
		    
	  }
	xmlhttp.open("GET",send,true);
	xmlhttp.send();
  }
 function forgot_password()
 {
	  var id=window.prompt("Enter your Employee Id");
if(isNaN(id))
	{
	alert("Please give digits");
	forgot_password();
	}
else
	{
	
	var employeeId=window.parseInt(id);
	  window.location="cforgotPwdQuestion.htm?employeeId="+employeeId;
	}
 
 }
  </script>

<script> 
$(document).ready(function(){
	 $("#showlogin").hide();
  $("#flip").click(function(){
    $("#showlogin").slideToggle("slow");
  });
});
</script>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</HEAD>
<BODY onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<!-- header -->
	<header>
		<div class="container">
			
    	<h1><a href="../index.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Training Management System</a></h1>
      <nav>
				<ul>
					<li><a href="../index.jsp" class="current">Home</a></li>
					<li><a href="../technology.html">Gist</a></li>
					<li><a href="../about.html">About</a></li>
					<li><a href="../contacts.html">Contact</a></li>

				</ul>
			</nav>
		</div>
	</header>
	<!-- #gallery -->
	<section id="gallery">
		<div class="container">
			
		<ul id="myRoundabout">
			<table width=100% >
				<tr>
					<td width=75% cellpadding=15px>
						<center>
							<b><h2>
									Welcome to Training Management System
								</h2></b>
								This application allows an effective and efficient way to manage the training system. An employee can register for the first time and login to know about all the trainings going on in the organization. These trainings can be technical and also non-technical.
						</center>
					</td>
					<td>
					<br/>
					<c:choose>
				<c:when test="${sessionScope.userId ==null}">
									<table>
										<tr>
											<td width=30%>Username:</td>
											<td><input type="text" id="empId"></td>
										</tr>
										<tr>
											<td>Password:</td>
											<td><input type="password" id="password"></td>
										</tr>
										
									</table>
									<font color="red"><span id="spanpass"></span> </font>
									<div align="center">
										<input type="button" onclick="login()" value="Sign In">
									</div>

								<table>	
						<tr>
							<td width=25%></td>
							<td><img src="../images/forgotpass.png" width=95px
								alt="Forgot Password" title="Forgot password click here"
								onclick="forgot_password()"> <img
								src="../images/register.png" alt="Sign Up" width=95px
								title="Sign Up for New user" onclick="register()"></td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
								<h6>
									Hi ${employeeDetails.employeeName}<br /> You are already logged
									in.<br />
									<c:choose>
										<c:when test="${employeeDetails.employeeCategory =='e'}">
											<a href="cloggedInEmployee.htm">Click here to go back to
												your account page</a>
										</c:when>
										<c:when test="${employeeDetails.employeeCategory =='p'}">
											<a href="cloggedInPM.htm">Click here to go back to your
												account page</a>
										</c:when>
										<c:otherwise>
											<a href="cloggedInTA.htm">Click here to go back to your
												account page</a>
										</c:otherwise>
									</c:choose>
								</h6>
							</c:otherwise>
						</c:choose></td>
			</table>
			
			<li><img src="../images/front.jpg" alt=""></li>
			<li><img src="../images/back2.jpg" alt=""></li>
			<li><img src="../images/slide6.jpg" alt=""></li>
			<li><img src="../images/06.jpg" alt=""></li>
			<li><img src="../images/07.jpg" alt=""></li>
		</ul>
		</div>
	</section>
	<!-- /#gallery -->
	<div class="main-box">
		
	</div>
	<!-- footer -->
	<footer>
		<div class="container">
			<div class="wrapper">
				<div align="center">Copyright - 2013</div>
			</div>
		</div>
	</footer>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>