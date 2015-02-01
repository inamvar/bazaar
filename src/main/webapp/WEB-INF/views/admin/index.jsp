<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
<div class="col-sm-12">
<h1>
	<spring:message code="welcome.title"/>
</h1>
<hr/>
</div>
<div class="col-sm-5">
	<div class="panel panel-default">
		<div class="panel-heading">
		
			<span class="glyphicon glyphicon-comment"> </span> <spring:message code="comments.unAccepted"/>  <span class="pull-right"> <a
							class="btn btn-primary btn-xs"
							href="${pageContext.request.contextPath}/admin/acceptAllComments"><span
								class="glyphicon glyphicon-ok-circle"> </span> <spring:message code="comments.acceptAll"/> <span class="badge">${commentsCount}</span> </a>  </span>
					
		
		</div>	
		<div class="panel-body"  style="max-height:400px; overflow-y:scroll;">
									<c:forEach items="${comments}" var="comment">
								<div>
									<p class="text text-muted">
										<span class="glyphicon glyphicon-user"></span>
										${comment.author.firstName} ${comment.author.lastName} | ${comment.deal.name} | <span><fmt:formatDate
												pattern="hh:mm:ss" value="${comment.date}" /> </span> <span
											class="persian-date"><fmt:formatDate
												pattern="yyyy/MM/dd" value="${comment.date}" /> </span> <span class="pull-right"><a
							class="btn btn-success btn-sm"
							href="${pageContext.request.contextPath}/admin/acceptComment?id=${comment.id}&accept=1"><span
								class="glyphicon glyphicon-ok-circle"> </span> </a></span>
									<p class="text text-info">${comment.commentText}</p>
									<hr />
								</div>
							</c:forEach>
		</div>
	</div>
</div>
<div class="col-sm-4">
</div>
<div class="col-sm-3">
</div>

</div>



