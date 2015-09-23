<%@ attribute name="children" type="java.util.List" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<!-- TreeView tag -->
<ul class="list-group">
	<c:forEach items="${children}" var="child">
		<li class="list-group-item">
			<span class="glyphicon glyphicon-folder-open text-primary"></span> 
			<a href="#">${child.name}</a>
			
			<c:set var="topic_name" value="${topic.name}"/>
			<c:set var="subChild" value="${requestScope[topic_name]}"/>
			<tags:treeView children="${subChild}"/>
		</li>
	</c:forEach>
</ul>