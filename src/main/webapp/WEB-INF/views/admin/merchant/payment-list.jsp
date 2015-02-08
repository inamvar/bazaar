<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="merchant.payment.history" /> 
		
	</h2>
	<p>${merchant.name} - ${merchant.contactPoint.firstName} ${merchant.contactPoint.lastName}</p>
	<hr />
	<div class="col-sm-12 col-md-12">
<a class="btn btn-success btn-sm"
			href="${pageContext.request.contextPath}/admin/merchant/insertPayment/${merchant.id}"><span
				class="icon-credit-card"> </span> <spring:message
					code="merchant.payment.insert" /> </a>
		<table class="table">
			<thead class="table-heading">
				<tr>

					<th><spring:message code="merchant.payment.paymentDate" /></th>
					<th><spring:message code="merchant.payment.IssueDate" /></th>
					<th><spring:message code="merchant.payment.amount" /></th>
					<th><spring:message code="merchant.payment.referenceNumber" /></th>
					<th><spring:message code="merchant.payment.chequeNumber" /></th>
					<th><spring:message code="merchant.payment.chequeDate" /></th>
					<th><spring:message code="merchant.payment.chequeAccount" /></th>
					<th><spring:message code="merchant.payment.note" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="payment" items="${payments}">
					<tr>

						<td><c:out value="${payment.paymentDate}" /></a></td>
						<td><c:out value="${payment.issueDate}" /></td>
						<td><c:out value="${payment.amount}" /></td>
						<td><c:out value="${payment.referenceNumber}"/></td>
						<td><c:out value="${payment.chequeNumber}"/></td>
						<td><c:out value="${payment.chequeDate}"/></td>
						<td><c:out value="${payment.chequeAccount}"/></td>
						<td><c:out value="${payment.note}"/></td>
						<td><a class="btn btn-warning btn-xs"
							href="${pageContext.request.contextPath}/admin/merchant/deletePayment/${payment.merchant.id}/${payment.id}"><span
								class=" glyphicon glyphicon-trash"> </span> <spring:message
									code="crud.delete" /></a> 

									
									</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



