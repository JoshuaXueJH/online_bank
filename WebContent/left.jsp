<%@page contentType="text/html;charset=utf-8" import="java.sql.*,java.util.*"%>

<%
if(session.getAttribute("user")!=null){
//--------------------------------------logged------------------------------------------------------------------
	
%>
<html>
<head>
<title>管理页面</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
<link rel="stylesheet" type="text/css" href="style/default.css">

</head>
<BODY>

<table cellpadding=0 cellspacing=0 width=200 align=center class="table">

  <tr>
        <td height=25 align="center" bgcolor="#DBC2B0"> 
          <b>用户管理</b></td>
  </tr>
  <tr>
    <td>
            <table cellpadding=0 cellspacing=0 align=center width=180 class="table" >                           
              <tr> 
                <td height=20><a href=/netbank/deposit.jsp target=right >存款</a></td>
              </tr>
              <tr> 
                <td height=20><a href=/netbank/withdrawal.jsp target=right>取款</a></td>
              </tr>
              <tr> 
                <td height=20><a href=/netbank/transfer.jsp target=right>转账</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/transaction/list?pager.curPage=1 target=right>查询交易记录</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/information.jsp target=right>查看信息</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/modify.jsp target=right>修改个人信息</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/changepwd.jsp target=right>修改密码</a></td>
              </tr>
               <tr> 
                <td height=20 ><a href=/netbank/user/user_logout target=_top>注销</a></td>
              </tr>
              </table>
	</td>
  </tr>
</table>
<%
}
%>