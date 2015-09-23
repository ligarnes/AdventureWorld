<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


<div class="container-fluid">
	<div class="row">

		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#topic_modal_add"
			onclick="$('#parent').val('Aucun'); $('#parent').selectpicker('refresh');">
			Ajouter</button>
	</div>

	<div class="row top10">
		<div class="panel-group" id="accordion">

			<c:forEach items="${root_topics}" var="topic">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> <span
								class="glyphicon glyphicon-folder-close"></span> ${topic.name}
							</a>
							<button type="button" class="btn glyphicon glyphicon-plus"
								data-toggle="modal" data-target="#topic_modal_add"
								onclick="$('#parent').val('${topic.id}'); $('#parent').selectpicker('refresh');">
							</button>
						</h4>
					</div>

					<div id="collapseOne" class="panel-collapse collapse in">

						<c:set var="topic_id">${topic.id}</c:set>
						<c:set var="children" value="${requestScope[topic_id]}"/>

						<tags:treeView children="${children}"/>
					</div>

				</div>
			</c:forEach>
		</div>
	</div>
	
	<tags:topicTable root_topics="${root_topics}"/>

</div>