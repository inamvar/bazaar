<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-10 col-md-10 col-sm-offset-1 col-md-offset-1">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
				<div class="col-sm-12">
				<p><spring:message code="order.id"/>: ${order.id }</p>
				<p><spring:message code="order.date"/>: 
				
				<span
											><fmt:formatDate
												pattern="hh:mm:ss"  value="${order.orderDate}" /> </span>
										
										 <span
											class="persian-date"><fmt:formatDate
												pattern="yyyy/MM/dd" value="${order.orderDate}" /> </span>
				
				</p>
				<p><spring:message code="order.quantity"/>: ${order.quantity}</p>
				</div>
					<div class="col-sm-3">
						<img class="img-thumbnail"
							src="${pageContext.request.contextPath}/files/deals/${order.deal.id}/thumbnail?width=300&height=300"
							alt="${order.deal.name}" />
					</div>
					<div class="col-sm-5">
						<p><span class="glyphicon glyphicon-tag"></span> <b>${order.deal.name}</b></p>
						<p>${order.deal.description}</p>
						
						<p><spring:message code="kalatag.paid" />: 
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${order.option.price}" />
							<spring:message code="kalatag.currency" />
						</p>
						<p>${order.deal.merchant.name}</p>
						<p><span class="glyphicon glyphicon-map-marker"></span> ${order.deal.merchant.contact.address}</p>
						<p><span class="glyphicon glyphicon-earphone"></span> ${order.deal.merchant.contact.phone}</p>
					</div>
					<div class="col-sm-4">
					<div class="gmap img-thumbnail" id="gmap"></div>
					</div>
					

					<div class="col-sm-12">
					<hr/>
						<c:if test="${ not empty order.coupons}">
							<p class="text text-info">
								<spring:message code="order.herecoupons" />
							</p>
							<table border="1" >

								<tbody>
									<c:forEach items="${order.coupons}" var="coupon">
										<tr>
											<td  valign="middle" style="font-family: Arial;font-size:2em;">${coupon.code}</td>
											<td valign="middle"><img
												src="${pageContext.request.contextPath}/files/coupons/${coupon.id}/barcode?width=275&height=50" /></td>
											<td valign="middle"><img
												src="${pageContext.request.contextPath}/files/coupons/${coupon.id}/qrcode?width=75&height=75" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<hr/>
						<c:if test="${empty order.coupons}">
							<p class="text text-danger">
								<spring:message code="order.couponsnotyet" />
							</p>
						</c:if>
						<a href="${pageContext.request.contextPath}/customer/panel"
							class="btn btn-default btn-sm"><span
							class="glyphicon glyphicon-arrow-right"></span> <spring:message
								code="back" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Similar offers -->

		<c:if test="${not empty similars}">


			<div class="col-sm-10 col-md-10 col-sm-offset-1 col-md-offset-1">
				<h3>
					<spring:message code="deal.similars" />
				</h3>
				<hr />

				<div class="similars">
					<div id="owl-demo" class="owl-carousel">
						<c:forEach items="${similars}" var="sim" varStatus="stat">

							<div class="item productbox">

								<a
									href="${pageContext.request.contextPath}/detail?deal=${sim.id}">
									<img
									src="${pageContext.request.contextPath}/files/deals/${sim.id}/thumbnail?width=255&height=170"
									class="img img-responsive" />
									<div class="caption">
										<p>${sim.name }</p>
										<span class="old-price" style="font-size: 0.9em;"><fmt:formatNumber
												type="number" maxFractionDigits="0" value="${deal.price}" /></span>
										<c:if test="${not empty deal.options}">
											<b class="finalprice" style="font-size: 0.9em;"> <fmt:formatNumber
													type="number" maxFractionDigits="0"
													value="${deal.options[0].price}" /> <spring:message
													code="kalatag.currency" />
											</b>
											<!-- from <b>Amazon</b> -->
										</c:if>


									</div>
								</a>

							</div>
						</c:forEach>



					</div>

				</div>

			</div>

		</c:if>
	
</div>


<script>
$(document).ready(function(){
	
	$("#owl-demo").owlCarousel({
		//	items : 4, //4 items above 1000px browser width
		autoPlay : 3000,
		itemsDesktop : [ 1000, 5 ], //5 items between 1000px and 901px
		itemsDesktopSmall : [ 900, 3 ], // betweem 900px and 601px
		itemsTablet : [ 600, 2 ], //2 items between 600 and 0
		itemsMobile : false
	// itemsMobile disabled - inherit from itemsTablet option
	});

	
	var loc = "<c:out value="${order.deal.merchant.contact.geoLocation}"/>";	
	showMap(loc);
	
});

</script>

<script type="text/javascript"
src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>