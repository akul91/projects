<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:if test="${employeeDetails.employeeCategory!='t'}">
	<% response.sendRedirect("clogout.htm"); %>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete User</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/sexybuttons.css" type="text/css" />
<style type="text/css" title="currentStyle">
			@import "../css/demo_page.css";
			@import "../css/demo_table.css";
		</style>
		<script type="text/javascript" language="javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>

<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').dataTable( {
					"sPaginationType": "full_numbers"
				} );
			} );
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
<h2><b>Select the employee you want to delete:-</b></h2>
<table>
<tr>
<td>
<form action="tdeleteUser.htm" method="post">		
<table class="display" id="example">
	<thead>
		<tr>
			<th>Select</th>
			<th>Employee Id</th>
			<th>Employee Name</th>
			<th>Employee Email Id</th>
			<th>Employee Number</th>
			<th>Employee Designation</th>
			<th>Tier</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${userList}" var="employeeList" varStatus="status">
				 <tr class="<c:if test="${status.count % 2 == 0}">even</c:if>"> 
					<td><input type="radio" name="enroll" value='${employeeList.employeeId}' checked></td>	
					<td>${employeeList.employeeId}</td>
					<td>${employeeList.employeeName}</td> 
					<td>${employeeList.employeeEmail}</td>
					<td>${employeeList.employeeNumber}</td>
					<td>${employeeList.employeeDesignation}</td>
					<td>${employeeList.employeeTier}</td>
				</tr>
			</c:forEach>
		</tbody>
	<tfoot>
		<tr>
			<th>Select</th>
			<th>Employee Id</th>
			<th>Employee Name</th>
			<th>Employee Email Id</th>
			<th>Employee Number</th>
			<th>Employee Designation</th>
			<th>Tier</th>
		</tr>
	</tfoot>
</table>
<br/>
<button type="submit" class="sexybutton sexylarge"><span><span><span class="delete">Delete</span></span></span></button>
</form>

</td>
</tr>
</table>
<h2><center>${msg}</center></h2>
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