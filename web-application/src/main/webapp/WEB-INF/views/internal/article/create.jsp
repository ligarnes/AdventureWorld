<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="panel panel-default">

	<form:form method="post" action="/WebApplication/article/createFinal" commandName="user" role="form">
	<div class="container-fluid">
		<div class="row">
			<div style="padding-left: 30px; padding-right: 30px; padding-top: 15px">

				<input type="text" class="form-control" id="title" placeholder="Titre" name="title">
			</div>
		</div>

		<div class="row">

			<div class="panel-body">
				<textarea id="article_content" name="content"></textarea>
			</div>
		</div>
		
		<div class="row">
			<button type="submit" class="btn btn-primary">Ajouter</button>
		</div>
		<!-- End row -->
	</div>
	</form:form>
	
</div>