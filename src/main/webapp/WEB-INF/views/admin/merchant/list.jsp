<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="merchant.list" />
	</h2>
	<hr />
	<div class="col-sm-12 col-md-12">
		<p>
			<a class="btn btn-danger btn-sm"
				href="${pageContext.request.contextPath}/admin/merchant/add"><span
				class=" glyphicon glyphicon-plus-sign"> </span> <spring:message
					code="crud.add" /></a>
		</p>

		<table class="table">
			<thead class="table-heading">
				<tr>

					<th><spring:message code="merchant.name" /></th>
					<th><spring:message code="person.firstname" /></th>
					<th><spring:message code="person.lastname" /></th>
					<th><spring:message code="contact.email" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="merchant" items="${merchants}">
					<tr>

						<td><c:out value="${merchant.name}" /></td>
						<td><c:out value="${merchant.contactPoint.firstName}" /></td>
						<td><c:out value="${merchant.contactPoint.lastName}" /></td>
						<td><c:out value="${merchant.contact.email}"/></td>

						<td><a class="btn btn-warning btn-xs"
							href="${pageContext.request.contextPath}/admin/merchant/delete/${merchant.id}"><span
								class=" glyphicon glyphicon-trash"> </span> <spring:message
									code="crud.delete" /></a> <a class="btn btn-success btn-xs"
							href="${pageContext.request.contextPath}/admin/merchant/update/${merchant.id}"><span
								class=" glyphicon glyphicon-pencil"> </span> <spring:message
									code="crud.edit" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



