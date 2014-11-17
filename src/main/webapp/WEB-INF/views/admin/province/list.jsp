<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<spring:message code="province.list" />
	</h2>
	<hr />
 			<div class="col-sm-6 col-md-6">
			<p>	<a class="btn btn-danger"
		href="${pageContext.request.contextPath}/admin/province/add"><spring:message
			code="crud.add" /></a></p>

	<table class="table table-bordered">
		<thead class="table-heading">
			<tr>
				<th><spring:message code="province.name" /></th>
				<th><spring:message code="province.code" /></th>
				<th><spring:message code="province.country" /></th>
				<th></th>
			</tr>
		</thead>
		<tbody class="table-body">
			<c:forEach var="province" items="${provinces}">
				<tr>
					<td><c:out value="${province.code}" /></td>
					<td><c:out value="${province.name}" /></td>
					<td><c:out value="${province.country.name}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/admin/province/delete/${province.id}"><spring:message
								code="crud.delete" /></a>
								<a
						href="${pageContext.request.contextPath}/admin/province/update/${province.id}"><spring:message
								code="crud.edit" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>



