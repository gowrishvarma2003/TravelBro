<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);

  if(session.getAttribute("logged_in") == null) {
    session.setAttribute("logged_in", "false");
  }
  if(session.getAttribute("logged_in").equals("false")) {
    response.sendRedirect("login_page.jsp");
  }else if (session.getAttribute("logged_in").equals("true")){
    response.sendRedirect("share_auto/auto_share.jsp");
  }
%>