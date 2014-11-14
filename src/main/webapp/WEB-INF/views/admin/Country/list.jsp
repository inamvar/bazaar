<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h1>
		<spring:message code="country.list" />
	</h1>
	<a class="btn btn-danger" href="${pageContext.request.contextPath}/country/add"><spring:message code="crud.add"/></a>
	<table>
		<thead>
			<tr>
				<th><spring:message code="country.name" /> <spring:message
						code="country.code" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="country" items="${countries}">
				<tr>
				 <td><c:out value="${country.code}"/></td>
				 <td><c:out value="${country.name}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>



