<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-4 col-sm-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="security.password.change.message" />
			</div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath}/changepassword"
					method="POST">
					<div class="form-group">
						<input class="form-control input-sm" type="password"
							name="new_password" />
					</div>
					<div class="form-group">
						<button class="btn btn-danger btn-sm">
							<spring:message code="submit" />
						</button>
					</div>
				</form>
			</div>

		</div>
	</div>


</div>



