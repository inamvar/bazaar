<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-md-8 col-md-offset-2" >
		<div class="panel panel-default">
			<div class="panel-heading">		
			<h3>	<span class="icon-star"></span> ${merchant.name}</h3>
								<a class="btn btn-success btn-xs"
			href="${pageContext.request.contextPath}/admin/merchant/insertPayment/${merchant.id}"><span
				class="icon-credit-card"> </span> <spring:message
					code="merchant.payment.insert" /> </a>
														<a class="btn btn-default btn-xs"
							href="${pageContext.request.contextPath}/admin/merchant/paymentList/${merchant.id}"><span
								class="icon-align-justify"> </span> <spring:message
									code="merchant.payment.history" /></a>
			</div>
			<div class="panel-body">

			<div class="col-sm-7"
				<p><spring:message code="person.firstname"/>: ${merchant.contactPoint.firstName}</p>
				<p><spring:message code="person.lastname"/>: ${merchant.contactPoint.lastName}</p>
				<p><spring:message code="contact.email"/>: ${merchant.contact.email}</p>
				<p><spring:message code="contact.phone"/>: ${merchant.contact.phone}</p>
				<p><spring:message code="contact.mobile"/>: ${merchant.contact.mobile}</p>
				<p><spring:message code="contact.fax"/>: ${merchant.contact.fax}</p>
				<p><spring:message code="contact.website"/>: ${merchant.contact.website}</p>
				<p><spring:message code="contact.address"/>: ${merchant.contact.address}</p>
			</div>
			<div class="col-sm-5">
				<div class="gmap img-thumbnail" id="gmap" style="min-height:250px;"></div>
			</div>			
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){

	var loc = "<c:out value="${merchant.contact.geoLocation}"/>";	
	showMap(loc);
	
});

</script>

<script type="text/javascript"
src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>

