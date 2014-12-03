<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
						<spring:message code="kalatag.currenncy" />
					</h4>
					<p>${deal.validity}</p>
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
					<div class="gmap"></div>
				</div>
			</div>

		</div>
		<div class="col-sm-9">
			<div class="col-sm-12">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">


					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<c:forEach items="${deal.images}" var="image" varStatus="status">
							<c:if test="${status.index == 0}">
								<div class="item active">
									<img
										src="${pageContext.request.contextPath}/files/attachments/${deal.images[0].id}/dynamicImage?width=900&height=350"
										alt="${deal.name}" />
								</div>
							</c:if>
							<c:if test="${status.index > 0}">
								<div class="item">
									<img
										src="${pageContext.request.contextPath}/files/attachments/${deal.images[status.index].id}/dynamicImage?width=900&height=350"
										alt="${deal.name}" />
								</div>
							</c:if>
						</c:forEach>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>

				</div>
				<hr />
			</div>


			<ul class="list-group col-sm-12">
				<c:forEach items="${deal.options}" var="opt">
					<li class="list-group-item col-sm-12">
						<div class="col-sm-4">
							<span class="glyphicon glyphicon-tag"> </span> ${opt.name}
						</div>
						<p class="col-sm-8">${opt.description}</p>
						<h4 class="text-danger col-sm-5">
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${opt.discount}" />
							%

							<spring:message code="kalatag.off" />
							.
							<spring:message code="kalatag.yourpay" />
							<fmt:formatNumber type="number" maxFractionDigits="0"
								value="${opt.price}" />
							<spring:message code="kalatag.currenncy" />
						</h4>


						<div class="col-sm-2">
							<a class="btn btn-success  btn-sm btn-block"
								href="${pageContext.request.contextPath}/buy?dealId=${deal.id}&optionId=${opt.id}&qty=1">
								<span class=" glyphicon glyphicon-shopping-cart"> </span> <b><spring:message
										code="kalatag.buy" /></b>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>


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
		</div>
	</div>

</div>



