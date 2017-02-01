<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      <fmt:message key="eventLookupForm.label"/>
    </jsp:attribute>
    <jsp:body>
    	<p>
    	Wyszukiwanie czasu wolnego o długości ${eventLookupCommand.durationEvent} w okresie od 
    	<fmt:formatDate type="both"  dateStyle="short" timeStyle="short" value="${eventLookupCommand.start}" />
    	do
    	<fmt:formatDate type="both"  dateStyle="short" timeStyle="short" value="${eventLookupCommand.end}" />
    	</p>
    	
    	    		
    	<p>Na podstawie analizy twojego kalendarza wytypowaliśmy następujące propozycje terminów wydarzeń. Wybierz propozycje, aby przejść do dodawania wydarzenia:</p>
		<div class="list-group">
		  	<c:forEach items="${match_list.list}" var="element"> 
			  <a class="list-group-item" href="/kalendarz/events/~create/suggestion-${element.id}"><fmt:formatDate type="both"  dateStyle="short" timeStyle="short" value="${element.event.eventEnd}" /> - okienko wynosi ${element.free_time}</a>
			</c:forEach> 
		</div>
		<hr>
		<h2>Występują następująca wydarzenia w przeszukiwanym okresie:</h2>
		<div class="card-columns">
			<c:forEach items="${event_list}" var="element"> 
				<t:event_small item="${element}"></t:event_small>
			</c:forEach>
		</div>
    </jsp:body>
</t:genericpage>
