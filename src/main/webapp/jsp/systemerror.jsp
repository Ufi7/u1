<!--error--><%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${errormessagecode != null}">
		<spring:message code="${errormessagecode}" arguments="${errormessageparamlist}">
		</spring:message>
	</c:when>
	<c:otherwise>
		<spring:message code="systemerror"/>
	</c:otherwise>
</c:choose>

