<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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



	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<p>${errorMsg}</p>
		</div>

	</c:if>





	<div class="col-sm-12">
		<c:if test="${not empty featureds[0].images}">
			<h3>
				<i class="icon-tag"></i>
				<spring:message code="kalatag.dealofday" />
			</h3>
			<div class="row white box">
				<div class="col-sm-12 col-md-8 col-lg-8"
					style="margin: 0; padding: 0;">

					<ul class="pgwSlider">
						<c:forEach items="${featureds[0].images}" var="featured"
							varStatus="status">
							<li><img
								src="${pageContext.request.contextPath}/files/attachments/${featured.id}/dynamicImage?width=600&height=300" /></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-sm-12 col-md-4 col-lg-4"
					style="padding-right: 15px; padding-left: 15px; padding-top: 0;">

					<h3>${featureds[0].name}</h3>
					<h4>${featureds[0].description}</h4>
					<p class="text text-info" style="font-size: 1.4em">
						<spring:message code="kalatag.realPrice" />
						<fmt:formatNumber type="number" maxFractionDigits="0"
							value="${featureds[0].price}" />
						<spring:message code="kalatag.currency" />
					</p>
					<spring:message code="kalatag.yourpay" />
					<div class="featured">
						<span class="square"> <span class="text text-success"
							style="font-size: 1.6em;"> <fmt:formatNumber type="number"
									maxFractionDigits="0" value="${featureds[0].options[0].price}" />

						</span> <spring:message code="kalatag.currency" />
						</span> <span class="discount"> <fmt:formatNumber type="number"
								maxFractionDigits="0"
								value="${featureds[0].options[0].discount}" />%
						</span>
					</div>
					<div class="countdown hidden-md"
						data-countdown="<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" 
            value="${featureds[0].validity}" />"></div>

					<a class="btn btn-success btn-block"
						href="${pageContext.request.contextPath}/detail?deal=${featureds[0].id}"><span
						class="glyphicon glyphicon-shopping-cart"></span> <spring:message
							code="kalatag.view" /></a>


				</div>

			</div>

		</c:if>
		<hr />
	</div>




	<div class="col-md-3 col-lg-3 hidden-sm hidden-xs">


		<ul class="ca-menu">
			<li><a style="color: black; font-size: 1em;"
				href="${pageContext.request.contextPath}/"><span class="ca-icon"><i
						class="glyphicon glyphicon-th"></i></span> <span class="ca-content">
						<spring:message code="deal.all" />

				</span> </a></li>
			<c:forEach items="${categories}" var="category">
				<li><a style="color: black; font-size: 1em;"
					href="${pageContext.request.contextPath}?category=${category.id}"><span
						class="ca-icon"><i class="${category.iconCss}"></i> </span> <span
						class="ca-content"> ${category.name} </span> </a></li>
			</c:forEach>
		</ul>

	</div>

	<div class="col-xs-12 col-sm-12  col-md-9 col-lg-9">

		<div class="row">
			<div class="btn-group pull-right" data-toggle="buttons">
				<label class="btn btn-default btn-sm active" id="btn-grid">
					<input type="radio" name="listMode" id="option1" autocomplete="off"
					value="0" checked> <i class=" icon-th"></i>
				</label> <label class="btn btn-default btn-sm" id="btn-list"> <input
					type="radio" name="options" id="option2" autocomplete="off"
					value="1"> <i class="icon-list"></i>
				</label>

			</div>
			
		</div>


		<div class="row"><br/></div>
		<div class="row" id="grid">

			<p></p>

			<c:forEach items="${deals}" var="deal">
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">

					<div class="productbox">
						<a
							href="${pageContext.request.contextPath}/detail?deal=${deal.id}">

							<img class="img-responsive"
							src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=230&height=200" />

							<div class="caption">
								<p>${deal.name}
									<!-- 									<span class="pull-right label label-success"><i
										class="glyphicon glyphicon-shopping-cart"></i> <span class="">10</span></span> -->
								</p>
								<span class="old-price"><fmt:formatNumber type="number"
										maxFractionDigits="0" value="${deal.price}" /></span>
								<c:if test="${not empty deal.options}">
									<span class="finalprice"> &nbsp; <fmt:formatNumber
											type="number" maxFractionDigits="0"
											value="${deal.options[0].price}" /> <spring:message
											code="kalatag.currency" />
									</span>
									<!-- from <b>Amazon</b> -->
								</c:if>




							</div> <c:if test="${not empty deal.options}">
								<div class="saleoffrate">
									<span><fmt:formatNumber type="number"
											maxFractionDigits="0" value=" ${deal.options[0].discount}" />%</span><br>
									<%-- 	<spring:message code="kalatag.off" /> --%>
								</div>
							</c:if> <%-- 						<button type="button" class="btn btn-success btn-md btn-block">
							<spring:message code="kalatag.viewdeal" />
						</button> --%>
						</a>
					</div>

				</div>
			</c:forEach>
		</div>



		<div class="row hidden" id="list">
			<div class="list-group">
				<c:forEach items="${deals}" var="deal">


					<a class="list-group-item"
						href="${pageContext.request.contextPath}/detail?deal=${deal.id}">
						<div class="row">
							<img class="img-responsive col-sm-2 col-xs-4"
								src="${pageContext.request.contextPath}/files/deals/${deal.id}/thumbnail?width=160&height=145" />


							<div class="col-sm-10 col-xs-8">
								<h4>${deal.name}</h4>
								<p>${deal.description}</p>
								<p>
									<span class="old-price"><fmt:formatNumber type="number"
											maxFractionDigits="0" value="${deal.price}" /></span>
									<c:if test="${not empty deal.options}">
										<span class="finalprice"> &nbsp; <fmt:formatNumber
												type="number" maxFractionDigits="0"
												value="${deal.options[0].price}" /> <spring:message
												code="kalatag.currency" /> &nbsp;&nbsp; <c:if
												test="${not empty deal.options}">

												<span class="text text-danger">%<fmt:formatNumber
														type="number" maxFractionDigits="0"
														value=" ${deal.options[0].discount}" />&nbsp; <spring:message
														code="kalatag.off" /></span>
												<%-- 	<spring:message code="kalatag.off" /> --%>

											</c:if>
										</span>
										<!-- from <b>Amazon</b> -->
									</c:if>
								</p>

							</div>
						</div>
					</a>



				</c:forEach>
			</div>

		</div>


	</div>

</div>

<script>
	$(document).ready(function() {
		$('.pgwSlider').pgwSlider({
			displayList : false,
			displayControls : true,
			intervalDuration : 5000
		});

		startCountdown();

		$('#btn-list').click(function() {

			$('#grid').fadeOut('slow', function() {
				$('#list').removeClass('hidden');
				$('#list').show();
			});

		});

		$('#btn-grid').click(function() {

			$('#list').fadeOut('slow', function() {

				$('#grid').show();
			});

		});

	});
</script>


