<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<div class="container">
	<div class="panel panel-default">
		<div class="panel-body">
		
			<h4 >
				<a href="#"  data-toggle="collapse" class="pull-right"
					data-target="#detail" aria-expanded="true" aria-controls="detail">
					<span class="glyphicon glyphicon-info-sign"></span>
					</a>
			<span class="text text-danger">	<spring:message code="error.message" text="default text" />
				: ${message}
				</span>
			</h4>

			<div id="detail" class="collapse" style="direction:ltr;">
				<hr />
				<small> ${exception} </small>
			</div>
			<hr />
			<a href="${pageContext.request.contextPath}" class="btn btn-default"><span
				class="glyphicon glyphicon-home"></span> <spring:message
					code="menu.home" /></a>
		</div>
	</div>

</div>