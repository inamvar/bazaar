<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
<title><spring:message code="error" text="default text" /></title>
</head>
<body>
	<div class="col-md-8 col-md-offset-2">

		<div class="alert alert-warning">
			<h2 class="text text-danger">
				<spring:message code="error.code" />: 403
			</h2>
			<spring:message code="error.403" />
			
			<hr />
			<a href="${pageContext.request.contextPath}" class="btn btn-default"><span
				class="glyphicon glyphicon-home"></span> <spring:message
					code="menu.home" /></a>

		</div>
	</div>
</body>
</html>