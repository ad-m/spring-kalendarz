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
    <jsp:attribute name="extra_css">
	   <link href="/kalendarz/resources/pikaday/pikaday.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="extra_js">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.js" integrity="sha256-NNpm8Jl9FFNBz7P8cceU6jK0xq/6P/XZ5+UQcXASXRw=" crossorigin="anonymous"></script>
	   <script src="/kalendarz/resources/pikaday/pikaday.js"></script> 
    	<script>
   	    	var picker = new Pikaday({ field: document.getElementById('date'), 
							    		format: 'DD.MM.YYYY HH:mm',
							    		use24hour: true,
									 });
		</script>
    </jsp:attribute>
    <jsp:body>
		<form:form commandName="eventLookupCommand" class="col-sm-8 offset-md-1">
			<t:form_input path="date" key="eventLookupForm.date"/>
			<t:form_input path="durationEvent" key="eventLookupForm.durationEvent" help_text="Wprowadź dane w formacie HH:MM np. 5:25, co oznaczać będzie 5 godzin i 25 minut."/>
			<t:form_select path="durationLookup" key="eventLookupForm.durationLookup" items="${days}"/>
			<t:form_submit key="eventLookupForm.submit"/>
		</form:form>

    </jsp:body>
</t:genericpage>
