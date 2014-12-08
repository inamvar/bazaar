<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="deal.list" />
	</h2>
	<hr />
	<div class="col-sm-12 col-md-12">
		<p>
			<a class="btn btn-danger btn-sm"
				href="${pageContext.request.contextPath}/admin/deal/add"><span
				class=" glyphicon glyphicon-plus-sign"> </span> <spring:message
					code="crud.add" /></a>
		</p>

		<table class="table">
			<thead class="table-heading">
				<tr>

					<th><spring:message code="deal.name" /></th>
					<th><spring:message code="deal.price" /></th>
					<th><spring:message code="deal.status" /></th>
					<th><spring:message code="deal.category" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="deal" items="${deals}">
					<tr>

						<td><a href="${pageContext.request.contextPath}/admin/deal/detail/${deal.id}"> <c:out value="${deal.name}" /> </a></td>
						<td><c:out value="${deal.price}" /></td>
						<td><c:out value="${deal.status}" /></td>
						<td><c:out value="${deal.category.name}" /></td>

						<td><a class="btn btn-warning btn-xs"
							href="${pageContext.request.contextPath}/admin/deal/delete/${deal.id}"><span
								class=" glyphicon glyphicon-trash"> </span> <spring:message
									code="crud.delete" /></a> <a class="btn btn-success btn-xs"
							href="${pageContext.request.contextPath}/admin/deal/update/${deal.id}"><span
								class=" glyphicon glyphicon-pencil"> </span> <spring:message
									code="crud.edit" /></a>
									<a class="btn btn-default btn-xs"
							href="${pageContext.request.contextPath}/admin/order?deal=${deal.id}"><span
								class="glyphicon glyphicon-list"> </span> <spring:message
									code="deal.orders" /></a>
									</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



