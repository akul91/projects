<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset Password</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script>

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


function change() {
	var password = document.getElementById("password").value;
		if (password.length == 0) {
			document.getElementById("spanPassword").innerHTML = "password should not be left empty";
			return;cpassword
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
		

	
		if (!validatePassword()) {
			return false;
		}
		if (!validateCPassword()) {
			return false;

		}
	
		var str = "cresetPassword.htm?password="+password;
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
				var str=xmlhttp.responseText;
/* 				 document.getElementById("error").innerHTML=xmlhttp.responseText;
 */
 window.location=str+".htm";
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
<div class="container"><center>
<h2>Reset Password</h2>
<table>
<tr>
			<td>Password:</td>
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
			<td><input type="button" value="Change Password" onclick="return change()" />
			</td>
			<td><h4><span id="error"></span></h4></font></td>
		</tr>
		
	</table>

</center>
<!-- end .container -->
</div>

<div class="footer">
<jsp:include page="footer.jsp" flush="true" />
<!-- end .footer -->
</div>

</body>
</html>