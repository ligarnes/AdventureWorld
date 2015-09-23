<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>


<div class="panel panel-default">
	<div class="panel-heading">Liste des joueurs</div>
	<div class="panel-body">


		<table class="table">
			<thead>
				<tr>
					<th>Nom</th>
					<th>MJ</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${players}" var="player">
					<tr>
						<td>${player.name}</td>
						<td><tags:yesno value="${player.dm}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Ajouter un joueur
</button>
