<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      <fmt:message key="eventForm.label"/>
    </jsp:attribute>
    <jsp:body>
		<form:form commandName="event">
			<t:form_input path="eventStart" key="eventForm.eventStart"/>
			<t:form_input path="eventEnd" key="eventForm.eventEnd"/>
			<t:form_input path="description" key="eventForm.description"/>
			<t:form_select path="category" key="eventForm.category" items="${userCategories}"/>			

	    	<div class="form-group row">
	    		<div class="offset-sm-2 col-sm-10">
	        		<button type="submit" class="btn btn-primary"><fmt:message key="eventForm.submit"/></button>
	        		<c:if test="${category.id > 0}">
	        		<a href="/kalendarz/events/${category.id}/delete" class="btn btn-danger"><fmt:message key="eventForm.delete"/></a>
					</c:if>
		      	</div>
	    	</div>    
		</form:form>

    </jsp:body>
</t:genericpage>
