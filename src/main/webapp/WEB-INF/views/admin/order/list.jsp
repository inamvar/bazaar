<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">



	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="order.list" />
	</h2>
	<hr />

	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>

	</c:if>
	<div class="col-sm-12 col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading"><span class="icon-search"></span> <spring:message code="report.search"/></div>
			<div class="panel-body">
				<form action="${pageContext.request.contextPath}/admin/order"
					method="POST" class="form">
					<div class="col-sm-4">
					<div class="form-group">
						<label for="order_id"><spring:message code="order.id" /></label>
						<input type="number" class="form-control" id="order_id"
							name="order_id" value="0" />
					</div>


					<div class="form-group">
						<label for="startDate"><spring:message code="date.start" /></label>
						<div class="input-group">
						<span class="btn btn-default" id="date_btn_start"><i
							class="icon-calendar"> </i> </span> <span id="display_area_start"
							class="form-control input-sm"> </span> 
							</div>
							
							<input
							class="form-control input-sm" id="date_input_start" type="hidden"
							name="startDate" />

						<script type="text/javascript">
							Calendar.setup({
								inputField : "date_input_start", // id of the input field
								displayArea : "display_area_start",
								ifFormat : "%Y/%m/%d", // format of the input field
								dateType : 'jalali',
								ifDateType : 'gregorian',
								button : "date_btn_start",
								langNumbers : true,
								weekNumbers : false
							});
						</script>

					</div>
					<div class="form-group">
						<label for="endDate"><spring:message code="date.end" /></label>
						<div class="input-group">
						 <span
							class="btn btn-default" id="date_btn_end"><i
							class="icon-calendar"></i> </span> <span id="display_area_end"
							class="form-control input-sm"></span>
							</div>
							
							 <input
							class="form-control input-sm" id="date_input_end" type="hidden"
							name="endDate" />

						<script type="text/javascript">
							Calendar.setup({
								inputField : "date_input_end", // id of the input field
								displayArea : "display_area_end",
								ifFormat : "%Y/%m/%d", // format of the input field
								dateType : 'jalali',
								ifDateType : 'gregorian',
								button : "date_btn_end",
								langNumbers : true,
								weekNumbers : false
							});
						</script>
					</div>
					</div>
					<div class="col-sm-4">
					<div class="form-group">
						<label for="customer_firstname"><spring:message
								code="person.firstname" /></label> <input type="text"
							class="form-control" id="customer_firstname"
							name="customer_firstname" />
					</div>
					<div class="form-group">
						<label for="customer_lastname"><spring:message
								code="person.lastname" /></label> <input type="text"
							class="form-control" id="customer_lastname"
							name="customer_lastname" />
					</div>
					<div class="form-group">
						<label for="merchant_name"><spring:message
								code="person.firstname" /> <spring:message
								code="order.merchant" /></label> <input type="text"
							class="form-control" id="merchant_name" name="merchant_name" />
					</div>
					</div>
					<div class="col-sm-4">
					<div class="form-group">
						<label for="deal_name"><spring:message code="deal.name" /></label>
						<input type="text" class="form-control" id="deal_name"
							name="deal_name" />
					</div>
					<div class="form-group">
					<label></label>
						<button type="submit" class="btn btn-danger">
							<spring:message code="report.create" />
						</button>
					</div>
					</div>
				</form>
			</div>
		</div>



	</div>
	<div class="col-sm-12 col-md-12">

		<table class="table">
			<thead class="table-heading">
				<tr>
					<th><spring:message code="order.id" /></th>
					<th><spring:message code="order.date" /></th>
					<th><spring:message code="order.status" /></th>
					<th><spring:message code="order.customer" /></th>
					<th><spring:message code="order.merchant" /></th>
					<th><spring:message code="order.dealName" /></th>
					<th><spring:message code="order.totalprice" /> (<spring:message
							code="kalatag.currency" />)</th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="order" items="${orders}">
					<tr>
						<td><c:out value="${order.id}" /></td>
						<td><span class="persian-date"><fmt:formatDate
									pattern="yyyy/MM/dd" value="${order.orderDate}" /> </span> <span><fmt:formatDate
									type="time" value="${order.orderDate}" /> </span></td>
						<td><c:out value="${order.status}" /></td>
						<td><c:out
								value="${order.person.firstName} ${order.person.lastName}" /></td>
						<td><c:out value="${order.deal.merchant.name}" /></td>
						<td><c:out value="${order.deal.name}" /></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="0"
								value=" ${order.totalPrice}" /></td>
						<td><a
							href="${pageContext.request.contextPath}/admin/order/detail/${order.id}"><span
								class="glyphicon glyphicon-th"> </span></a> <a
							class="btn btn-danger btn-xs"
							href="${pageContext.request.contextPath}/admin/order/delete/${order.id}"><span
								class="glyphicon glyphicon-trash"> </span></a>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>



