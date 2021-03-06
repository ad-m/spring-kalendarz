<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="key" %>
<%@ attribute name="path" %>
<%@ attribute name="type" required="false" %>
<%@ attribute name="help_text" required="false"  %>

<div class="form-group row">
	<label class="col-sm-2 col-form-label">
		<fmt:message key="${key}"/>{}
	</label> 
	<div class="col-sm-10">
		<form:input path="${path}" type="${(empty type) ? 'text' : type}" class="form-control"/>
		<c:if test="${empty help_text}"><p class="form-text text-muted">${help_text}</p></c:if>
		<form:errors path="${path}" cssStyle="color:red;"/>
	</div>
</div>
