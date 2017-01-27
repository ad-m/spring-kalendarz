<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">Wyniki wyszukiwania "${query}"</jsp:attribute>
    <jsp:body>
		<ul>
			<c:forEach items="${event_list}" var="element"> 
				<li><a href="/kalendarz/event-${element.id}">${element.description}</a></li>
			</c:forEach>
		</ul>
    </jsp:body>
</t:genericpage>
