<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Successful</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
<div class="header">
<jsp:include page="header.jsp" flush="true" />
<!-- end .header -->
</div>
<div class="sidebar1">
<!-- end .sidebar1 -->
</div>
<div class="content">
<center><h2>You have been successfully registered.</h2>
<a href="cloginCall.htm">Click here to login</a></center>

<!-- end .content -->
</div>


<div class="footer">
<jsp:include page="footer.jsp" flush="true" />
<!-- end .footer -->
</div>

</body>
</html>