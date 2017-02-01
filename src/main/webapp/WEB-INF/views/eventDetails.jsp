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
		<div class="card text-center">
		  <div class="card-block">
		    <h4 class="card-title"><c:out value="${event.category.name}"/></h4>
		    <p class="card-text"><c:out value="${event.description}"/></p>
		  </div>
		  <div class="card-footer text-muted">
		    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.eventStart}" />
		    -
		    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.eventEnd}" />
		    ( ${event.duration} )
		  </div>
		</div>
		<a href="/kalendarz/events/${event.year}/${event.week}" class="pull-right btn btn-secondary btn-sm">Zobacz tydzień</a>
		<a href="/kalendarz/events/~create/edit-${event.id}" class="pull-right btn btn-primary btn-sm">Edytuj</a>
    </jsp:body>
</t:genericpage>
