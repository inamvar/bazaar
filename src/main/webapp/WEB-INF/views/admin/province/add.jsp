<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

<h2><spring:message code="country.insert.message"/></h2>
<form:form method="POST" commandname="country" action="${pageContext.request.contextPath}/admin/country/add">
<table>
<tbody>
	<tr>
		<td><spring:message code="country.name"/></td>
		<td><form:input class="form-control input-sm" path="name"></form:input></td>
	</tr>
	<tr>
		<td><spring:message code="country.code"/></td>
		<td><form:input class="form-control input-sm" path="code"></form:input></td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input  class="btn btn-danger btn-sm" value="<spring:message code="submit"/>" type="submit">
		<a href="${pageContext.request.contextPath}/admin/country" class="btn btn-default btn-sm"><spring:message code="cancel"/></a></td>
	</tr>
</tbody>
</table>
</form:form>
</div>



