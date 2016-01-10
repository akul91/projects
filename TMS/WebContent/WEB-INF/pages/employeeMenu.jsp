<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/SpryMenuBar.js" type="text/javascript"></script>
<link href="../css/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
</head>

<body>
<ul id="MenuBar1" class="MenuBarVertical">
  <li><strong>MENU</strong></li>
  <li><a class="MenuBarItemSubmenu" href="#">Training</a>
  	<ul>
      <li><a href="enrollTraining.htm">Enrollment</a></li>
  	  <li><a href="clistTraining.htm">Posting</a></li>
    </ul>
    </li>  
  <li><a class="MenuBarItemSubmenu" href="#">View Current Status</a>
  	<ul>
      <li><a href="estatusTraining.htm">Training</a></li>
      <li><a href="estatusEnrollment.htm">Enrollments</a></li>
    </ul>
    </li>
   <li><a class="MenuBarItemSubmenu" href="#">Cancel</a>
    <ul>
      <li><a href="cancelTraining.htm">Training</a></li>
      <li><a href="ecancelEnrollment.htm">Enrollment</a></li>
    </ul>
  </li>
  <li><a href="cviewFeedback.htm">View Feedback of Posted Training</a></li>
  <li><a href="egiveFeedback.htm">Give Feedback of Attended Training</a></li>
  <li><a href="eviewHistory.htm">View History</a></li>
  <li><a href="changePassword.htm">Change Password</a></li>
  <li><a href="clogout.htm">Logout</a></li>
</ul>
<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgRight:"../images/SpryMenuBarRightHover.gif"});
</script>
</body>
</html>