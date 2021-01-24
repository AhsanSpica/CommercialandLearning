<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="servercode.PostClass "   %>
<%@ page import="java.sql.*,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  
 <%   PostClass db1=new PostClass();
   //  String hotelResult[]= new String[10];
   String name="";
 
   int j=0;
   String query="";
  
   String tag ="";
   tag= request.getParameter("t");
   
  if (tag.equals("a"))
  {
   ArrayList<String>  after=new ArrayList<String>();  
   
  after=db1.postrequestinterface(request, response);
  out.println("*"+after+"*");
  
  }
  
  
  %>
</body>
</html>