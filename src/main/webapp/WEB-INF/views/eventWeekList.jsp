<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">Wykaz tygodni dla ${month}</jsp:attribute>
    <jsp:body>
		<p>Wybierz tydzień z listy poniżej, aby przejść do szczegółów na temat ${month} za okres od ${month.startDate} do ${month.endDate}:</p>
		<ul>
			<c:forEach items="${weeks_list}" var="element"> 
				<li><a href="/kalendarz/events/${month.year}/${month.month}/${element.week_no}">${element}</a></li>
			</c:forEach>
		</ul>
    </jsp:body>
</t:genericpage>
