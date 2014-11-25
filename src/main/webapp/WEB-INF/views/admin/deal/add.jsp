<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="container">

	<div class="col-sm-10">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-plus-sign"></span>
				<spring:message code="deal.insert.message" />
			</div>
			<div class="panel-body">
				<form:form method="POST" commandName="deal" class="form" role="form"
					enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/admin/deal/add">
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
						<div class="col-sm-3">
							<div class="form-group">
								<p>
									<spring:message code="deal.price" />
								</p>
								<form:input class="form-control input-sm" path="price"></form:input>
								<form:errors path="price" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-3">
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

						<div class="col-sm-12">
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
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.category" />
								</p>
								<form:select class="form-control input-sm" path="category"
									items="${categories}" itemValue="id" itemLabel="name">
								</form:select>
								<form:errors path="category" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-5">

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
						<div class="col-sm-3">
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

					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.mincoupon" />
								</p>
								<form:input class="form-control input-sm" path="minCoupon"></form:input>
								<form:errors path="minCoupon" cssClass="text text-danger" />
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<p>
									<spring:message code="deal.maxcoupon" />
								</p>
								<form:input class="form-control input-sm" path="maxCoupon"></form:input>
								<form:errors path="maxCoupon" cssClass="text text-danger" />
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<p class="text-info">
									<spring:message code="deal.couponlimitation" />
								</p>

							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-sm-12">
							<div class="form-group">
								<label><spring:message code="dealoption.label" /></label>
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group">
											<p>
												<spring:message code="dealoption.name" />
											</p>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<p>
												<spring:message code="delaoption.description" />
											</p>
										</div>
									</div>
																		<div class="col-sm-3">
										<div class="form-group">
											<p>
												<spring:message code="dealoption.discount" />
											</p>
										</div>
									</div>
								</div>
								<c:forEach items="${deal.options}" varStatus="loop">
									<!-- Add a wrapping div -->
									<c:choose>
										<c:when test="${deal.options[loop.index].remove eq 1}">
											<div id="options${loop.index}.wrapper" class="hidden">
										</c:when>
										<c:otherwise>
											<div id="options${loop.index}.wrapper">
										</c:otherwise>
									</c:choose>
									<!-- Generate the fields -->
									<form:input path="options[${loop.index}].name" />
									<form:input path="options[${loop.index}].description" />
									<form:input path="options[${loop.index}].discount" />
									<!-- Add the remove flag -->
									<c:choose>
										<c:when test="${options[loop.index].remove eq 1}">
											<c:set var="hiddenValue" value="1" />
										</c:when>
										<c:otherwise>
											<c:set var="hiddenValue" value="0" />
										</c:otherwise>
									</c:choose>
									<form:hidden path="options[${loop.index}].remove"
										value="${hiddenValue}" />
									<!-- Add a link to remove the Employee -->
									<a href="#" class="options.remove" data-index="${loop.index}"><spring:message
											code="crud.delete" /></a>
							</div>
							</c:forEach>

							<div class="form-group">
								<button id="add" type="button" class="btn btn-default btn-sm">
									<spring:message code="dealoption.add" />
								</button>
							</div>


						</div>
					</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<p>
							<spring:message code="deal.features" />
						</p>
						<form:textarea class="form-control input-sm" path="features"
							rows="15"></form:textarea>
						<form:errors path="features" cssClass="text text-danger" />
					</div>
				</div>

				<div class="col-sm-12">
					<div class="form-group">
						<p>
							<spring:message code="deal.termsOfUse" />
						</p>
						<form:textarea class="form-control input-sm" path="termsOfUse"
							rows="15"></form:textarea>
						<form:errors path="termsOfUse" cssClass="text text-danger" />
					</div>
				</div>
			</div>


			<div class="alert alert-warning">
				<p>
					<spring:message code="jpg.warning" />
				</p>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<p>
							<spring:message code="deal.thumbnail" />
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
							value="<spring:message code="submit"/>" type="submit"> <a
							href="${pageContext.request.contextPath}/admin/deal"
							class="btn btn-default btn-sm"><spring:message code="cancel" /></a>
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
<script type="text/javascript">
    $(function() {

        // Start indexing at the size of the current list
        var index = ${fn:length(deal.options)};
       
 			//var index =0;
        // Add a new option
        $("#add").off("click").on("click", function() {
            $(this).before(function() {
            	console.log("adding option at index:"+index+" ...");
                var html = '<div id="options' + index + '.wrapper">';    
                html +='<div class="col-sm-4">';
                html +='<div class="form-group">';
               
                html += '<input class="form-control input-sm" type="text" id="options' + index + '.name" name="options[' + index + '].name" />';
                html +='</div></div>';
                html +='<div class="col-sm-4">';
                html +='<div class="form-group">';
                html += '<input class="form-control input-sm" type="text" id="options' + index + '.description" name="options[' + index + '].description" />';
                html +='</div></div>';
                html +='<div class="col-sm-3">';
                html +='<div class="form-group">';
                html += '<input class="form-control input-sm" type="text" id="options' + index + '.discount" name="options[' + index + '].discount" />';
                html +='</div></div>';
                html +='<div class="col-sm-1">';
                html +='<div class="form-group">';
                html += '<input class="form-control input-sm" type="hidden" id="options' + index + '.remove" name="options[' + index + '].remove" value="0" />';
                html += '<a href="#" class="options.remove" data-index="' + index + '">Delete</a>';  
                html +='</div></div>';
                html += "</div>";
                return html;
            });
            $("#options" + index + "\\.wrapper").show();
            index++;
            return false;
        });

        // Remove an option
        $('body').off("click").on("click","a.options\\.remove", function() {
        	
            var index2remove = $(this).data("index");
            console.log("index2remove:"+index2remove);
            $("#options" + index2remove + "\\.wrapper").hide();
            $("#options" + index2remove + "\\.remove").val("1");
            return false;
        });

    });
    </script>




<script src="//tinymce.cachefly.net/4.1/tinymce.min.js"></script>
<script>
	tinymce
			.init({
				selector : 'textarea',
				plugins : "directionality, image, link, table, textcolor",
				toolbar : "undo redo styleselect bold italic link image ltr rtl textcolor  table forecolor backcolor  "

			});
</script>



