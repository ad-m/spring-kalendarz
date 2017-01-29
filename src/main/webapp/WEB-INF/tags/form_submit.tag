<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@attribute name="key" %>

<div class="form-group row">
	<div class="offset-sm-2 col-sm-10">
		<button type="submit" class="btn btn-primary">
			<fmt:message key="${key}"/>
		</button>
	</div>
</div>