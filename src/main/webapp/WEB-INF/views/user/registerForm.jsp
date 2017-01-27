<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      <fmt:message key="registerForm.label"/>
    </jsp:attribute>
    <jsp:body>
		<form:form commandName="registerCommand">
			<div class="form-group row">
	      		<label class="col-sm-2 col-form-label"><fmt:message key="registerForm.username"/></label>
	      		<div class="col-sm-10">
	        		<form:input path="username" class="form-control"/>
	      		</div>
	    	</div>
		    <div class="form-group row">
	    		<label class="col-sm-2 col-form-label"><fmt:message key="registerForm.password"/></label>
	      		<div class="col-sm-10">
	        		<form:input path="password" type="password" class="form-control"/>
					<form:errors path="password" cssStyle="color:red;"/>
		    	</div>
	    	</div>
	    	<div class="form-group row">
	    		<label class="col-sm-2 col-form-label"><fmt:message key="registerForm.confirmationPassword"/></label>
	      		<div class="col-sm-10">
	        		<form:input path="confirmationPassword" type="password" class="form-control"/>
					<form:errors path="confirmationPassword" cssStyle="color:red;"/>
		    	</div>
	    	</div>
	    	<div class="form-group row">
	    		<div class="offset-sm-2 col-sm-10">
	        		<button type="submit" class="btn btn-primary"><fmt:message key="registerForm.submit"/></button>
		      	</div>
	    	</div>    
		</form:form>

    </jsp:body>
</t:genericpage>
