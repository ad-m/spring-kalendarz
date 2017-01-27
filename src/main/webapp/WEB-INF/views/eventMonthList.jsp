<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">Wykaz miesięcy dla roku ${year.year}</jsp:attribute>
    <jsp:body>
		<p>Wybierz miesiąć z listy poniżej, aby przejść do szczegółów:</p>
		<ul>
			<c:forEach items="${months_list}" var="element"> 
				<li><a href="/kalendarz/events/${element.year}/${element.month}">${element.month}</a></li>
			</c:forEach>
		</ul>
    </jsp:body>
</t:genericpage>
