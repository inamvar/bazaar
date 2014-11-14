<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

<p><spring:message code="country.insert.message"/></p>
<form:form method="POST" commandname="country" action="${pageContext.request.contextPath}/country/add">
<table>
<tbody>
	<tr>
		<td><spring:message code="country.name"/></td>
		<td><form:input path="name"></form:input></td>
	</tr>
	<tr>
		<td><spring:message code="country.code"/></td>
		<td><form:input path="code"></form:input></td>
	</tr>
	<tr>
		<td><input value="<spring:message code="crud.add"/>" type="submit"></td>
		<td></td>
	</tr>
</tbody>
</table>
</form:form>
</div>



