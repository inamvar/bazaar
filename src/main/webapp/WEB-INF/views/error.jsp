<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">

	<div class="alert alert-danger">

		<h4>
			<spring:message code="error.message" text="default text" />
			: ${exception.message}
		</h4>
		<p>
			<spring:message code="error.url" text="default text" />
			: ${url}
		</p>
		<hr/>
		<a href="${pageContext.request.contextPath}" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> <spring:message
				code="menu.home" /></a>

	</div>




</div>

