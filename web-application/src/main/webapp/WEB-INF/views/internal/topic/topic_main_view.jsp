<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>

<div class="container-fluid">
<div class="row">

<button type="button" 
		class="btn btn-primary" 
		data-toggle="modal" 
		data-target="#topic_modal_add"
		onclick="$('#parent').val('Aucun'); $('#parent').selectpicker('refresh');">
  Ajouter
</button>
</div>

<div class="row">
	<div class="panel-group" id="accordion">

		<c:forEach items="${root_topics}" var="topic">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> 
						<span class="glyphicon glyphicon-folder-close"></span>
						${topic.name}
					</a>
					<button type="button" 
							class="btn glyphicon glyphicon-plus" 
							data-toggle="modal" 
							data-target="#topic_modal_add"
							onclick="$('#parent').val('${topic.id}'); $('#parent').selectpicker('refresh');">
					</button>
				</h4>
			</div>
			
			<c:set var="topic_name" value="${topic.name}" />
			
			<div id="collapseOne" class="panel-collapse collapse in">
				<ul class="list-group">
					<c:forEach items="${requestScope[topic_name]}" var="child">
					<li class="list-group-item">
						<span class="glyphicon glyphicon-folder-open text-primary"></span> 
						<a href="#">${child.name}</a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<div class="row">
	<div class="panel-group" id="accordion">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne"> <span
						class="glyphicon glyphicon-folder-close"> </span> Content
					</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<ul class="list-group">
					<li class="list-group-item"><span
						class="glyphicon glyphicon-pencil text-primary"></span> <a
						href="http://fb.com/moinakbarali">Articles</a>

						<ul class="list-group">
							<li class="list-group-item"><span
								class="glyphicon glyphicon-pencil text-primary"></span> <a
								href="http://fb.com/moinakbarali">Articles</a></li>

							<li class="list-group-item"><span
								class="glyphicon glyphicon-flash text-success"></span> <a
								href="http://fb.com/moinakbarali">News</a></li>

							<li class="list-group-item"><span
								class="glyphicon glyphicon-file text-info"></span><a
								href="http://fb.com/moinakbarali">Newsletters</a></li>

							<li class="list-group-item"><span
								class="glyphicon glyphicon-comment text-success"></span><a
								href="http://fb.com/moinakbarali">Comments</a><span
								class="badge">42</span></li>

						</ul></li>

					<li class="list-group-item"><span
						class="glyphicon glyphicon-flash text-success"></span><a
						href="http://fb.com/moinakbarali">News</a></li>

					<li class="list-group-item"><span
						class="glyphicon glyphicon-file text-info"></span><a
						href="http://fb.com/moinakbarali">Newsletters</a></li>

					<li class="list-group-item"><span
						class="glyphicon glyphicon-comment text-success"></span><a
						href="http://fb.com/moinakbarali">Comments</a><span class="badge">42</span></li>

				</ul>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseFour"><span class="glyphicon glyphicon-file">
					</span>Reports</a>
				</h4>
			</div>
			<div id="collapseFour" class="panel-collapse collapse">
				<div class="list-group">
					<a href="#" class="list-group-item"> Cras justo odio </a>
					<div class="list-group">
						<a href="#" class="list-group-item"> Cras justo odio </a> <a
							href="#" class="list-group-item">Dapibus ac facilisis in</a> <a
							href="#" class="list-group-item">Morbi leo risus</a> <a href="#"
							class="list-group-item">Porta ac consectetur ac</a> <a href="#"
							class="list-group-item">Vestibulum at eros</a>
					</div>
					<a href="#" class="list-group-item">Dapibus ac facilisis in</a> <a
						href="#" class="list-group-item">Morbi leo risus</a> <a href="#"
						class="list-group-item">Porta ac consectetur ac</a> <a href="#"
						class="list-group-item">Vestibulum at eros</a>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseFive"><span class="glyphicon glyphicon-heart">
					</span>Reports</a>
				</h4>
			</div>
			<div id="collapseFive" class="panel-collapse collapse">
				<div class="list-group">
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">List group item heading</h4>
						<p class="list-group-item-text">Donec id elit non mi porta
							gravida at eget metus. Maecenas sed diam eget risus varius
							blandit.</p>
					</a>
				</div>
				<div class="list-group">
					<a href="#" class="list-group-item active">
						<h4 class="list-group-item-heading">List group item heading</h4>
						<p class="list-group-item-text">Donec id elit non mi porta
							gravida at eget metus. Maecenas sed diam eget risus varius
							blandit.</p>
					</a>
				</div>
				<div class="list-group">
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">List group item heading</h4>
						<p class="list-group-item-text">Donec id elit non mi porta
							gravida at eget metus. Maecenas sed diam eget risus varius
							blandit.</p>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
</div>