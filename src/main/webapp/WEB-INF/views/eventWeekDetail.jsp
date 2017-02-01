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
		<p>Poniżej jest dostępny wykaz wydarzeń dla okresu 		    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${week.start}" />
		    -
		    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${week.end}" />:</p>
		<div class="card-columns">
			<c:forEach items="${event_list}" var="element"> 
				<t:event_small item="${element}"></t:event_small>
			</c:forEach>
		</div>
		
    <link rel="stylesheet" href="/kalendarz/resources/styles/timetablejs.css">
    <link rel="stylesheet" href="/kalendarz/resources/styles/demo.css">
	    <script src="/kalendarz/resources/scripts/timetable.js"></script>
	    <div class="timetable"></div>

	    <script>
	      var timetable = new Timetable();
	
	      timetable.setScope(9,2)
	
	      timetable.addLocations([
	    	  <c:forEach items="${userCategories}" var="current" varStatus="loop">"<c:out value="${current.name}"/>"
	    	  <c:if test="${!loop.last}">,</c:if>
	    	</c:forEach>
]);
	      <c:forEach items="${event_list2}" var="item">
	      timetable.addEvent('<c:out value="${item.description}"/>', '<c:out value="${item.category.name}"/>',
							new Date(<fmt:formatDate pattern="yyyy,M,D,H,m" value="${item.eventStart}" />), 
							new Date(<fmt:formatDate pattern="yyyy,M,D,H,m" value="${item.eventEnd}" />), { url: '/kalendarz/event-${item.id}' } );
	  	  </c:forEach>
	      
	      var renderer = new Timetable.Renderer(timetable);
	      renderer.draw('.timetable');
	    </script>
  	<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="/kalendarz/events/${week.prev.year}/${week.prev.week_no}">Poprzedni tydzień</a>
    <li class="page-item">
      <a class="page-link" href="/kalendarz/events/${week.next.year}/${week.next.week_no}">Następny tydzień</a>
    </li>
  </ul>
</nav>
    </jsp:body>
</t:genericpage>
