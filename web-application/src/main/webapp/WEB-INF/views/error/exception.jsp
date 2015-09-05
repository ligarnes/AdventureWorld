<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>Error</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap.min.css" />'>
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap-theme.min.css" />'>
</head>
<body>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- start body -->     
<div class="alert alert-danger" role="alert">

	<div class="row" style="margin-top:5px;">
  		<div class="col-sm-8">
			<strong>URI</strong> ${pageContext.errorData.requestURI}
		</div>
	</div>
	
	<div class="row" style="margin-top:10px;">
  		<div class="col-sm-8">
			<strong>Status code</strong> ${pageContext.errorData.statusCode}
		</div>
	</div>
	
	<div class="row" style="margin-top:10px;">
  		<div class="col-sm-8">
			<strong>Error</strong> ${pageContext.exception}
		</div>
	</div>
	
	<div class="row" style="margin-top:10px;">
  		<div class="col-sm-8">
			<strong>Stack trace</strong>
			<c:forEach var="trace" 
			         items="${pageContext.exception.stackTrace}">
			<p>${trace}</p>
			</c:forEach>
		</div>
	</div>
	
</div>
<!-- end body -->
 
 
<!-- scripts -->
 <script type="text/javascript" src="<c:url value="/resources/theme/js/jquery.2.1.4.min.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/theme/js/bootstrap.min.js" />"></script>

</body>
</html>
