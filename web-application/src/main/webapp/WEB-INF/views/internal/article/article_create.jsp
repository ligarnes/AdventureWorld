<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Article</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap.min.css" />'>
<link rel='stylesheet' href='<c:url value="/resources/theme/css/bootstrap-theme.min.css" />'>
<link rel='stylesheet' href='<c:url value="/resources/theme/css/mine.css" />'>
<link rel='stylesheet' href='<c:url value="/resources/theme/css/trumbowyg/trumbowyg.min.css" />'>
</head>
<body>

	<%@ include file="/WEB-INF/views/layout/header.jsp"%>


		<div class="container-fluid">

			<div class="row">
				<!-- start left -->
				<div class="col-sm-3"></div>
				<!-- end left -->

				<!-- start center -->
				<div class="col-sm-9">
					<%@ include file="/WEB-INF/views/internal/article/create.jsp"%>
				</div>
				<!-- end center -->
			</div>

			<!-- scripts -->
			<script type="text/javascript" src="<c:url value="/resources/theme/js/jquery-2.1.4.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="/resources/theme/js/bootstrap.min.js" />"></script>

			<!--  -->
			<script src="<c:url value="/WEB-INF/resources/theme/js/trumbowyg/lang/fr.min.js" />"></script>
		
		<script src="//rawgit.com/Alex-D/Trumbowyg/2.0.0-beta.4/dist/trumbowyg.min.js"></script>
        <script src="//rawgit.com/Alex-D/Trumbowyg/2.0.0-beta.4/dist/plugins/upload/trumbowyg.upload.min.js"></script>
        <script type="text/javascript">
            $('#article_content').trumbowyg({
                btnsDef: {
                    // Customizables dropdowns
                    image: {
                        dropdown: ['insertImage', 'upload'],
                        ico: 'insertImage'
                    }
                },
                btns: ['viewHTML',
                    '|', 'formatting',
                    '|', 'btnGrp-design',
                    '|', 'link',
                    '|', 'image',
                    '|', 'btnGrp-lists',
                    '|', 'horizontalRule']
            });
        </script>
        <!--   '|', 'foreColor', -->
	
		</div>
</body>
</html>
