<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      <fmt:message key="categoryForm.label"/>
    </jsp:attribute>
    <jsp:body>
		<form:form commandName="category">
			<div class="form-group row">
	      		<label class="col-sm-2 col-form-label"><fmt:message key="categoryForm.name"/></label>
	      		<div class="col-sm-10">
	        		<form:input path="name" class="form-control"/>
					<form:errors path="name" cssStyle="color:red;"/>
	      		</div>
	    	</div>
		    <div class="form-group row">
	    		<label class="col-sm-2 col-form-label"><fmt:message key="categoryForm.description"/></label>
	      		<div class="col-sm-10">
	        		<form:input path="description" class="form-control"/>
					<form:errors path="description" cssStyle="color:red;"/>
		    	</div>
	    	</div>
	    	<div class="form-group row">
	    		<div class="offset-sm-2 col-sm-10">
	        		<button type="submit" class="btn btn-primary"><fmt:message key="categoryForm.submit"/></button>
	        		<c:if test="${category.id > 0}">
	        		<a href="/kalendarz/categories/${category.id}/delete" class="btn btn-danger"><fmt:message key="categoryForm.delete"/></a>
					</c:if>

		      	</div>
	    	</div>    
		</form:form>

    </jsp:body>
</t:genericpage>
