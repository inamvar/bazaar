<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<div class="col-sm-6 col-sm-offset-3">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="merchant.payment.insert" />  < ${payment.merchant.contactPoint.firstName} ${payment.merchant.contactPoint.lastName}>
			</div>
			<div class="panel-body">

				
					<form:form commandName="payment" class="form" role="form" method="POST"
						action="${pageContext.request.contextPath}/admin/merchant/insertPayment/${payment.merchant.id}">

						<div class="form-group">
							<p>
								<spring:message code="merchant.payment.amount" />
							</p>
							<form:input class="form-control input-sm" path="amount"></form:input>
							<form:errors path="amount" cssClass="text text-danger" />
						</div>

						<div class="form-group">
							<p>
								<spring:message code="merchant.payment.paymentDate" />
							</p>
							
							
								<div class="input-group">
								<span class="btn btn-default" id="date_btn"><i
									class="icon-calendar"></i> </span> <span id="display_area"
									class="form-control input-sm"></span>
							</div>
							<form:input class="form-control input-sm" id="date_input"
								type="hidden" path="paymentDate" />

							<script type="text/javascript">
								Calendar.setup({
									inputField : "date_input", // id of the input field
									displayArea : "display_area",
									ifFormat : "%Y/%m/%d", // format of the input field
									dateType : 'jalali',
									ifDateType : 'gregorian',
									button : "date_btn",
									langNumbers : true,
									weekNumbers : false
								});
							</script>

							
							
							<form:errors path="paymentDate" cssClass="text text-danger" />
						</div>
							<div class="form-group">
							<p>
								<spring:message code="merchant.payment.chequeNumber" />
							</p>
							<form:input class="form-control input-sm" path="chequeNumber"></form:input>
							<form:errors path="chequeNumber" cssClass="text text-danger" />
						</div>
						
							<div class="form-group">
							<p>
								<spring:message code="merchant.payment.chequeDate" />
							</p>
						    
							
								<div class="input-group">
								<span class="btn btn-default" id="date_btn_cheque"><i
									class="icon-calendar"></i> </span> <span id="display_area_cheque"
									class="form-control input-sm"></span>
							</div>
							<form:input class="form-control input-sm" id="date_input_cheque"
								type="hidden" path="paymentDate" />

							<script type="text/javascript">
								Calendar.setup({
									inputField : "date_input_cheque", // id of the input field
									displayArea : "display_area_cheque",
									ifFormat : "%Y/%m/%d", // format of the input field
									dateType : 'jalali',
									ifDateType : 'gregorian',
									button : "date_btn_cheque",
									langNumbers : true,
									weekNumbers : false
								});
							</script>

							
							
							<form:errors path="chequeDate" cssClass="text text-danger" />
						</div>
							<div class="form-group">
							<p>
								<spring:message code="merchant.payment.chequeAccount" />
							</p>
							<form:input class="form-control input-sm" path="chequeAccount"></form:input>
							<form:errors path="chequeAccount" cssClass="text text-danger" />
						</div>
						
						<div class="form-group">
							<p>
								<spring:message code="merchant.payment.referenceNumber" />
							</p>
							<form:input class="form-control input-sm" path="referenceNumber"></form:input>
							<form:errors path="referenceNumber" cssClass="text text-danger" />
						</div>
							<div class="form-group">
							<p>
								<spring:message code="merchant.payment.note" />
							</p>
							<form:textarea class="form-control input-sm" path="note" rows="3" />
							<form:errors path="note" cssClass="text text-danger" />
						</div>
						<div class="form-group">
							<input class="btn btn-danger btn-sm"
								value="<spring:message code="submit"/>" type="submit"> <a
								href="${pageContext.request.contextPath}/admin/merchant"
								class="btn btn-default btn-sm"><spring:message code="cancel" /></a>
						</div>


					</form:form>
			
			</div>
		</div>
	</div>

</div>



