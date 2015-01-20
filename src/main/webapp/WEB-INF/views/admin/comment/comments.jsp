<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<h2>
		<span class=" glyphicon glyphicon glyphicon-th-list"> </span>
		<spring:message code="comment.list" />
	</h2>
	<hr />
	<div class="col-sm-12 col-md-12">

		<table class="table">
			<thead class="table-heading">
				<tr>


					<th><spring:message code="comment.deal" /></th>
					<th><spring:message code="comment.text" /></th>
					<th><spring:message code="comment.author" /></th>
					<th><spring:message code="contact.email" /></th>
					<th><spring:message code="comment.date" /></th>
					<th><spring:message code="comment.accepted" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody class="table-body">
				<c:forEach var="comment" items="${comments}">
					<tr>

						<td><c:out value="${comment.deal.name}" /></td>
						<td><c:out value="${comment.commentText}" /></td>
						<td><c:out
								value="${comment.author.firstName} ${comment.author.lastName}" /></td>
						<td><c:out value="${comment.author.username}" /></td>
						<td><span class="persian-date"><fmt:formatDate
									pattern="yyyy/MM/dd" value="${comment.date}" /> </span> <span><fmt:formatDate
									type="time" value="${comment.date}" /> </span></td>
						<td><c:out value="${comment.accepted}" /></td>
						<td class="btn-group"><a class="btn btn-danger btn-sm"
							href="${pageContext.request.contextPath}/admin/comment/delete/${comment.id}"><span
								class=" glyphicon glyphicon-trash"></span> </a> <a
							class="btn btn-success btn-sm"
							href="${pageContext.request.contextPath}/admin/comment/change?id=${comment.id}&accept=1"><span
								class="glyphicon glyphicon-ok-circle"> </span> </a> <a
							class="btn btn-default btn-sm"
							href="${pageContext.request.contextPath}/admin/comment/change?id=${comment.id}&accept=0"><span
								class="glyphicon glyphicon-ban-circle"> </span> </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>




