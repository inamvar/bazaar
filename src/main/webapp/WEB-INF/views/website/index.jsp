<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<div class="container">
	<div class="alert alert-warning">
		<h3><span class="glyphicon glyphicon-warning-sign"></span> <b>توجه: </b>سایت در دست ساخت است. کلیه کالابرگ ها آزمایشی و غیر واقعی هستند</h3>
	</div>

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
					<ul class="nav nav-tabs nav-stacked nav-coupon-category">
						<li class="nav-header"><spring:message
								code="admin.menu.definitions.categories" /></li>
						<li class="active"><a href="${pageContext.request.contextPath}"><i
								class="glyphicon glyphicon-th"></i> <spring:message
									code="deal.all" /></a></li>
						<c:forEach items="${categories}" var="category">
							<li><a href="${pageContext.request.contextPath}?category=${category.id}"><i class="icon-edit"></i>${category.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		<div class="col-sm-9">


			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">


				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${featureds}" var="featured" varStatus="status">
						<c:if test="${status.index == 0}">
							<div class="item active">
								<img
									src="${pageContext.request.contextPath}/files/attachments/${featured.images[0].id}/dynamicImage?width=900&height=350"
									alt="${featured.name}">
								<div class="carousel-caption">
									<h3>${featured.name}</h3>
									<p>${featured.description}</p>
									<a href="#" class="btn btn-success btn-md btn-block"><spring:message
											code="kalatag.viewdeal" /></a>
								</div>
								<c:if test="${not empty featured.options}">
									<div class="saleoffrate">
										<b><fmt:formatNumber type="number" maxFractionDigits="0"
												value=" ${featured.options[0].discount}" /> %</b><br>
									<%-- 	<spring:message code="kalatag.off" /> --%>
									</div>
								</c:if>
							</div>
						</c:if>
						<c:if test="${status.index > 0}">
							<div class="item">
								<img
									src="${pageContext.request.contextPath}/files/attachments/${featured.images[0].id}/dynamicImage?width=900&height=350"
									alt="${featured.name}">
								<div class="carousel-caption">
									<h3>${featured.name}</h3>
									<p>${featured.description}</p>
									<a href="#" class="btn btn-success btn-md btn-block"><spring:message
											code="kalatag.viewdeal" /></a>
								</div>
								<c:if test="${not empty featured.options}">
									<div class="saleoffrate">
										<b><fmt:formatNumber type="number" maxFractionDigits="0"
												value=" ${featured.options[0].discount}" /> %</b><br>
									<%-- 	<spring:message code="kalatag.off" /> --%>
									</div>
								</c:if>
							</div>
						</c:if>
					</c:forEach>
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
							<p>${deal.name}</p>
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
<%-- 							<p>
								<button type="button" class="btn btn-success btn-md btn-block">
									<spring:message code="kalatag.viewdeal" />
								</button>
							</p> --%>
						</div>
						<c:if test="${not empty deal.options}">
							<div class="saleoffrate">
								<b><fmt:formatNumber type="number" maxFractionDigits="0"
										value=" ${deal.options[0].discount}" /> %</b><br>
							<%-- 	<spring:message code="kalatag.off" /> --%>
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>




