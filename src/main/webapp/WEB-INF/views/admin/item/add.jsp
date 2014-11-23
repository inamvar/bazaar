<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-plus-sign"></span>
				<spring:message code="item.insert.message" />
			</div>
			<div class="panel-body">
				<form:form method="POST" commandName="item" class="form" role="form"
					enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/admin/item/add">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="item.name" />
								</p>
								<form:input class="form-control input-sm" path="name"></form:input>
								<form:errors path="name" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="item.price" />
								</p>
								<form:input class="form-control input-sm" path="price"></form:input>
								<form:errors path="price" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="item.status" />
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
									<spring:message code="item.rate" />
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
									<spring:message code="item.description" />
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
									<spring:message code="item.finePrint" />
								</p>
								<form:textarea class="form-control input-sm" path="finePrint"></form:textarea>
								<form:errors path="finePrint" cssClass="text text-danger" />
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="item.details" />
								</p>
								<form:textarea class="form-control input-sm" path="details"></form:textarea>
								<form:errors path="details" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<p>
									<spring:message code="item.category" />
								</p>
								<form:select class="form-control input-sm" path="category"
									items="${categories}" itemValue="id" itemLabel="name">
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
									items="${merchants}" itemValue="id" itemLabel="name">
								</form:select>
								<form:errors path="merchant" cssClass="text text-danger" />
							</div>


						</div>

					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<p>
									<spring:message code="item.thumbnail" />
								</p>
								<%-- <form:input type="file" path="thumbnail"></form:input> --%>
								<input name="file" type="file" />
								<form:errors path="thumbnail" cssClass="text text-danger" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="form-group">
								<a id="addFile" class="btn btn-default btn-sm" type="button">
									<span class="glyphicon glyphicon-paperclip"></span> <spring:message
										code="attachment.add" />
								</a>
								<p></p>
								<table id="fileTable" class="table">

								</table>

							</div>


						</div>
					</div>
					<div class="row">

						<div class="col-sm-4">
							<div class="form-group">
								<input class="btn btn-danger btn-sm"
									value="<spring:message code="submit"/>" type="submit">
								<a href="${pageContext.request.contextPath}/admin/item"
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
</script>



