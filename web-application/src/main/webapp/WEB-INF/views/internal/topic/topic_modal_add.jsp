<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Modal -->
<div class="modal fade" id="topic_modal_add" tabindex="-1" role="dialog"
	aria-labelledby="topic_modal_add">
	
	<div class="modal-dialog" role="document">
		<form:form method="post" action="/WebApplication/topic/create"
			commandName="user" role="form">
			
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Ajouter une
						categorie</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="name" class="control-label">Nom:</label> <input
								type="text" class="form-control" id="name" name="name">
						</div>
						
						<div class="form-group">
						  <label for="sel1">Categorie parent:</label>
						  <select class="form-control" id="parent" name="parent">
						  	<option value="null">Aucun</option>
						    <c:forEach items="${root_topics}" var="topic">
						    <option value="${topic.id}">${topic.name}</option>
						    </c:forEach> 
						  </select>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
					<button type="submit" class="btn btn-primary">Ajouter</button>
				</div>
			</div>
		</form:form>
	</div>
</div>