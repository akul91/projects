<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <c:if test="${employeeDetails.employeeCategory!='e'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Employee Home</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/sexybuttons.css" type="text/css" />
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
<jsp:include page="employeeMenu.jsp" flush="true" />
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInEmployee.htm">${employeeDetails.employeeName}</a></div>
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
				<td>Designation</td> 
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
</div></div></center>
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