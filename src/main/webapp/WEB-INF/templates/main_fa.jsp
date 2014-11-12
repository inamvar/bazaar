
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html dir="rtl">
<head>
<title>Hello Spring mvc</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/resources/styles/bootstrap.min.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/resources/scripts/bootstrap.min.js"></script>

<style>
html {
	font-family: "Segoe UI";
}
</style>
</head>
<body>
	<div id="header">
		<div id="headerTitle">
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div id="content">
		<td><tiles:insertAttribute name="body" />
	</div>
</body>
</html>
