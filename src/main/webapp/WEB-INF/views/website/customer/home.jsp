<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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


<div class="col-sm-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<spring:message code="customer.panel.welcome"  arguments="${customer.firstName} ${customer.lastName}" />
		</div>
		<div class="panel-body">
		 <p> <spring:message code="customer.panel.ordercount"/> <span class="badge"> ${fn:length(orders)}</span> </p>
		  <p > <spring:message code="customer.panel.ordersTotalPrice"/>: <span class="badge"> <fmt:formatNumber type="number" maxFractionDigits="0"
													value=" ${totalOrdersPrice}" />  <spring:message code="kalatag.currency"/> </span> </p> 
		</div>
	</div>
</div>
<div class="col-sm-9">
	<div class="row">
		<div class="btn-group btn-group-justified" role="tablist" id="myTab">
			<div class="btn-group" id="orders">
				<button type="button" class="btn btn-nav">
					<span class="glyphicon glyphicon-align-justify"></span>
					<p>
						<spring:message code="customer.panel.orders" />
					</p>
				</button>
			</div>
			<div class="btn-group" id="account">
				<button type="button" class="btn btn-nav">
					<span class="glyphicon glyphicon-leaf"></span>
					<p>
						<spring:message code="customer.panel.account" />
					</p>
				</button>
			</div>

		</div>
	</div>




	<div class="row">
			<div class="my-pane panel panel-default" id="pane-0"
			style="min-height: 400px;">
			<div class="panel-body">

				<h2><spring:message code="customer.panel.orders"/></h2>
				<hr/>
				<c:if test="${not empty orders}">
					<table class="table table-responsive">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="order.id" /></th>
					<th><spring:message code="order.date" /></th>
					<th><spring:message code="order.status" /></th>
					<th><spring:message code="order.merchant" /></th>
					<th><spring:message code="order.dealName" /></th>
					<th><spring:message code="order.totalprice" /> (<spring:message code="kalatag.currency"/>)</th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="order" items="${orders}">
					<tr>
						<td><c:out value="${order.id}" /></td>
						<td><c:out value="${order.orderDate}" /></td>
						<td><c:out value="${order.status}" /></td>
						<td><c:out value="${order.deal.merchant.name}" /></td>
						<td><c:out value="${order.deal.name}" /></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="0"
													value=" ${order.totalPrice}" /></td>
						<td><a 
							href="${pageContext.request.contextPath}/customer/order/detail?id=${order.id}"><span
								class="glyphicon glyphicon-zoom-in">  </span> <spring:message code="customer.panel.order.detail"/> </a> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
				</c:if>
			</div>

		</div>
	
		<div class="my-pane panel panel-default" id="pane-1"
			style="min-height: 400px;">
			<div class="panel-body">
				<h2><spring:message code="customer.panel.account"/></h2>
				<hr/>				
			</div>
		</div>
	</div>



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
