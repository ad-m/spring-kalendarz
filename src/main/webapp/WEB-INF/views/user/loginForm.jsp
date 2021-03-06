<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      <fmt:message key="logonForm.label"/>
    </jsp:attribute>
    <jsp:body>
		<form:form commandName="logonCommand" class="col-sm-6 col-md-5 offset-md-2">
			<t:form_input key="logonForm.username" path="username"/>
			<t:form_password key="logonForm.password" path="password"/>
			<t:form_submit key="logonForm.submit"/>
		</form:form>
    </jsp:body>
</t:genericpage>
