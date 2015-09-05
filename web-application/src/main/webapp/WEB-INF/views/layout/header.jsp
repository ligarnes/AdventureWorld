<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Live Campaign</a>
    </div>
    

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
    
    	<!-- ${pageContext.request.requestURI} -->
    	<li class="${pageContext.request.requestURI eq '/WebApplication/WEB-INF/views/home.jsp' ? ' active' : ''}"><a href="/WebApplication/">Polos</a></li>
    	<li class="${pageContext.request.requestURI eq '/WebApplication/WEB-INF/views/player_list.jsp' ? ' active' : ''}"><a href="/WebApplication/player/">Joueurs</a></li>
    	<li class="${pageContext.request.requestURI eq '/WebApplication/WEB-INF/views/internal/article_create.jsp' ? ' active' : ''}"><a href="/WebApplication/article/create">Article</a></li>
    	
				<!-- 
    	<li class="active"><a href="#">World <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Rules</a></li> -->

			</ul>
      
      <ul class="nav navbar-nav navbar-right">
<!--         <ul class="nav nav-pills" role="tablist"> -->
  <li role="presentation"><a href="#">Profile</a></li>
  <li role="presentation"><a href="#">Messages <span class="badge">3</span></a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
    
  </div><!-- /.container-fluid -->
</nav>