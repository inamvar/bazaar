<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<div class="container">



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
			<div class="btn-group" id="change-pass">
				<button type="button" class="btn btn-nav">
					<span class=" glyphicon glyphicon-lock"></span>
					<p>
						<spring:message code="merchant.panel.changepass" />
					</p>
				</button>
			</div>
		</div>
	</div>



	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>

	</c:if>

	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${errorMsg}</p>
		</div>

	</c:if>

	<div class="row">
		<div class="my-pane panel panel-default" id="pane-0"
			style="min-height: 400px;">
			<div class="panel-body">
				<h2>Verify a Counpon</h2>
				<form class="form-inline" method="POST" action="${pageContext.request.contextPath}/merchant/panel/verify">
					<label class="form-label">Coupon's code:</label>
					<div class="form-group">

						<input type="text" class="form-control" name="couponCode" />

					</div>
					<div class="form-group">
						<a class="btn btn-danger" type="submit">Verify</a>
					</div>
				</form>
				<c:if test="${not empty verifyCoupon}">
					<div class="verify-result">
						<P> Coupon Issue date : ${verifyCoupon.issueDate}</P>
						<P> Deal : ${verifyCoupon.deal.name}</P>
						<P> Status: ${verifyCoupon.status}</P>
						<P> Expire Date: ${verifyCoupon.expireDate}</P>
					</div>
				</c:if>
				<c:if test="${empty verifyCoupon}">
					<h4 class="text text-danger">Coupon is not valid</h4>
				</c:if>
			</div>
		</div>

		<div class="my-pane panel panel-default" id="pane-1"
			style="min-height: 400px;">Redeem</div>

		<div class="my-pane panel panel-default" id="pane-2"
			style="min-height: 400px;">
			<div class="panel-body">
				<h2>Deals</h2>
				<c:forEach items="${deals}" var="deal">
					<div class="row">
						<p class="col-sm-2">${deal.name} </p>
						<p class="col-sm-2">${deal.validity} </p>
					</div>
				</c:forEach>
				
			</div>
			</div>
		<div class="my-pane panel panel-default" id="pane-3"
			style="min-height: 400px;">Account</div>
		<div class="my-pane panel panel-default" id="pane-4"
			style="min-height: 400px;">Change pass</div>
	</div>



</div>


<script>
	var activeEl = 0;
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
