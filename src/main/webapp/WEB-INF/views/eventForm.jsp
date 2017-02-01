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
    <jsp:attribute name="extra_css">
	   <link href="/kalendarz/resources/pikaday/pikaday.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="extra_js">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.js" integrity="sha256-NNpm8Jl9FFNBz7P8cceU6jK0xq/6P/XZ5+UQcXASXRw=" crossorigin="anonymous"></script>
	   <script src="/kalendarz/resources/pikaday/pikaday.js"></script> 
    	<script>
    	
	    	var picker = new Pikaday({ field: document.getElementById('eventStart'), 
							    		format: 'DD.MM.YYYY HH:mm',
									   showTime: true,
									   showMinutes: true,
							    	   use24hour: true,

									 });
	    	var picker2 = new Pikaday({ field: document.getElementById('eventEnd'), 
	    		format: 'DD.MM.YYYY HH:mm', use24hour: true,

				 });
		</script>
    </jsp:attribute>
    <jsp:body>
    	<c:if test="${event.id == 0}">
	    	<div class="alert alert-info" role="alert">
			  <strong>Tip:</strong> Jeżeli nie wiesz kiedy dodać wydarzenie - skorzystaj z <a href="/kalendarz/lookup">planera</a>.
			</div>
		</c:if>
		<form:form commandName="event">
			<t:form_input path="eventStart" type="date" key="eventForm.eventStart"/>
			<t:form_input path="eventEnd" type="date" key="eventForm.eventEnd"/>
			<t:form_textarea path="description" key="eventForm.description"/>
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
