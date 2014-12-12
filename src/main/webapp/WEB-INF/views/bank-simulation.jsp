<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-sm-4 col-md-4 col-sm-offset-4 col-md-offset-4">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h2>Bank simulation page</h2>
		</div>
		<div class="panel-body">
			<p>Merchant ID: ${MID}</p>
			<p>Reference number : ${RefNum}</p>
			<p>Reservation number : ${ResNum}</p>
			<p>Trance number : ${TraceNo}</p>
			<h4 class="text text-info">Amount : ${Amount}</h4>
			<form action="${RedirectURL}" method="POST">
				<input type="hidden" name="State" value="OK" /> <input
					type="hidden" name="RefNum" value="${RefNum}" /> <input
					type="hidden" name="ResNum" value="${ResNum}" /> <input
					type="hidden" name="MID" value="${MID}" /> <input type="hidden"
					name="TraceNo" value="${TraceNo}" />
				<button type="submit" class="btn btn-primary">Submit and back to site</button>
			</form>
		</div>
	</div>

</div>

