
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>${title}</title>
<meta charset="UTF-8">
<meta name="description" content="خرید انواع کالا و خدمات تفریحی و گردشگری و محصولات زییایی و سلامت همراه با بهترین تخفیف و فروش ویژه">
<meta name="keywords" content="تخفیف,فروش,خرید,معامله,کالا,خدمات,گردشگری,زییایی,لیزر,پوست,مو,رستوران,کافه,غذا,هتل,تخفیفات,فروش ویژه,kalatag,کالاتگ,تگ,tag,پیشنهاد,بهترین,پزشکی,مسافرت,آژانس,تعطیلات,بلیط,کنسرت,ارزان,ارزون,جواهر,زیورآلات,کوپن,نت برگ,روزانه,کالابرگ,تخفیفان">
<meta name="author" content="Iman Namvar">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-site-verification" content="Yc1mbk4-I56SbDM4IxVKZuAhCkpqRcJf6mVgHr-umKg" />
<meta name="msvalidate.01" content="F52DA68057BF2F9C2CDE5FDAEF54BDB0" />


<!-- <link rel="stylesheet" href="http://ifont.ir/apicode/38"> -->


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.rtl.min.css" />
	<link href="${pageContext.request.contextPath}/resources/styles/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />

	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/font_awesome.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/website.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/my.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/countdown.css" />
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/pgwslider.min.css" />	 
	
		
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/owl.carousel.css" />	
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/owl.theme.css" />	
	
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/menu-rtl.css" />	
		<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/rtl.css" />	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/bootstrap.min.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/scripts/star-rating.min.js" type="text/javascript"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/pgwslider.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/countdown.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/gmap3.min.js"></script>
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/owl.carousel.min.js"></script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/myscript.js"></script>
	
	
</head>
<body>
	<div id="header">
		<div id="headerTitle">
			<tiles:insertAttribute name="header" />
		</div>
	</div>
	<div id="content">
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>
