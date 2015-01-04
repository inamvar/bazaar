<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="category.list" />
	</h2>
	<hr />
	<div class="col-sm-6 col-md-6">
		<p>
			<a class="btn btn-danger btn-sm"
				href="${pageContext.request.contextPath}/admin/category/add"><span
				class=" glyphicon glyphicon-plus-sign"> </span> <spring:message
					code="crud.add" /></a>
		</p>
		<div class="panel panel-default">
			<div class="panel-body">
				<table class="table">
					<thead class="table-heading">
						<tr>

							<th><spring:message code="category.name" /></th>

							<th></th>
						</tr>
					</thead>
					<tbody class="table-body">
						<c:forEach var="category" items="${categories}">
							<tr>

								<td><i class="${category.iconCss}"
									style="font-size: 1.8em;"> </i> <c:out value="${category.name}" /></td>

								<td><a class="btn btn-warning btn-xs"
									href="${pageContext.request.contextPath}/admin/category/delete/${category.id}"><span
										class=" glyphicon glyphicon-trash"> </span> <spring:message
											code="crud.delete" /></a> <a class="btn btn-success btn-xs"
									href="${pageContext.request.contextPath}/admin/category/update/${category.id}"><span
										class=" glyphicon glyphicon-pencil"> </span> <spring:message
											code="crud.edit" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

	</div>
</div>



