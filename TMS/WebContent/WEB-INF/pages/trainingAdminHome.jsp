<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${employeeDetails.employeeCategory!='t'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Training Admin Home</title>
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
<div class="sidebar1">
<jsp:include page="trainingAdminMenu.jsp" flush="true" />
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInTA.htm">${employeeDetails.employeeName}</a></div>
<center><h2>Personal Details</h2>
	<div ><table cellpadding="5" >
			<tr>
				<td>EmployeeId</td> 
				<td>${employeeDetails.employeeId}</td>
			</tr>
			<tr>
				<td>Name</td> 
				<td>${employeeDetails.employeeName}</td>
			</tr>
			<tr>
				<td>Mobile Number</td> 
				<td>${employeeDetails.employeeNumber}</td>
			</tr>
			<tr>
				<td>Destination</td> 
				<td>${employeeDetails.employeeDesignation}</td>
			</tr>
			<tr>
				<td>Tier</td> 
				<td>${employeeDetails.employeeTier}</td>
			</tr>
			<tr>
				<td>Email Id</td> 
				<td>${employeeDetails.employeeEmail}</td>
			</tr>
	</table>

</div>
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