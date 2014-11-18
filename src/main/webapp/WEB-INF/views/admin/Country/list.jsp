<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<spring:message code="country.list" />
	</h2>
	<hr />
	<div class="col-sm-6 col-md-6">
		<p>
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/admin/country/add"><spring:message
					code="crud.add" /></a>
		</p>

		<table class="table table-bordered">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="country.code" /></th>
					<th><spring:message code="country.name" /></th>
					<th><spring:message code="province.name" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="country" items="${countries}">
					<tr>
						<td><c:out value="${country.code}" /></td>
						<td><c:out value="${country.name}" /></td>
						<td><c:if test="${not empty country.provinces }">

								<c:forEach items="${country.provinces }" var="province">
									<p>${province.name}</p>
								</c:forEach>
							</c:if></td>
						<td><a
							href="${pageContext.request.contextPath}/admin/country/delete/${country.id}"><spring:message
									code="crud.delete" /></a> <a
							href="${pageContext.request.contextPath}/admin/country/update/${country.id}"><spring:message
									code="crud.edit" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



