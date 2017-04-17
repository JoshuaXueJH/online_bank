
<%@page import="com.joshua.onlinebank.domain.Account"%>
<%
	Account account=(Account)session.getAttribute("user");
	if(account==null){
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

%>
