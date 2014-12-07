<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">



	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="customer.list" />
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
		<p>
			<a class="btn btn-danger btn-sm"
				href="${pageContext.request.contextPath}/register"><span
				class=" glyphicon glyphicon-plus-sign"> </span> <spring:message
					code="crud.add" /></a>
		</p>

		<table class="table">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="person.firstname" /></th>
					<th><spring:message code="person.lastname" /></th>
					<th><spring:message code="person.username" /></th>
					<th><spring:message code="person.enable" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="customer" items="${customers}">
					<tr>
						<td><c:out value="${customer.firstName}" /></td>
						<td><c:out value="${customer.lastName}" /></td>
							<td><c:out value="${customer.username}" /></td>
							<td><c:out value="${customer.enabled}" /></td>

						<td><a class="btn btn-warning btn-xs"
							href="${pageContext.request.contextPath}/admin/customer/delete/${customer.id}"><span
								class=" glyphicon glyphicon-trash"> </span> <spring:message
									code="crud.delete" /></a> <a class="btn btn-success btn-xs"
							href="${pageContext.request.contextPath}/admin/customer/update/${customer.id}"><span
								class=" glyphicon glyphicon-pencil"> </span> <spring:message
									code="crud.edit" /></a>
									<a class="btn btn-default btn-xs"
							href="${pageContext.request.contextPath}/admin/customer/resetpassword?email=${customer.username}"><span
								class="glyphicon glyphicon-lock"> </span> <spring:message
									code="customer.resetpassword" /></a>
									</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



