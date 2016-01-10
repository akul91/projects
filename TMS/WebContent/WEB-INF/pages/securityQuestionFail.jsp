<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Security Question</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
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
<form action="csendAnswer.htm">
<center><h2>Enter the security answer which you entered at time of registration<br/>
<font color="red">Incorrect Answer</font></h2>
<table>
<tr>
<td>
</td>
<td>
</td>
</tr>

<c:if test="${question.securityQuestion==1}">
<tr>
<td>Security Question:</td>
<td>What is the name of your favorite teacher
<font color="red">(case-sensitive)</font></td>
</tr>
<tr>
<td>Answer:</td>
<td><input type="text" name="answer"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" name="submit"></td>
</tr>
</c:if>
<c:if test="${question.securityQuestion==2}">
<tr>
<td>Security Question:</td>
<td>Who is the person you hate the most
<font color="red">(case-sensitive)</font></td>
</tr>
<tr>
<td>Answer:</td>
<td><input type="text" name="answer"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" name="submit"></td>
</tr>
</c:if>
<c:if test="${question.securityQuestion==3}">
<tr>
<td>Security Question:</td>
<td>Which place do you like the most?
<font color="red">(case-sensitive)</font></td>
</tr>
<tr>
<td>Answer:</td>
<td><input type="text" name="answer"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" name="submit"></td>
</tr>
</c:if>
<c:if test="${question.securityQuestion==4}">
<tr>
<td>Security Question:</td>
<td>Who is your inspiration?
<font color="red">(case-sensitive)</font></td>
</tr>
<tr>
<td>Answer:</td>
<td><input type="text" name="answer"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" name="submit"></td>
</tr></c:if>
<c:if test="${question.securityQuestion==0}">
No employee registered with this employee Id
</c:if>

</table></center>
</form>
<!-- end .container -->
</div>

<div class="footer">
<jsp:include page="footer.jsp" flush="true" />
<!-- end .footer -->
</div>

</body>
</html>