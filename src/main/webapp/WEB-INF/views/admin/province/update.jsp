<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

<h2><spring:message code="province.update.message"/></h2>
<form:form method="POST" commandName="province" action="${pageContext.request.contextPath}/admin/province/update/${province.id}">

<table>
<tbody>
	<tr>
		<td><spring:message code="province.name"/></td>
		<td><form:input class="form-control input-sm" path="name"></form:input></td>
	</tr>
	<tr>
	<td><spring:message code="province.country"/></td>
		<td><form:select  class="form-control input-sm" path="country" items="${countries}" itemValue="id" itemLabel="name" value="${province.country.id}"></form:select></td>
		
	</tr>
	<tr>
		<td></td>
		<td>
		<input  class="btn btn-danger btn-sm" value="<spring:message code="submit"/>" type="submit">
		<a href="${pageContext.request.contextPath}/admin/province" class="btn btn-default btn-sm"><spring:message code="cancel"/></a></td>
	</tr>
</tbody>
</table>
</form:form>
</div>



