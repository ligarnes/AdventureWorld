<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<title>${article.title}</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap.min.css" />'>
    <link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap-theme.min.css" />'>

	<!-- scripts -->
	<script type="text/javascript" src="<c:url value="/resources/theme/js/jquery-2.1.4.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/theme/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/theme/js/wysiwig/bootstrap-wysiwyg.js" />"></script>
 
</head>
<body>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- start body -->

<div class="container-fluid">
	<div class="row">
		<!-- start left -->
		<div class="col-sm-3"></div>
		<!-- end left -->

		<!-- start center -->
		<div class="col-sm-9">
			<div class="jumbotron">
				<h2>
					${article.title}
				</h2>
				<p>
					${article.content}
				</p>
				<!-- 
				<p>
					<a class="btn btn-primary btn-large" href="#">Learn more</a>
				</p>
				 -->
			</div>
		</div>
	</div>
</div>
 

</body>
</html>