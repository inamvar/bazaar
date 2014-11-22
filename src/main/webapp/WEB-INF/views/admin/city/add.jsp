<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-5">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="city.insert.message" />
			</div>
			<div class="panel-body">
				<form:form method="GET" commandName="city" class="form" role="form"
					action="${pageContext.request.contextPath}/admin/city/add">
					<div class="form-group">
						<p>
							<spring:message code="country.label" />
						</p>
						<form:select path="province.country.id"
							cssClass="form-control input-sm" onchange="this.form.submit()"
							itemValue="id" itemLabel="name">
							<form:option value="-1" label="----" />
							<form:options items="${countries}" itemValue="id"
								itemLabel="name" />
						</form:select>
					</div>
				</form:form>


				<c:if test="${not empty country }">
					<form:form commandName="city" class="form" role="form"
						action="${pageContext.request.contextPath}/admin/city/add">

						<div class="form-group">
							<p>
								<spring:message code="province.label" />
							</p>
							<form:select class="form-control input-sm" path="province"
								items="${country.provinces}" itemValue="id" itemLabel="name"></form:select>
						</div>

						<div class="form-group">
							<p>
								<spring:message code="city.name" />
							</p>
							<form:input class="form-control input-sm" path="name"></form:input>
							<form:errors path="name" cssClass="text text-danger" />
						</div>
						<div class="form-group">
							<input class="btn btn-danger btn-sm"
								value="<spring:message code="submit"/>" type="submit"> <a
								href="${pageContext.request.contextPath}/admin/city"
								class="btn btn-default btn-sm"><spring:message code="cancel" /></a>
						</div>


					</form:form>
				</c:if>
			</div>
		</div>
	</div>

</div>



