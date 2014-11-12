    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>            
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Hello Spring mvc</title>
   
   <link href="<c:url value="/resources/styles/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.9.1.min.js" />"></script>
   
   <!-- 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/resources/styles/bootstrap.min.css" />
	
	 <script type="text/javascript" src="${pageContext.request.contextPath}/admin/resources/scripts/jquery-1.9.1.min.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/admin/resources/scripts/bootstrap.min.js"></script>
	  -->
</head>
<body>
    <div id="header">
       <div id="headerTitle"><tiles:insertAttribute name="header" /></div>
    </div>
    <p>asdf sldfkjhdf</p>
    <div id="content">
       <tiles:insertAttribute name="body" />
    </div>
</body>
</html>
        