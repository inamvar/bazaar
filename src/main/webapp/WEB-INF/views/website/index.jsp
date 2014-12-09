<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<div class="container">

		<h4 class="text text-danger">
			<span class="glyphicon glyphicon-warning-sign"></span> <b>توجه: </b>سایت
			در دست ساخت است. کلیه محتوای سایت آزمایشی و غیر واقعی هستند
		</h4>
	

	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>

	</c:if>

	<div class="row">

		<div class="sidebar-nav col-sm-2">

			<ul class="nav nav-pills nav-stacked nav-success">
				<%-- 					<li class="text-info"><a><spring:message
								code="admin.menu.definitions.categories" /></a></li> --%>
				<li><a class="text-success"
					href="${pageContext.request.contextPath}/"><i
						class="glyphicon glyphicon-th"></i> <spring:message
							code="deal.all" /></a></li>
				<c:forEach items="${categories}" var="category">
					<li><a class="text-success"
						href="${pageContext.request.contextPath}?category=${category.id}"><span
							class="glyphicon glyphicon-th"></span> ${category.name}</a></li>
				</c:forEach>
			</ul>

		</div>


		<div class="col-sm-10 ">

			<div class="row white">
				<div class="col-sm-8">
					<ul class="pgwSlider">
						<c:forEach items="${featureds[0].images}" var="featured"
							varStatus="status">
							<li><img
								src="${pageContext.request.contextPath}/files/attachments/${featured.id}/dynamicImage?width=600&height=320" /></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-sm-4" style="padding-right: 15px;padding-left: 15px; padding-top:0;">

					<h3>${featureds[0].name}</h3>
					<h4>${featureds[0].description}</h4>
					<p class="text text-info" style="font-size: 1.4em">
						<spring:message code="kalatag.realPrice" />
						<fmt:formatNumber type="number" maxFractionDigits="0"
							value="${featureds[0].price}" />
						<spring:message code="kalatag.currency" />
					</p>
					<div class="featured">
						<span class="square"> <spring:message
								code="kalatag.yourpay" /> <span class="text text-success"
							style="font-size: 1.6em;"> <fmt:formatNumber type="number"
									maxFractionDigits="0" value="${featureds[0].options[0].price}" />

						</span> <spring:message code="kalatag.currency" />
						</span> <span class="discount"> <fmt:formatNumber type="number"
								maxFractionDigits="0"
								value="${featureds[0].options[0].discount}" />%
						</span>
					</div>
					<div class="countdown"
						data-countdown="<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" 
            value="${featureds[0].validity}" />"></div>

					<a class="btn btn-success btn-block"
						href="${pageContext.request.contextPath}/detail?deal=${featureds[0].id}"><span
						class="glyphicon glyphicon-shopping-cart"></span> <spring:message
							code="kalatag.view" /></a>


				</div>

			</div>
			<hr/>
			<div class="row white">
				<c:forEach items="${deals}" var="deal">
					<div class="col-xs-12 col-sm-4 col-md-3">

						<div class="productbox">
							<a
								href="${pageContext.request.contextPath}/detail?deal=${deal.id}">
								<div class="imgthumb img-responsive">
									<img
										src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=300&height=250">
								</div>
								<div class="caption">
									<p>${deal.name}
										<!-- 									<span class="pull-right label label-success"><i
										class="glyphicon glyphicon-shopping-cart"></i> <span class="">10</span></span> -->
									</p>
									<s class="text-muted"><fmt:formatNumber type="number"
											maxFractionDigits="0" value="${deal.price}" /></s>
									<c:if test="${not empty deal.options}">
										<b class="finalprice"> <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${deal.options[0].price}" /> <spring:message
												code="kalatag.currency" />
										</b>
										<!-- from <b>Amazon</b> -->
									</c:if>




								</div> <c:if test="${not empty deal.options}">
									<div class="saleoffrate">
										<b><fmt:formatNumber type="number" maxFractionDigits="0"
												value=" ${deal.options[0].discount}" /> %</b><br>
										<%-- 	<spring:message code="kalatag.off" /> --%>
									</div>
								</c:if> <%-- 						<button type="button" class="btn btn-success btn-md btn-block">
							<spring:message code="kalatag.viewdeal" />
						</button> --%>
							</a>
						</div>

					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.pgwSlider').pgwSlider({
			displayList : false,
			displayControls : true,
			intervalDuration : 5000
		});

		startCountdown();
	});
</script>


