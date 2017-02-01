<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="item" type="info.jawne.kalendarz.models.Event" %>

  <div class="card card-outline-<%=(new String[]{"primary", "success", "info", "warning", "danger"}[((int) item.getCategory().getId()) % 4]) %>">
    <div class="card-block ">
      <h4 class="card-title"><a href="/kalendarz/event-${item.id}"><c:out value="${item.category.name}"/></a></h4>
      <p class="card-text"><c:out value="${item.description}"/></p>
    </div>
    <div class="card-footer"><small class="text-muted"><fmt:formatDate type="both" 
            dateStyle="short" timeStyle="short" 
            value="${item.eventStart}" /> - <fmt:formatDate type="both" 
            dateStyle="short" timeStyle="short" 
            value="${item.eventEnd}" /> (${item.duration})</small></div>
  </div>