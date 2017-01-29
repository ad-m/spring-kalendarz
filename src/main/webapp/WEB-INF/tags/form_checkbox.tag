<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="key" %>
<%@ attribute name="path" %>

<div class="form-group row">
    <div class="col-sm-10 offset-2">
		<div class="form-check">
			<form:checkbox path="${path}" class="form-check-input" />
	        <fmt:message key="${key}"/>
	        <form:errors path="${path}" cssStyle="color:red;"/>
	    </div>
	</div>
 </div>