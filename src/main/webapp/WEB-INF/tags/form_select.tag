<%@ tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="key" %>
<%@ attribute name="path" %>
<%@ attribute name="items" type="java.util.List" %>

<div class="form-group row">
	<label class="col-sm-2 col-form-label"><fmt:message key="${key}"/></label>
	<div class="col-sm-10">
		<form:select path="${path}" items="${items}" class="form-control" itemLabel="name" itemValue="id"/>
		<form:errors path="${path}" cssStyle="color:red;"/>
	</div>
</div>