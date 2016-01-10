<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${employeeDetails.employeeCategory!='t'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Details</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<style type="text/css" title="currentStyle">
@import "../css/demo_page.css";

@import "../css/demo_table.css";
</style>
<script type="text/javascript" language="javascript"
	src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="../js/jquery.dataTables.js"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable({
			"sPaginationType" : "full_numbers"
		});
	});
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
<jsp:include page="trainingAdminMenu.jsp" flush="true" />
<!-- end .sidebar1 -->
</div>
<div class="content">
<div style='float:right;margin-right:10px'> Welcome <a href="cloggedInTA.htm">${employeeDetails.employeeName}</a></div>
<h2><b>Employees enrolled for this training are:-</b></h2>
<table>
<tr>
<td>
<c:choose>
<c:when test="${fn:length(history) > 0}">
	<table class="display" id="example">
		<thead>
		<tr>
			<td>EmployeeId</td>
			<td>EmployeeName</td>
			<td>Mobile Number</td> 
			<td>Designation</td>
			<td>Tier</td>  
			<td>Email Id</td> 
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${history}" var="enrollment" varStatus="status">
			<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
			
				<td>${enrollment.employeeId}</td>
				<td>${enrollment.employee.employeeName}</td>   
				<td>${enrollment.employee.employeeNumber}</td>
				<td>${enrollment.employee.employeeDesignation}</td>
				<td>${enrollment.employee.employeeTier}</td>
				<td>${enrollment.employee.employeeEmail}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</c:when>
<c:otherwise>
		<h2>No Employee enrolled in this training</h2>
</c:otherwise>
</c:choose></td>
				</tr>
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