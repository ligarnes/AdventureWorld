<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<form:form method="post" action="/WebApplication/player/create" commandName="user" role="form">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Ajouter un joueur</h4>
				</div>
				<div class="modal-body">
					<div class="panel panel-default">
					  <div class="panel-body">
					  	<form class="form-horizontal">
							<div class="form-group">
								<label for="name" class="control-label">Nom:</label>
								<input type="text" class="form-control" id="name" name="name">
							</div>
							<div class="form-group">
								<label for="isDM" class="control-label">Maitre du jeu:</label>
								<input type="checkbox" class="form-control" id="isDM" name="isDM"/>
							</div>
						</form>
					  </div>
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
					<button type="submit" class="btn btn-primary">Ajouter</button>
				</div>
			</div>
		</form:form>
	</div>
</div>