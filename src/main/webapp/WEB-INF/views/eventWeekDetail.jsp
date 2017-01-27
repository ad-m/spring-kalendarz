<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">Wykaz wydarzeń dla ${week}</jsp:attribute>
    <jsp:body>
		<p>Poniżej jest dostępny wykaz wydarzeń dla okresu od ${week.start} do ${week.end}:</p>
		<ul>
			<c:forEach items="${event_list}" var="element"> 
				<li>${element}</li>
			</c:forEach>
		</ul>
    </jsp:body>
</t:genericpage>
