<%@page contentType="text/html;charset=utf-8" import="java.sql.*,java.util.*"%>
<%
	if(session.getAttribute("user")==null)
	{ 
%>
		<jsp:forward page="login.jsp"></jsp:forward>
<%
	}
%>
<html>
<head>
<title>网上银行</title>
</head>
  <frameset framespacing="0" border="false" cols="270,*" frameborder="0">
  <frame name="left"  scrolling="no" marginwidth="0" marginheight="0" src="/netbank/left.jsp">
  <frame name="right" scrolling="yes" src="/netbank/information.jsp">
</frameset>

<noframes>
</noframes> 
</html>
