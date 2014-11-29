<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">

	<c:if test="${not empty successMsg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${successMsg}</p>
		</div>

	</c:if>

	<div class="row">
		<c:forEach items="${deals}" var="deal">
			<div class="col-xs-18 col-sm-4 col-md-3">
				<div class="productbox">
					<div class="imgthumb img-responsive">
						<img
							src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=300&height=250">
					</div>
					<div class="caption">
						<h5>${deal.name}</h5>
						<s class="text-muted">${deal.price}</s>
						<c:if test="${not empty deal.options}">
							<b class="finalprice"> ${deal.options[0].price}  <spring:message code="kalatag.currenncy"/> </b>
							<!-- from <b>Amazon</b> -->
						</c:if>
<!-- 						<a href="#" class="btn btn-default btn-xs pull-right"
							role="button"><i class="glyphicon glyphicon-zoom-in"></i></a> -->
						<p>
							<button type="button" class="btn btn-success btn-md btn-block">
								<spring:message code="kalatag.viewdeal" />
							</button>
						</p>
					</div>
					<c:if test="${not empty deal.options}">
						<div class="saleoffrate">
							<b>${deal.options[0].discount} %</b><br>OFF
						</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</div>




