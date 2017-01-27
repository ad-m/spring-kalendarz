<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">Wykaz roczników</jsp:attribute>
    <jsp:body>
		<p>Wybierz rok z listy poniżej, aby przejść do szczegółów:</p>
		<ul>
			<c:forEach items="${year_list}" var="element"> 
				<li><a href="/kalendarz/events/${element.year}">${element.year}</a></li>
			</c:forEach>
		</ul>
    </jsp:body>
</t:genericpage>
