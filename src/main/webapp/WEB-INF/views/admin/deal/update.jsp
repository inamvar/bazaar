<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-plus-sign"></span>
				<spring:message code="deal.update.message" />
			</div>
			<div class="panel-body">
				<form:form method="POST" commandName="deal" class="form" role="form"
					enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/admin/deal/update/${deal.id}">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.name" />
								</p>
								<form:input class="form-control input-sm" path="name"></form:input>
								<form:errors path="name" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.price" />
								</p>
								<form:input class="form-control input-sm" path="price"></form:input>
								<form:errors path="price" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.status" />
								</p>
								<form:select class="form-control input-sm" path="status">
									<form:options />
								</form:select>
								<form:errors path="price" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.rate" />
								</p>
								<form:select class="form-control input-sm" path="rate">
									<form:options />
								</form:select>
								<form:errors path="rate" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-8">
							<div class="form-group">
								<p>
									<spring:message code="deal.description" />
								</p>
								<form:input class="form-control input-sm" path="description"></form:input>
								<form:errors path="description" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.features" />
								</p>
								<form:textarea class="form-control input-sm" path="finePrint"></form:textarea>
								<form:errors path="features" cssClass="text text-danger" />
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.termOfUse" />
								</p>
								<form:textarea class="form-control input-sm" path="details"></form:textarea>
								<form:errors path="termOfUse" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="deal.category" />
								</p>
								<form:select class="form-control input-sm" path="category"
									deals="${categories}" dealValue="id" dealLabel="name">
								</form:select>
								<form:errors path="category" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-6">

							<div class="form-group">
								<p>
									<spring:message code="merchant.label" />
								</p>
								<form:select class="form-control input-sm" path="merchant"
									deals="${merchants}" dealValue="id" dealLabel="name">
								</form:select>
								<form:errors path="merchant" cssClass="text text-danger" />
							</div>


						</div>

					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<p>
									<spring:message code="deal.thumbnail" />
								</p>
								<img src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=100&height=100" width="100" height="100" class="img img-thumbnail"/>
								<input name="file" type="file" />
								<form:errors path="thumbnail" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-10">
							<div class="form-group">


								<a id="addFile" class="btn btn-default btn-sm" type="button">
									<span class="glyphicon glyphicon-paperclip"></span> <spring:message
										code="attachment.add" />
								</a>
								<p></p>
								<table id="fileTable" class="table">
									<c:forEach items="${deal.images}" varStatus="image" var="imageObj">
										<tr>
											<td><form:hidden path="images[${image.index}].name"></form:hidden>
												<form:hidden path="images[${image.index}].id"></form:hidden>
												<img src="${pageContext.request.contextPath}/files/attachments/<c:out value="${imageObj.id}"/>/dynamicImage?width=50&height=50" width="150" height="100" class="img img-rounded"/>
												<form:input readonly="true"
													path="images[${image.index}].fileName"></form:input></td>
											<td><a type="button" class="text text-danger"
												onclick="javascript:removeOldAtt(this);"><span
													class="glyphicon glyphicon-remove"></span> </a></td>
										</tr>
									</c:forEach>
								</table>

							</div>


						</div>
					</div>
					<div class="row">

						<div class="col-sm-4">
							<div class="form-group">
								<input class="btn btn-danger btn-sm"
									value="<spring:message code="submit"/>" type="submit">
								<a href="${pageContext.request.contextPath}/admin/deal"
									class="btn btn-default btn-sm"><spring:message
										code="cancel" /></a>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script>
	$(document)
			.ready(
					function() {
						//add more file components if Add is clicked
						$('#addFile')
								.click(
										function() {
											var fileIndex = $('#fileTable tr')
													.children().length - 1;
											$('#fileTable')
													.append(
															'<tr id="idx'+fileIndex+'"><td>'
																	+ '   <input type="file" name="files"/>'

																	+ '</td>'
																	+ '<td><a  class="text text-danger" onclick="javascript:removeAtt(this);" ><span class="glyphicon glyphicon-remove"></span></a></td>'
																	+ '</tr>');
										});

					});

	function removeAtt(el) {

		$(el).parent().parent().remove();
	};

	function removeOldAtt(el) {
		console.log("marked as deleted");
		var tmp = $(el).parent().parent().find("input[type=hidden]:first").val(
				"deleted");
		$(el).parent().parent().hide();
	};
</script>

