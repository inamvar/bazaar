<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><spring:message
					code="website.header.brand" /></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<div class="nav navbar-nav"></div>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><spring:message code="language"/> (<c:out value="${pageContext.response.locale.language}"/>) <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?lang=en"><img src="${pageContext.request.contextPath}/resources/images/gb.gif"/> English</a></li>
						<li><a href="?lang=ar"><img src="${pageContext.request.contextPath}/resources/images/ae.gif"/> العربیه</a></li>
						<li><a href="?lang=fa"><img src="${pageContext.request.contextPath}/resources/images/ir.gif"/> فارسی</a></li>
					</ul> <c:if test="${pageContext['request'].userPrincipal != null}">
						<li><a
							href="${pageContext.request.contextPath}/j_spring_security_logout"><spring:message
									code="security.logout" /></a></li>
					</c:if> <c:if test="${pageContext['request'].userPrincipal == null}">
						<li><a href="${pageContext.request.contextPath}/admin/login"><spring:message
									code="security.login" /></a></li>
					</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>






