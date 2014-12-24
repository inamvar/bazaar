<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<div class="container">



	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>
		<hr />
	</c:if>

	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${errorMsg}</p>
		</div>
		<hr />
	</c:if>

	<div class="row">
		<div class="btn-group btn-group-justified" role="tablist" id="myTab">
			<div class="btn-group" id="verify">
				<button type="button" class="btn btn-nav">
					<span class="glyphicon glyphicon-search"></span>
					<p>
						<spring:message code="merchant.panel.verify" />
					</p>
				</button>
			</div>
			<div class="btn-group" id="redeem">
				<button type="button" class="btn btn-nav">
					<span class="glyphicon glyphicon-ok"></span>
					<p>
						<spring:message code="merchant.panel.redeem" />
					</p>
				</button>
			</div>
			<div class="btn-group" id="deals">
				<button type="button" class="btn btn-nav">
					<span class="glyphicon glyphicon-tags"></span>
					<p>
						<spring:message code="merchant.panel.deals" />
					</p>
				</button>
			</div>
			<div class="btn-group" id="account">
				<button type="button" class="btn btn-nav">
					<span class="glyphicon glyphicon-leaf"></span>
					<p>
						<spring:message code="merchant.panel.account" />
					</p>
				</button>
			</div>
<%-- 			<div class="btn-group" id="change-pass">
				<button type="button" class="btn btn-nav">
					<span class=" glyphicon glyphicon-lock"></span>
					<p>
						<spring:message code="merchant.panel.changepass" />
					</p>
				</button>
			</div> --%>
		</div>
	</div>




	<div class="row">
		<div class="my-pane panel panel-default" id="pane-0"
			style="min-height: 400px;">
			<div class="panel-body">
				<h2><spring:message code="coupon.verify.title"/></h2>
				<hr/>
				<form class="form-inline" method="POST"
					action="${pageContext.request.contextPath}/merchant/panel/verify">
					<label class="form-label"><spring:message code="coupon.code"/></label>
					<div class="form-group">

						<input type="text" class="form-control" name="couponCode" />

					</div>
					<div class="form-group">
						<button class="btn btn-danger" type="submit"><spring:message code="coupon.verify"/></button>
					</div>
				</form>
				<c:if test="${not empty verifyCoupon}">
					<div class="verify-result  alert alert-info col-sm-6">
						<P><spring:message code="coupon.issueDate"/>: ${verifyCoupon.issueDate}</P>
						<P><spring:message code="coupon.deal"/>: ${verifyCoupon.deal.name}</P>
						<P><spring:message code="coupon.status"/>: ${verifyCoupon.status}</P>
						<P><spring:message code="coupon.expireDate"/>: ${verifyCoupon.expireDate}</P>
					</div>
				</c:if>
				<c:if test="${not empty verifyError}">
				<div class="alert alert-danger col-sm-6">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${verifyError}</p>
		</div>
				
					
				</c:if>
			</div>
		</div>

		<div class="my-pane panel panel-default" id="pane-1"
			style="min-height: 400px;">
			<div class="panel-body">

				<h2><spring:message code="coupon.redeem.title"/></h2>
				<hr/>
				<form class="form-inline" method="POST"
					action="${pageContext.request.contextPath}/merchant/panel/redeem">
					<label class="form-label"><spring:message code="coupon.code"/></label>
					<div class="form-group">

						<input type="text" class="form-control" name="couponCode" />

					</div>
					<div class="form-group">
						<button class="btn btn-danger" type="submit"><spring:message code="coupon.redeem"/></button>
					</div>
				</form>

				<c:if test="${not empty redeemSuccess}">
					<h4 class="text text-info">${redeemSuccess}</h4>
				</c:if>
				<c:if test="${not empty redeemError}">
				<div class="alert alert-danger col-sm-6">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${redeemError}</p>
		</div>
				
					
				</c:if>


				<c:if test="${not empty redeemCoupon}">
					<div class="redeem-result alert alert-info col-sm-6">
						<P><spring:message code="coupon.issueDate"/>: ${redeemCoupon.issueDate}</P>
						<P><spring:message code="coupon.deal"/>: ${redeemCoupon.deal.name}</P>
						<P><spring:message code="coupon.status"/>: ${redeemCoupon.status}</P>
						<P><spring:message code="coupon.expireDate"/>: ${redeemCoupon.expireDate}</P>
					</div>
				</c:if>


			</div>

		</div>

		<div class="my-pane panel panel-default" id="pane-2"
			style="min-height: 400px;">
			<div class="panel-body">
				<h2><spring:message code="merchant.panel.deals" /></h2>
				<table class="table table-responsive">
				<thead>
					<tr>
						<th><spring:message code="deal.name" /></th>
						<th><spring:message code="deal.validity" /></th>
						<th><spring:message code="deal.status" /></th>
						<th><spring:message code="deal.price" /> (<spring:message code="kalatag.currency" />)</th>
					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${deals}" var="deal">
						<tr >
							<td >${deal.name}</td>
							<td >${deal.validity}</td>
							<td >${deal.status}</td>
							<td >${deal.price}</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>

			</div>
		</div>
		<div class="my-pane panel panel-default" id="pane-3"
			style="min-height: 400px;">
			
			<div class="col-sm-4">
			<h4 class="alert alert-success"><spring:message code="merchant.panel.balance"/>: <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${balance}" /> <spring:message code="kalatag.currency"/></h4>
			</div>
			<div class="col-sm-4">
			<h4 class="alert alert-info"><spring:message code="merchant.panel.totalSold"/>: <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${sold}" /> <spring:message code="kalatag.currency"/></h4>
			</div>
			<div class="col-sm-4">
			<h4 class="alert alert-danger"><spring:message code="merchant.panel.totalPaid"/>: <fmt:formatNumber type="number"
												maxFractionDigits="0" value="${paid}" /> <spring:message code="kalatag.currency"/></h4>
			</div>
			
			<h4 class="col-sm-12"><spring:message code="merchant.payment.history"/></h4>
			<div class="col-sm-9">
			<table class="table table-responsive">
			<thead>
			<tr>
				<th><spring:message code="merchant.payment.id"/></th>
				<th><spring:message code="merchant.payment.refNumber"/></th>
				<th><spring:message code="merchant.payment.date"/></th>
				<th><spring:message code="merchant.payment.amount"/></th>
				<th><spring:message code="merchant.payment.note"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${payments}" var="payment">
			<tr>
			<td>${payment.id}</td>
				<td>${payment.referenceNumber}</td>
			<td>${payment.paymentDate}</td>
			<td><fmt:formatNumber type="number"
					maxFractionDigits="0" value="${payment.amount}" /> <spring:message code="kalatag.currency"/></td>
					<td>${payment.note}</td>
			</tr>
			</c:forEach>
			</tbody>
			
			</table>
			</div>
			
			<div class="col-sm-3">
				<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body"> 
					<p>Customers Count: ${totalCustomers}</p>
				</div>
				</div>
			</div>
			</div>
		<div class="my-pane panel panel-default" id="pane-4"
			style="min-height: 400px;">Change pass</div>
	</div>



</div>


<script>
	var activeEl = ${idx};
	console.log("Active element= "+ activeEl);
	$(function() {
		var items = $('.btn-nav');
		var panels = $('.my-pane');
		$(panels).each(function() {
			$(this).hide();

		});

		$(items[activeEl]).addClass('active');
		$(panels[activeEl]).show();

		$(".btn-nav").click(function() {
			$(panels[activeEl]).hide();
			$(items[activeEl]).removeClass('active');
			$(this).addClass('active');
			/*  window.location.href = "${pageContext.request.contextPath}/merchant/panel?index="+ activeEl; */
			activeEl = $(".btn-nav").index(this);
			$(panels[activeEl]).show();

		});
	});
</script>
