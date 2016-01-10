<!DOCTYPE html>
<html lang="en">
<head>
<title>Registration</title>
<meta charset="utf-8">
<link rel="stylesheet" href="../css/demo.css" type="text/css"
	media="all">
<link rel="stylesheet" href="../css/style.css" type="text/css"
	media="all">
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/cufon-yui.js"></script>
<script type="text/javascript" src="../js/Humanst521_BT_400.font.js"></script>
<script type="text/javascript" src="../js/Humanst521_Lt_BT_400.font.js"></script>
<script type="text/javascript" src="../js/roundabout.js"></script>
<script type="text/javascript" src="../js/roundabout_shapes.js"></script>
<script type="text/javascript" src="../js/gallery_init.js"></script>
<script type="text/javascript" src="../js/cufon-replace.js"></script>

<script>
function register() {
	var employeeId = document.getElementById("employeeId").value;
	if (employeeId.length == 0) {
		document.getElementById("spanEmployeeId").innerHTML = "Employee Id should not be left empty";
		return;
	}
	else{document.getElementById("spanEmployeeId").innerHTML = "";}
	
	var password = document.getElementById("password").value;
	if (password.length == 0) {

		document.getElementById("spanPassword").innerHTML = "password should not be left empty";
		return;
	}
	else{document.getElementById("spanPassword").innerHTML = "";}
	var cpassword = document.getElementById("cpassword").value;
	if (cpassword.length == 0) {

		document.getElementById("spanCPassword").innerHTML = "confirm password should not be left empty";
		return;
	}
	else{document.getElementById("spanCPassword").innerHTML = "";}
	var employeeName = document.getElementById("employeeName").value;
	if (employeeName.length == 0) {
		document.getElementById("spanEmployeeName").innerHTML = "Employee Name should not be left empty";
		return;
	}
	else{document.getElementById("spanEmployeeName").innerHTML = "";}
	var employeeEmail = document.getElementById("employeeEmail").value;
	if (employeeEmail.length == 0) {
		document.getElementById("spanEmployeeEmail").innerHTML = "Employee Email should not be left empty";
		return;
	}
	else{document.getElementById("spanEmployeeEmail").innerHTML = "";}

	var employeeNumber = document.getElementById("employeeNumber").value;
	if (employeeNumber.length == 0) {
		document.getElementById("spanMobileNumber").innerHTML = "Employee Mobile Number should not be left empty";
		return;
	}
	else{document.getElementById("spanMobileNumber").innerHTML = "";}

	var employeeDesignation = document
			.getElementById("employeeDesignation").value;
	if (employeeDesignation.length == 0) {
		document.getElementById("spanEmployeeDesignation").innerHTML = "Designation should not be left empty";
		return;
	}
	else{document.getElementById("spanEmployeeDesignation").innerHTML = "";}
	var Tier = document.getElementById("Tier").value;
	if (Tier == 0) {
		document.getElementById("spanTier").innerHTML = "Tier should not be left empty";
		return;
	}
	else{document.getElementById("spanTier").innerHTML = "";}
	/* var projectManagerId = document.getElementById("projectManagerId").value;
	if (projectManagerId.length == 0) {
		document.getElementById("spanProjectMangaerId").innerHTML = "Project Manager Id should not be left empty";
		return;
	}
	else{document.getElementById("spanProjectMangaerId").innerHTML = "";}
*/
var security = document.getElementById("security").value;
if (security == 0) {
	document.getElementById("spanSecurity").innerHTML = "Security should not be left empty";
	return;
}
else{
	document.getElementById("spanSecurity").innerHTML = "";
}

var securityAnswer=document.getElementById("answer").value;
if(securityAnswer.length==0)
	{
	document.getElementById("securityAnswer").innerHTML = "Security Answer should not be left empty";
	
	return;
	}
else
	{
	document.getElementById("securityAnswer").innerHTML = "";

	}

if(!userId())
 {
 	 return false;
 }
	       if(!mobile())
   {
	
 	  return false;
   }
	       if(!validateMail())
  {
  return false;
  
  }
if(!validatePassword())
  {
  return false;
  }
	if(!validateCPassword())
		{
		return false;
		  
		}
	var strings=category();
	if(strings.indexOf("o")!=-1)
		{
		document.getElementById("spanCategory").innerHTML = "Employee Category should be selected";

		return false;
		
		}
	else{
		
		document.getElementById("spanCategory").innerHTML = "";
	}
var projectManagerId="0";
if(strings.indexOf("e")!=-1)
{
projectManagerId = document.getElementById("projectManagerId").value;
}
else
{
}

	var str = "cregister.htm?employeeId="+employeeId+"&password=" + password + "&employeeName="
			+ employeeName + "&employeeEmail=" + employeeEmail
			+ "&employeeNumber=" + employeeNumber + "&employeeDesignation="
			+ employeeDesignation + "&Tier=" + Tier + "&projectManagerId="
			+ projectManagerId + "&category=" + strings+"&security="+security+"&securityAnswer="+securityAnswer;

	registeringInDatabase(str);
	

}
function registeringInDatabase(str) {
	
	if (window.XMLHttpRequest) {

		xmlhttp = new XMLHttpRequest();
	} else {

		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var check=xmlhttp.responseText;
			if(check==1)
				{
				window.location="cRegistrationSuccess.htm";
				}
			else
				{
				document.getElementById("spanEmployeeId").innerHTML="Employee with this Id already exists";
				}
	}

	};
	xmlhttp.open("GET",str, true);
	xmlhttp.send();

}
function category()
{
var category = 'not Selected';
var inputs = document.getElementsByName("category");
for (var i = 0; i < inputs.length; i++) {
  if (inputs[i].checked) {
    category = inputs[i].value;
    break ;
  }
}

if(category.indexOf('not')!=-1)
{	return 'o';
}
else
{
return category;
}

}

function userName()
{
	var employeeName = document.getElementById("employeeName").value;
	
	 if(/[0-9]/.test(employeeName)){
    		document.getElementById("spanEmployeeName").innerHTML ="User name should not contain digits";
        return false;
    }
     else
    	 {
    	 document.getElementById("spanEmployeeName").innerHTML ="";
    	 }
    if(/[!@#$%^&*()]/.test(employeeName)){
    	document.getElementById("spanEmployeeName").innerHTML ="Username should not contain  special character";
        return false;
    }
    else
    	{
    	document.getElementById("spanEmployeeName").innerHTML ="";
    	
    	}
	
}
function designation()
{
	var employeeDesignation = document.getElementById("employeeDesignation").value;
	
	 if(/[0-9]/.test(employeeDesignation)){
    		document.getElementById("spanEmployeeDesignation").innerHTML ="Employee Designation should not contain digits";
        return false;
    }
     else
    	 {
    	 document.getElementById("spanEmployeeDesignation").innerHTML ="";
    	 }
    if(/[!@#$%^&*()]/.test(employeeDesignation)){
    	document.getElementById("spanEmployeeDesignation").innerHTML ="Employee Designation should not contain  special character";
        return false;
    }
    else
    	{
    	document.getElementById("spanEmployeeDesignation").innerHTML ="";
    	
    	}
	
}
function mobile()
{
	
	var str=document.getElementById("employeeNumber").value;
	if(isNaN(str))
		{
		
		document.getElementById("spanMobileNumber").innerHTML = "Employee Mobile Number should be digits";
return false;
		}
	else
	{
	document.getElementById("spanMobileNumber").innerHTML = "";			

	}if(str.length!=10)
	{
		document.getElementById("spanMobileNumber").innerHTML = "Employee Mobile Number should be of 10 digits";			
	
	return false;
	}
	else
		{
		document.getElementById("spanMobileNumber").innerHTML = "";			

		}
	return true;
}
function manager()
{
	var str = document.getElementById("projectManagerId").value;
	if(isNaN(str))
		{
		document.getElementById("spanProjectMangaerId").innerHTML = "Project Manager Id should be digits";
		return false;
		}
	else
		{
		document.getElementById("spanProjectMangaerId").innerHTML = "";
		
		}
	if(str.length!=7)
		{
		document.getElementById("spanProjectMangaerId").innerHTML = "Project Manager Id should be of 7 digits";
		return false;
					
		}
	else
		{
		document.getElementById("spanProjectMangaerId").innerHTML = "";
		}
return true;
}

function validateMail()
{
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   var address = document.getElementById("employeeEmail").value;
	   if(reg.test(address) == false) {
	 
		   document.getElementById("spanEmployeeEmail").innerHTML = "invalid email address eg:-username@example.com";
			 return false;
	   }
	   else
		   {
		   document.getElementById("spanEmployeeEmail").innerHTML = "";
		   
		   }

return true;
}

function validatePassword()
{
            
    var password=document.getElementById("password").value;
    var specialletters = /^[0-9a-zA-Z!@#$%^&*()]+$/;  
	    if (password==null || password=="")
      {
    	document.getElementById("spanPassword").innerHTML ="password must be filled out";
      return false;
      }
    if(!(password.length>=8 && password.length<=12))
    {
    	document.getElementById("spanPassword").innerHTML ="password should be min 8 char and max 12 char";
        return false;
    }
    else
    	{
    	document.getElementById("spanPassword").innerHTML ="";
    	}
    
     if(!/[0-9]/.test(password)){
    		document.getElementById("spanPassword").innerHTML ="Password requires atleast one digit";
        return false;
    }
     else
    	 {
    	 document.getElementById("spanPassword").innerHTML ="";
    	 }
    if(!/[!@#$%^&*()]/.test(password)){
    	document.getElementById("spanPassword").innerHTML ="Password requires atleast one special character";
        return false;
    }
    else
    	{
    	document.getElementById("spanPassword").innerHTML ="";
    	
    	}
    return true;
}
function validateCPassword()
{
	var password=document.getElementById("password").value;
	var cpassword = document.getElementById("cpassword").value;
	if(password!=cpassword)
		{
		document.getElementById("spanCPassword").innerHTML = "confirm password should be same as password";
			return false;
		}
	else
		{
		document.getElementById("spanCPassword").innerHTML = "";
		}
	return true;
}
function radio()
{
	

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
		  document.getElementById("managerEmployee").innerHTML =xmlhttp.responseText;
		 
	    
	    }
	  }
	   
	xmlhttp.open("GET","cprojectManager.htm",true);
	xmlhttp.send();
	}
function other()
{
	document.getElementById("managerEmployee").innerHTML ="";
	
}
function userId()
{
	var str = document.getElementById("employeeId").value;
	if(isNaN(str))
		{
		document.getElementById("spanEmployeeId").innerHTML = "Employee Id should be digits";
		return false;
		}
	else
		{
		document.getElementById("spanEmployeeId").innerHTML = "";
		
		}
	if(str.length!=7)
		{
		document.getElementById("spanEmployeeId").innerHTML = "Employee Id should be of 7 digits";
		return false;
					
		}
	else
		{
		document.getElementById("spanEmployeeId").innerHTML = "";
		}
return true;
}
</script>
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<header>
		<div class="container">
			<h1>
				<a href="../index.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Training Management System</a>
			</h1>
			<nav>
				<ul>
					<li><a href="../index.jsp" class="current">Home</a></li>
					<li><a href="../technology.html">Technology</a></li>
					<li><a href="../about.html">About</a></li>
					<li><a href="../contacts.html">Contact</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="main-box">
		<br/>
		<u><h2><center>Registration Form</center></h2></u>
		<div class="container">
			<div class="inside">
				<div class="wrapper">

					<aside>
						<h2>Instructions</h2>
						User Name cannot contain digits and special characters<br>
						Password must be of 8 to 12 character length<br> Password
						must consist of atleast one number <br> Password must consist
						of atleast one special character<br> Mobile Number must be of
						10 digits<br> <font color="red">All Fields are
							mandatory</font>


					</aside>


							<table>
							<tr>
								<td>EmployeeId:</td>
								<td><input type="text" id="employeeId" onchange="userId()"></td>
									<td><font color="red"><h5>
												<span id="spanEmployeeId"></span>
											</h5></font></td>
								</tr>
							
								<tr>
									<td>EmployeeName:</td>
									</font>
									<td><input type="text" id="employeeName"
										onchange="userName()"></td>
									<td><font color="red"><h5>
												<span id="spanEmployeeName"></span>
											</h5></font></td>
								</tr>

								<tr>
									<td>Password:</td>
									<td><input type="password" id="password"
										onchange="validatePassword()"></td>
									<td><font color="red"><h5>
												<span id="spanPassword"></span>
											</h5></font></td>
								</tr>
								<tr>
									<td>Confirm Password:</td>
									<td><input type="password" id="cpassword"
										onchange="validateCPassword()"></td>
									<td><font color="red"><h5>
												<span id="spanCPassword"></span>
											</h5></font></td>
								</tr>


								<tr>
									<td>Employee Email:</td>
									<td><input type="text" id="employeeEmail"
										onchange="validateMail()"></td>
									<td><font color="red"><span id="spanEmployeeEmail"></span></font></td>
								</tr>

								<tr>
									<td>Employee Mobile Number:</td>
									<td><input type="text" id="employeeNumber"
										onchange="mobile()"></td>
									<td><font color="red"><span id="spanMobileNumber"></span></font></td>
								</tr>

								<tr>
									<td>Employee Designation:</td>
									<td><input type="text" id="employeeDesignation"
										onchange="designation()"></td>
									<td><font color="red"><h5>
												<span id="spanEmployeeDesignation"></span>
											</h5></font></td>
								</tr>

								<tr>
									<td>Employee Tier:</td>
									<td><select id="Tier">
											<option value="0">---select---</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
									</select></td>
									<td><font color="red"><h5>
												<span id="spanTier"></span>
											</h5></font></td>
								</tr>
								<tr>

										<td>Select a security question:</td>
										<td><select id="security">
											<option value="0">---select---</option>
											<option value="1">What is the name of your favourite
											teacher?</option>
											<option value="2">Who is the person you hate the most?</option>
											<option value="3">Which place do you like the most?</option>
											<option value="4">Who is your inspiration?</option>
										</select></td>
										<td><font color="red"><h5>
												<span id="spanSecurity"></span>
											</h5></font></td>
									
								</tr>
								<tr>
										<td>Answer</td>
										<td><input type="text" id="answer"></td>
										<td><font color="red">
										<td><font color="red"><h5>
												<span id="securityAnswer"></span>
											</h5></font></td>
									</tr>
								<tr>
									<td>Employee Category:</td>
									<td><input type="radio" id="category" name="category"
										value="e" onclick="radio()">Employee</input><br>
										<span id="managerEmployee"> </span><br> <input
										type="radio" id="category" name="category" value="p"
										onclick="other()">Project Manager</input><br>
										</td>
									<td><font color="red"><h5>
												<span id="spanCategory"></span>
											</h5></font></td>
									
								</tr>
										<tr>
											<td><input type="reset" value="Reset"></td>
											<td><input type="button" value="Register"
												onclick="return register()"></td>
										</tr>
		
									</table>
							
							


				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<footer>
		<div class="container">
			<div class="wrapper">
				<div align="center">Copyright - Virtusa</div>
			</div>
		</div>
	</footer>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>