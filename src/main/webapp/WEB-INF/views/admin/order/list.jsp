<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">



	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="order.list" />
	</h2>
	<hr />

	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>

	</c:if>
	<div class="col-sm-12 col-md-12">

		<table class="table">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="order.id" /></th>
					<th><spring:message code="order.date" /></th>
					<th><spring:message code="order.status" /></th>
					<th><spring:message code="order.customer" /></th>
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
						<td><c:out
								value="${order.person.firstName} ${order.person.lastName}" /></td>
						<td><c:out value="${order.deal.merchant.name}" /></td>
						<td><c:out value="${order.deal.name}" /></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="0"
													value=" ${order.totalPrice}" /></td>
						<td><a 
							href="${pageContext.request.contextPath}/admin/order/detail/${order.id}"><span
								class="glyphicon glyphicon-th"> </span></a> 
								<a class="btn btn-danger btn-xs"
							href="${pageContext.request.contextPath}/admin/order/delete/${order.id}"><span
								class="glyphicon glyphicon-trash"> </span></a> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



