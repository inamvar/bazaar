<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-6 col-md-6 col-sm-offset-3 col-md-offset-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<p class="panel-title">
					<spring:message code="security.resetpass.title" />
				</p>


			</div>
			<div class="panel-body">
				<p>
					<spring:message code="security.resetpass.message" />
				</p>
				<form:form name='f'
					action="${pageContext.request.contextPath}/resetpass" method='POST'>
					<div class="text text-danger">
						<p>
							<form:errors path="*" />
						</p>
					</div>

					<div class="form-group">
						<p>
							<spring:message code="security.username" />
							:
						</p>
						<input class="form-control input-sm" type='text' name='email'
							value=''>
					</div>

					<div class="form-group">

						<input class="btn btn-danger btn-sm" name="submit" type="submit"
							value="<spring:message code="submit"/>" />
					</div>

				</form:form>
			</div>
		</div>
	</div>
</div>



