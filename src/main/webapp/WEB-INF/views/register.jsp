<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="container">

	<div class="col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<p class="panel-title">
					<spring:message code="customer.register.message" />
				</p>

			</div>
			<div class="panel-body">

				<form:form name='f'
					action="${pageContext.request.contextPath}/register"
					commandName="customer" method='POST'>

					<div class="col-sm-6">
						<div class="form-group">
							<p>
								<spring:message code="contact.email" />
							</p>
							<form:input path="username" class="form-control input-sm" />
							<form:errors path="username" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<p>
								<spring:message code="person.gender" />
							</p>
							<form:select class="form-control input-sm" path="gender">
								<form:options />
							</form:select>
							<form:errors path="gender" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<spring:message code="person.firstname" />
							<form:input class="form-control input-sm" path="firstName"></form:input>
							<form:errors path="firstName" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<spring:message code="person.lastname" />
							<form:input class="form-control input-sm" path="lastName"></form:input>
							<form:errors path="lastName" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<spring:message code="person.birthday" />
							<form:input class="form-control input-sm"
								placeholder="e.g 1983/10/23" path="birthday"></form:input>
							<form:errors path="birthday" cssClass="text text-danger" />
						</div>
					</div>

					<div class="col-sm-4">

						<div class="form-group">
							<spring:message code="contact.mobile" />
							<form:input class="form-control input-sm" path="contact.mobile"
								placeholder="00989120000000"></form:input>
							<form:errors path="contact.mobile" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<spring:message code="contact.phone" />
							<form:input class="form-control input-sm" path="contact.phone"
								placeholder="00982144000000"></form:input>
							<form:errors path="contact.phone" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<spring:message code="contact.city" />
							<form:select class="form-control input-sm" path="contact.city"
								items="${cities}" itemValue="id" itemLabel="name">
							</form:select>
							<form:errors path="contact.city" cssClass="text text-danger" />
						</div>
					</div>
					<div class="col-sm-2">
						<div class="form-group">

							<input class="btn btn-danger btn-block btn-sm" name="submit"
								type="submit" value="<spring:message code="submit"/>" />

						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<p></p>
							<a href="${pageContext.request.contextPath}/login"><spring:message
									code="register.already" /></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>



