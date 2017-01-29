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
		<form:form commandName="eventLookupCommand" class="col-sm-8 offset-md-1">
			<t:form_input path="name" key="eventLookupForm.name"/>
			<t:form_input path="description" key="eventLookupForm.description"/>
			<t:form_select path="category" key="eventLookupForm.category" items="${categories}"/>
			<t:form_input path="date" key="eventLookupForm.date"/>
			<t:form_rows>
    			<jsp:attribute name="label">
					<fmt:message key="eventLookupForm.period"/>
				</jsp:attribute>
				<jsp:attribute name="input">
					<form:select path="period" items="${periods}"/>	
				</jsp:attribute>
			</t:form_rows>
			<t:form_submit key="eventLookupForm.submit"/>
		</form:form>

    </jsp:body>
</t:genericpage>
