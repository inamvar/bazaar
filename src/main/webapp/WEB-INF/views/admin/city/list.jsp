<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<spring:message code="city.list" />
	</h2>
	<hr />
	<div class="col-sm-6 col-md-6">
	
	
		<p>
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/admin/city/add"><spring:message
					code="crud.add" /></a>
		</p>

		<table class="table table-bordered">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="city.name" /></th>
					<th><spring:message code="province.label" /></th>
					<th><spring:message code="country.label" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="city" items="${cities}">
					<tr>
						<td><c:out value="${city.name}" /></td>
						<td><c:out value="${city.province.name}" /></td>
						<td><c:out value="${city.province.country.name}" /></td>
						<td><a
							href="${pageContext.request.contextPath}/admin/city/delete/${city.id}"><spring:message
									code="crud.delete" /></a> <a
							href="${pageContext.request.contextPath}/admin/city/update/${city.id}"><spring:message
									code="crud.edit" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



