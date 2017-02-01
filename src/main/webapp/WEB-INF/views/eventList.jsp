<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">Wykaz wydarzeń</jsp:attribute>
    <jsp:body>
		<div class="card-columns">
			<c:forEach items="${event_list}" var="element"> 
				<t:event_small item="${element}"></t:event_small>
			</c:forEach>
		</div>
    </jsp:body>
</t:genericpage>
