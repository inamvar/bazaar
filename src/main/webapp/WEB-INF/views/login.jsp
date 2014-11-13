<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-4 col-md-4 col-sm-offset-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<p class="panel-title">
					<spring:message code="security.login.message" />
				</p>

				<c:if test="${not empty error}">
					<div class="text text-danger errorblock">
						<spring:message code="security.login.failed" />
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					</div>
				</c:if>


			</div>
			<div class="panel-body">

				<form name='f' action="<c:url value='/j_spring_security_check' />"
					method='POST'>

				
						<div class="form-group">
							<p ><spring:message code="security.username" />: </p>
							<input class="form-control input-sm" type='text' name='j_username' value=''>
						</div>
						<div class="form-group">
							<p ><spring:message code="security.password" />:</p>
							<input class="form-control input-sm" type='password' name='j_password' />
						</div>
						<div class="form-group">
						
							<input class="btn btn-danger btn-sm" name="submit" type="submit"
								value="<spring:message code="security.login"/>" />
							<input class="btn btn-default btn-sm" name="reset" type="reset"
								value="<spring:message code="reset"/>" />
							
						</div>
					
				</form>
			</div>
		</div>
	</div>
</div>



