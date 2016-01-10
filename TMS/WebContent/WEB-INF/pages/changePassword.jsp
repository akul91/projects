<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/sexybuttons.css" type="text/css" />

<script>
	function change() {
	var oldPassword = document.getElementById("oldpassword").value;
	
	if (oldPassword.length == 0) {
			document.getElementById("oldSpanPassword").innerHTML = "password should not be left empty";
			return;
		} else {
			document.getElementById("oldSpanPassword").innerHTML = "";
		}
	var password = document.getElementById("password").value;
		if (password.length == 0) {
			document.getElementById("spanPassword").innerHTML = "password should not be left empty";
			return;
		} else {
			document.getElementById("spanPassword").innerHTML = "";
		}
		
	var cpassword = document.getElementById("cpassword").value;
		if (cpassword.length == 0) {

			document.getElementById("spanCPassword").innerHTML = "confirm password should not be left empty";
			return;
		} else {
			document.getElementById("spanCPassword").innerHTML = "";
		}
		

		if (!validateOldPassword()) {
			return false;
		}
		if (!validatePassword()) {
			return false;
		}
		if (!validateCPassword()) {
			return false;

		}
	
		var str = "changeOldPassword.htm?password="+password+"&oldPassword="+oldPassword;
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
				 document.getElementById("error").innerHTML=xmlhttp.responseText;
			}
		};

		xmlhttp.open("POST", str, true);
		xmlhttp.send();

	}
	
	function validateOldPassword() {

		var password = document.getElementById("oldpassword").value;
		var specialletters = /^[0-9a-zA-Z!@#$%^&*()]+$/;
		if (password == null || password == "") {
			document.getElementById("oldSpanPassword").innerHTML = "password must be filled out";
			return false;
		}
		if (!(password.length >= 8 && password.length <= 12)) {
			document.getElementById("oldSpanPassword").innerHTML = "password should be min 8 char and max 12 char";
			return false;
		} else {
			document.getElementById("oldSpanPassword").innerHTML = "";
		}

		if (!/[0-9]/.test(password)) {
			document.getElementById("oldSpanPassword").innerHTML = "Password requires atleast one digit";
			return false;
		} else {
			document.getElementById("oldSpanPassword").innerHTML = "";
		}
		if (!/[!@#$%^&*()]/.test(password)) {
			document.getElementById("oldSpanPassword").innerHTML = "Password requires atleast one special character";
			return false;
		} else {
			document.getElementById("oldSpanPassword").innerHTML = "";

		}
		return true;
	}
	
	function validatePassword() {

		var password = document.getElementById("password").value;
		var specialletters = /^[0-9a-zA-Z!@#$%^&*()]+$/;
		if (password == null || password == "") {
			document.getElementById("spanPassword").innerHTML = "password must be filled out";
			return false;
		}
		if (!(password.length >= 8 && password.length <= 12)) {
			document.getElementById("spanPassword").innerHTML = "password should be min 8 char and max 12 char";
			return false;
		} else {
			document.getElementById("spanPassword").innerHTML = "";
		}

		if (!/[0-9]/.test(password)) {
			document.getElementById("spanPassword").innerHTML = "Password requires atleast one digit";
			return false;
		} else {
			document.getElementById("spanPassword").innerHTML = "";
		}
		if (!/[!@#$%^&*()]/.test(password)) {
			document.getElementById("spanPassword").innerHTML = "Password requires atleast one special character";
			return false;
		} else {
			document.getElementById("spanPassword").innerHTML = "";

		}
		return true;
	}
	function validateCPassword() {
		var password = document.getElementById("password").value;
		var cpassword = document.getElementById("cpassword").value;
		if (password != cpassword) {
			document.getElementById("spanCPassword").innerHTML = "confirm password should be same as password";
			return false;
		} else {
			document.getElementById("spanCPassword").innerHTML = "";
		}
		return true;
	}
	function checkPassword()
	{
		var password = document.getElementById("oldpassword").value;
		var str = "checkPassword.htm?password="+password;

		if (window.XMLHttpRequest) {

			xmlhttp = new XMLHttpRequest();
		} else {

			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				 document.getElementById("oldSpanPassword").innerHTML=xmlhttp.responseText;
			}
		};

		xmlhttp.open("GET", str, true);
		xmlhttp.send();

		
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
<jsp:include page="header.jsp" flush="true" />
<!-- end .header -->
</header>
<div class="container">
<div class="sidebar1">
	<c:choose>
			<c:when test="${employeeDetails.employeeCategory =='e'}">
				<jsp:include page="employeeMenu.jsp" flush="true" />
			</c:when>
			<c:when test="${employeeDetails.employeeCategory =='p'}">
				<jsp:include page="projectManagerMenu.jsp" flush="true" />
			</c:when>
			<c:otherwise>
				<jsp:include page="trainingAdminMenu.jsp" flush="true" />
			</c:otherwise>
		</c:choose>
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome ${employeeDetails.employeeName}</a></div>
<h2><b>Enter the details to change your password:-</b></h2>
		<table>
		<td>
			<table>
		<tr>
			<td>Old Password:</td>
			<td><input type="password" id="oldpassword" onchange="checkPassword()"></td>
			<td><font color="red"><h5>
						<span id="oldSpanPassword"></span>
					</h5></font></td>
		</tr>

		<tr>
			<td>New Password:</td>
			<td><input type="password" id="password" onchange="validatePassword()"></td>
			<td><font color="red"><h5>
						<span id="spanPassword"></span>
					</h5></font></td>
		</tr>
		<tr>
			<td>Confirm Password:</td>
			<td><input type="password" id="cpassword" onchange="validateCPassword()"></td>
			<td><font color="red"><h5>
						<span id="spanCPassword"></span>
					</h5></font></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><br/><button type="button" class="sexybutton sexylarge" onclick="return change()"><span><span><span class="ok">Change Password</span></span></span></button></td>
			<td><h4><span id="error"></span></h4></font></td>
		</tr>
	</table>
		</td>
		<td>
				<center>
						<b>Instructions</b><br> 1. Length of password should be
						between 8-12 characters.<br> 2. Must contain atleast one
						special character.<br> 3. Must contain atleast one number.
					</center>
		</td>
		</table>
					
		
<!-- end .content -->
</div>

<!-- end .container -->
</div>

<div class="footer">
<jsp:include page="footer.jsp" flush="true" />
<!-- end .footer -->
</div>

</body>
</html>