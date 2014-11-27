<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



	<div class="text text-danger">

		<h4>
			<spring:message code="error.message" text="default text" />
			: ${exception}
		</h4>
		<p>
			<spring:message code="error.url" text="default text" />
			: ${url}
		</p>
		<hr/>
		<a href="${pageContext.request.contextPath}" class="btn btn-default"><span class="glyphicon glyphicon-home"></span> <spring:message
				code="menu.home" /></a>






</div>

