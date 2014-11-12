<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
   <head>
   <title> <spring:message code="error" text="default text" /></title>
   </head>
   <body>
   <h2> <spring:message code="error.code" text="default text" />: ${status} </h2>
   <p> <spring:message code="error.url" text="default text" />: ${url} </p>
    <p> <spring:message code="error.message" text="default text" />:  ${exception.message}</p>
   </body>
</html>