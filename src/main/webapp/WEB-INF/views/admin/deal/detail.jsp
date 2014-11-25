<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-8 col-sm-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-exclamation-sign"></span>
				<spring:message code="deal.detail.message" />
			</div>
			<div class="panel-body">
				<form:form method="POST" commandName="deal" class="form" role="form"
					action="#">
					<div class="row">
						<div class="col-sm-12">
							<div id="carousel-example-generic" class="carousel slide" style="min-height:450;"
								data-ride="carousel">

								<div class="carousel-inner" role="listbox">
									<c:forEach items="${deal.images}" var="image" varStatus="stat">
										<c:if test="${stat.index == 0}">
											<div class="deal active">
												<img
													src="${pageContext.request.contextPath}/files/attachments/${image.id}/dynamicImage?width=900&height=450"
													alt="">

											</div>
										</c:if>
										<c:if test="${stat.index > 0}">
											<div class="deal">
												<img
													src="${pageContext.request.contextPath}/files/attachments/${image.id}/dynamicImage?width=900&height=450"
													alt="">

											</div>
										</c:if>

									</c:forEach>
								</div>


								<!-- Controls -->
								<a class="left carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
									<span class="sr-only">Previous</span>
								</a> <a class="right carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
						</div>
						
					</div>
					<hr/>
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
								<p>
									<spring:message code="deal.thumbnail" />
								</p>
								<img
									src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=100&height=100"
									width="110" height="110" class="img img-thumbnail" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.name" />
								</p>
								<form:input class="form-control input-sm " path="name"
									disabled="true"></form:input>
								<form:errors path="name" cssClass="text text-danger" />
							</div>
							<div class="form-group">
								<p>
									<spring:message code="deal.price" />
								</p>
								<form:input class="form-control input-sm  disabled" path="price"
									disabled="true"></form:input>
								<form:errors path="price" cssClass="text text-danger" />
							</div>
						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<p>
									<spring:message code="deal.status" />
								</p>
								<form:input class="form-control input-sm  disabled"
									disabled="true" path="status"></form:input>

							</div>
							<div class="form-group">
								<p>
									<spring:message code="deal.rate" />
								</p>
								<form:input class="form-control input-sm  disabled" path="rate"
									disabled="true"></form:input>
							</div>
						</div>
					</div>
					<div class="row">

						<div class="col-sm-12">
							<div class="form-group">
								<p>
									<spring:message code="deal.description" />
								</p>
								<form:input class="form-control input-sm  disabled"
									path="description" disabled="true"></form:input>
								<form:errors path="description" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.finePrint" />
								</p>
								<form:textarea class="form-control input-sm  disabled"
									disabled="true" path="finePrint"></form:textarea>
								<form:errors path="finePrint" cssClass="text text-danger" />
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.details" />
								</p>
								<form:textarea class="form-control input-sm  disabled"
									disabled="true" path="details"></form:textarea>
								<form:errors path="details" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.category" />
								</p>
								<form:input class="form-control input-sm  disabled"
									disabled="true" path="category.name"></form:input>

								<form:errors path="category" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-6">

							<div class="form-group">
								<p>
									<spring:message code="merchant.label" />
								</p>
								<form:input class="form-control input-sm  disabled"
									disabled="true" path="merchant.name"></form:input>

								<form:errors path="merchant" cssClass="text text-danger" />
							</div>


						</div>

					</div>


					<div class="row">

						<div class="col-sm-4">
							<div class="form-group">
								<a href="${pageContext.request.contextPath}/admin/deal"
									class="btn btn-default btn-sm"><spring:message
										code="deal.list" /></a>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>



