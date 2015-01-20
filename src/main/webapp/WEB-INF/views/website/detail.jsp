<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
#owl-demo .item {
	/* padding: 30px 0px; */
	margin: 5px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-sm-3">
			<div class="panel panel-default">
				<div class="panel-body">
					<img
						src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=300&height=300"
						class="img-thumbnail" alt="${deal.name}" />
					<h3>${deal.name}</h3>
					<p>${deal.description}</p>
					<h4 class="text text-success">
						<spring:message code="kalatag.realPrice" />
						<fmt:formatNumber type="number" maxFractionDigits="0"
							value="${deal.price}" />
						<spring:message code="kalatag.currency" />
					</h4>
					<%-- <div class="countdown countdown-inline" data-countdown="${deal.validity}"></div> --%>
					<div class="countdown"
						data-countdown="<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" 
            value="${deal.validity}" />"></div>

				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">${deal.merchant.name}</div>
				<div class="panel-body">
					<p>
						<span class="glyphicon glyphicon-map-marker"> </span>
						${deal.merchant.contact.address}
					</p>
					<p>
						<span class="glyphicon glyphicon-earphone"> </span>
						${deal.merchant.contact.phone}
					</p>
					<p>
						<span class="glyphicon glyphicon-envelope"> </span>
						${deal.merchant.contact.email}
					</p>
					<div id="gmap" class="gmap"></div>
				</div>
			</div>

		</div>
		<div class="col-sm-9">
			<c:if test="${not empty deal.images}">
				<div class="col-sm-12  box white">
					<ul class="pgwSlider">
						<c:forEach items="${deal.images}" var="deal" varStatus="status">
							<li><img
								src="${pageContext.request.contextPath}/files/attachments/${deal.id}/dynamicImage?width=800&height=450" /></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>

			<div class="col-sm-12">
				<hr />
				<c:forEach items="${deal.options}" var="opt">
					<div class="col-sm-6">
						<div>
							<span class="glyphicon glyphicon-tag"> </span> ${opt.name}
						</div>
						<p>${opt.description}</p>
						<h4 class="text-danger">
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${opt.discount}" />
							%

							<spring:message code="kalatag.off" />
							.
							<spring:message code="kalatag.yourpay" />
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${opt.price}" />
							<spring:message code="kalatag.currency" />
						</h4>


						<div class="form-inline">
							<c:if test="${not expired}">
								<form action="${pageContext.request.contextPath}/buy"
									method="POST" class="btn-buy form-inline" role="form">



									<input type="hidden" name="dealId" value="${deal.id}" /> <input
										type="hidden" name="optionId" value="${opt.id}" />
									<spring:message code="order.quantity" />
									:
									<div class="form-group">

										<input type="text" class="form-control input-sm" name="qty"
											size="5" value="1" />


									</div>
									<button type="submit" class="btn btn-success btn-sm">
										&nbsp;&nbsp;&nbsp;&nbsp; <span
											class=" glyphicon glyphicon-shopping-cart"> </span> <b><spring:message
												code="kalatag.buy" /></b> &nbsp;&nbsp;&nbsp;&nbsp;
									</button>


								</form>
							</c:if>
						</div>

					</div>

				</c:forEach>

			</div>

			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="kalatag.features" />
					</div>
					<div class="panel-body">${deal.features}</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="kalatag.termsOfUse" />
					</div>
					<div class="panel-body">${deal.termsOfUse}</div>
				</div>
			</div>


			<div class="col-sm-12">
				<c:if test="${not empty comments}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-comment"></span> <span> <spring:message
									code="comments" />
							</span> <span class="label label-default pull-right">
								${fn:length(comments)}</span>
						</div>
						<div class="panel-body">

							<c:forEach items="${comments}" var="comment">
								<div>
									<p class="text text-muted">
										<span class="glyphicon glyphicon-user"></span>
										${comment.author.firstName} ${comment.author.lastName} | <span
											class="persian-date"><fmt:formatDate
												pattern="yyyy/MM/dd" value="${comment.date}" /> </span>
												
												<span
											><fmt:formatDate
												type="time"  value="${comment.date}" /> </span>
									<p class="text text-info">${comment.commentText}</p>
									<hr />
								</div>
							</c:forEach>

						</div>
					</div>
				</c:if>
				<hr />
				<form action="${pageContext.request.contextPath}/newcomment"
					method="POST">
					<input type="hidden" value="${deal.id}" name="dealId" />
					<div class="form-group">
						<label><spring:message code="deal.comment.write" /></label>
						<textarea class="form-control" rows="3" cols="8" name="memo"></textarea>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">
							<spring:message code="submit" />
						</button>
					</div>
				</form>
			</div>


		</div>

		<!-- Similar offers -->

		<c:if test="${not empty similars}">


			<div class="col-md-12">
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

</div>

<script>


	$(document).ready(function() {

		$("#owl-demo").owlCarousel({
			//	items : 4, //4 items above 1000px browser width
			autoPlay : 3000,
			itemsDesktop : [ 1000, 5 ], //5 items between 1000px and 901px
			itemsDesktopSmall : [ 900, 3 ], // betweem 900px and 601px
			itemsTablet : [ 600, 2 ], //2 items between 600 and 0
			itemsMobile : false
		// itemsMobile disabled - inherit from itemsTablet option
		});

		$('.pgwSlider').pgwSlider({
			displayList : true,
			displayControls : true,
			listPosition : 'left',
			transitionEffect : 'sliding',
			intervalDuration : 5000
		});

		var loc = "<c:out value="${deal.merchant.contact.geoLocation}"/>";
		console.log(loc);
		startCountdown();
		showMap(loc);

	});
</script>

<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false&amp;language=<c:out value="${pageContext.response.locale.language}"/>"></script>
