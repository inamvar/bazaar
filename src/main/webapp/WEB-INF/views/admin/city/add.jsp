<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<h2>
		<spring:message code="city.insert.message" />
	</h2>
	<c:if test="${not empty errors}">
		<div class="alert alert-danger">
			<c:forEach var="error" items="errors">
				<p>${error}</p>
			</c:forEach>
		</div>
	</c:if>
	<form:form method="GET" commandName="city"
		action="${pageContext.request.contextPath}/admin/city/add">
		<label><spring:message code="country.label" /></label>
		<form:select path="province.country.id" onchange="this.form.submit()"
			itemValue="id" itemLabel="name">
			<form:option value="-1"
				label="----" />
			<form:options items="${countries}" itemValue="id" itemLabel="name"/>
		</form:select>

	</form:form>
	<c:if test="${not empty country }">
		<form:form method="POST" commandname="city"
			action="${pageContext.request.contextPath}/admin/city/add">
			<table>
				<tbody>
					<tr>
						<td><spring:message code="province.label" /></td>
						<td><form:select class="form-control input-sm"
								path="province" items="${country.provinces}" itemValue="id"
								itemLabel="name"></form:select></td>
					</tr>
					<tr>
						<td><spring:message code="city.name" /></td>
						<td><form:input class="form-control input-sm" path="name"></form:input></td>
					</tr>
					<tr>
						<td></td>
						<td><input class="btn btn-danger btn-sm"
							value="<spring:message code="submit"/>" type="submit"> <a
							href="${pageContext.request.contextPath}/admin/city"
							class="btn btn-default btn-sm"><spring:message code="cancel" /></a></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</c:if>
</div>



