<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/SpryMenuBar.js" type="text/javascript"></script>
<link href="../css/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">

<ul id="MenuBar1" class="MenuBarVertical">
<li><strong>MENU</strong></li>
  <li><a href="clistTraining.htm">Post Training</a></li>
  <li><a class="MenuBarItemSubmenu" href="#">Approve/Reject Requests</a>
  	<ul>
      <li><a href="prequestTraining.htm">Training</a></li>
      <li><a href="prequestEnrollment.htm">Enrollments</a></li>
    </ul>
  </li>
  <li><a class="MenuBarItemSubmenu" href="#">View Status</a>
  	<ul>
      <li><a href="pstatusTraining.htm">Training</a></li>
      <li><a href="pstatusEmployee.htm">Employee</a></li>
    </ul>
  </li>
  <li><a href="cancelTraining.htm">Cancel Training</a></li>
  <li><a href="cviewFeedback.htm">View Feedback of Posted Training</a></li>
  <li><a href="phistory.htm">View History</a></li>
  <li><a href="changePassword.htm">Change Password</a></li>
  <li><a href="clogout.htm">Logout</a></li>
</ul>
<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgRight:"../images/SpryMenuBarRightHover.gif"});
</script>
</body>
</html>