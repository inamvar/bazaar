<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
    
<html>
   <head>
   <title> <spring:message code="error" text="default text" /></title>
   </head>
   <body>
   <div class="col-md-8 col-md-offset-2">
   <h2 class="text text-danger"> <spring:message code="error.code"  />:404 </h2>
 <div class="alert alert-danger">
 
    <p> <spring:message code="error.message" />:  <spring:message code="error.404" /></p>
    </div>
    </div>
   </body>
</html>