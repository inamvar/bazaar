
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<%--  <title>${title}</title> --%>
<title>${title}</title>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/b-roya.css" />
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/website.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/my.css" />
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/countdown.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/countdown.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/gmap3.min.js"></script>
		<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/myscript.js"></script>
</head>
<body>
	<div id="header">
		<div id="headerTitle">
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div id="content">
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>
