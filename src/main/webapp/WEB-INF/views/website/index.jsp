<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<div class="col-sm-3">
			<div class="sidebar-nav">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header"><spring:message
								code="admin.menu.definitions.categories" /></li>
						<li class="active"><a href="index"><i
								class="glyphicon glyphicon-th"></i> <spring:message
									code="deal.all" /></a></li>
						<c:forEach items="${categories}" var="category">
							<li><a href="#"><i class="icon-edit"></i>${category.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		<div class="col-sm-9">


			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="http://placehold.it/900x300" alt="...">
						<div class="carousel-caption">...</div>
					</div>
					<div class="item">
						<img src="http://placehold.it/900x300" alt="...">
						<div class="carousel-caption">...</div>
					</div>
					...
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>




			<c:forEach items="${deals}" var="deal">
				<div class="col-xs-12 col-sm-4 col-md-4">
					<div class="productbox">
						<div class="imgthumb img-responsive">
							<img
								src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=300&height=250">
						</div>
						<div class="caption">
							<h5>${deal.name}</h5>
							<s class="text-muted"><fmt:formatNumber type="number"
									maxFractionDigits="0" value="${deal.price}" /></s>
							<c:if test="${not empty deal.options}">
								<b class="finalprice"> <fmt:formatNumber type="number"
										maxFractionDigits="0" value="${deal.options[0].price}" /> <spring:message
										code="kalatag.currenncy" />
								</b>
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
								<b><fmt:formatNumber type="number" maxFractionDigits="0"
										value=" ${deal.options[0].discount}" /> %</b><br>
								<spring:message code="kalatag.off" />
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>




