<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>teal 3d dice roller</title>

    <link rel='stylesheet' href='<c:url value="/WEB-INF/resources/theme/css/dice/main.css" />'>
    <link rel='stylesheet' href='<c:url value="/WEB-INF/resources/theme/css/dice/dice.css" />'>
 
</head>

<body class="svg" style="margin: 0">
	<div class="control_panel">
		<p id="loading_text">Loading libraries, please wait a bit...</p>
	</div>
	<div id="info_div" style="display: none">
		<div class="center_field">
			<span id="label"></span>
		</div>
		<div class="center_field">
			<div class="bottom_field">
				<span id="labelhelp">click to continue or tap and drag again</span>
			</div>
		</div>
	</div>
	<div id="selector_div" style="display: none">
		<div class="center_field">
			<div id="sethelp">
				choose your dice set by clicking the dices or by direct input of
				notation,<br /> tap and drag on free space of screen or hit throw
				button to roll
			</div>
		</div>
		<div class="center_field">
			<input type="text" id="set" value="1d20"></input><br />
			<button id="clear">clear</button>
			<button style="margin-left: 0.6em" id="throw">throw</button>
		</div>
	</div>
	<div id="canvas"></div>

<script type="text/javascript" src="<c:url value="/resources/theme/js/dice/libs/teal.js" />"></script>

 <script type="text/javascript" src="<c:url value="/resources/theme/js/dice/libs/three.min.js" />"></script>
 <script type="text/javascript" src="<c:url value="/resources/theme/js/dice/libs/cannon.min.js" />"></script>

<script type="text/javascript" src="<c:url value="/resources/theme/js/dice/dice.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/theme/js/dice/main.js" />"></script>



	<script type="text/javascript" defer="defer">
		dice_initialize(document.body, window.innerWidth - 1,
				window.innerHeight - 1);
	</script>
</body>
</html>