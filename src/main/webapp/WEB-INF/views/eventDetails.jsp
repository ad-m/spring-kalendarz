<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
     ${event.description}
    </jsp:attribute>
    <jsp:body>
		<p>Start: ${event.eventStart}</p>
		<p>End: ${event.eventEnd}</p>
		<c:if test="${not empty event.category}">
    		<p>Category: ${event.category.name}</p>
		</c:if>
    </jsp:body>
</t:genericpage>
