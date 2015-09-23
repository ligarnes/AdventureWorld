<%@ attribute name="root_topics" type="java.util.List" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<!-- TreeView tag -->
<div class="row">
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>parent id</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${root_topics}" var="topic">
				<tr>
					<td>${topic.id}</td>
					<td>${topic.name}</td>
					<td>${topic.parentId}</td>
				</tr>

				<c:set var="topic_id">${topic.id}</c:set>
				<c:set var="children" value="${requestScope[topic_id]}" />

				<c:forEach items="${children}" var="child">
					<tr>
						<td>${child.id}</td>
						<td>${child.name}</td>
						<td>${child.parentId}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</div>