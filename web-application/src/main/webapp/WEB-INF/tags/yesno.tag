<%@ attribute name="value" type="java.lang.Boolean" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${value}">Oui</c:when>
	<c:otherwise>Non</c:otherwise>
</c:choose>