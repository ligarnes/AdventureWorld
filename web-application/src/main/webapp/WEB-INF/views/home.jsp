<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap.min.css" />'>
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap-theme.min.css" />'>
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/mine.css" />'>
</head>
<body> 

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- start body -->     
<h1>
	Hello world!  
</h1>


<P>  The time on the server is ${serverTime}. </P>


<!-- end body -->
 
 
<!-- scripts -->
 <script type="text/javascript" src="<c:url value="/resources/theme/js/jquery-2.1.4.min.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/theme/js/bootstrap.min.js" />"></script>

</body>
</html>
