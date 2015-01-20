<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row footer">
	<div class="col-sm-12 col-md-6">
		<h3>
			<spring:message code="footer.website" />
		</h3>
		<p>
			<spring:message code="footer.about" />
		</p>
	</div>
	<div class="col-sm-12 col-md-6">
		<div class="pull-right">
			<p>
				<spring:message code="footer.socials" />
			</p>
			<h4>
			<a href=""><span class="icon-facebook lg"></span></a> 
			<a href=""><span class="icon-twitter"></span></a> 
			<a href=""><span class="icon-google-plus"></span></a>
			</h4>
			<p style="direction:ltr;"><span class="icon-phone"> </span> (021) 00000000</p>
		</div>
	</div>

	<div class="col-sm-12 col-md-12">
	<jsp:useBean id="date" class="java.util.Date" />
	<p class="text-center"><spring:message code="footer.copyright" /></p>
	<p class="text-center"><fmt:formatDate value="${date}" pattern="yyyy" /></p>
	</div>

</div>
